import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Klasa reprezentująca panel gry.
 */
public final class GameBoard extends JPanel implements ActionListener {

    static final int BOARD_WIDTH = 800;
    static final int BOARD_HEIGHT = 500;
    private int ID;
    final Font font = new Font("TimesRoman", Font.BOLD, 20);
    private Object mutex = new Object();
    
    
    
    ArrayList<MovingEntity> listOfMovables = new ArrayList<>();
    ArrayList<Renderable> listOfRenderables = new ArrayList<>();
    ArrayList<Collidable> listOfCollidables = new ArrayList<>();
    
    GameState gameState = new GameState(80,50,listOfCollidables);
    
    ArrayList<Obstacle> listOfObstacles = new ArrayList<>();
    ArrayList<Playable> listOfPlayables = new ArrayList<>();
    ArrayList<Edible> listOfEdibles = new ArrayList<>();
    
    boolean inGame = false;
    
    
     /**
     * Konstruktor klasy GameBoard.
     */
    public GameBoard() {
        TextureLoader.loadTextures();
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (inGame) {
                    gameState.addInput(e.getKeyCode());
                } else {
                    initiateGame();
                }
            }
        });
        initiateGame();
    }
    /**
     * Sprawdza, czy gra jest w trakcie.
     *
     * @return True, jeśli gra jest w trakcie. False w przeciwnym przypadku.
     */
    public boolean isGame(){
        return inGame;
    }
    /**
     * Inicjalizuje grę.
     */
    protected void initiateGame() {
        ID = 0;
        inGame = false;
        
        listOfMovables = new ArrayList<>();
        listOfRenderables = new ArrayList<>();
        listOfCollidables = new ArrayList<>();
    
        listOfObstacles = new ArrayList<>();
        listOfPlayables = new ArrayList<>();
        listOfEdibles = new ArrayList<>();
    }
    /**
     * Inicjalizuje grę z określoną liczbą węży, jedzenia i żab.
     *
     * @param snakes Liczba węży.
     * @param food   Liczba jedzenia.
     * @param frogs  Liczba żab.
     */
    protected void initiateGame(int snakes, int food, int frogs) {
        ID = 0;
        ArrayList<Color> values = new ArrayList<>(Arrays.asList(Color.ORANGE,Color.CYAN,Color.WHITE, Color.YELLOW, Color.RED, Color.GREEN, Color.MAGENTA, Color.BLUE, Color.PINK));
        inintializeEntities(1,snakes-1,values,food,frogs);

        inGame = true;

    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            for (Renderable entity : listOfRenderables) {
                entity.render(g);
            }
        } else {
            String scoreText = String.format("Game Over!");
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(scoreText, (BOARD_WIDTH - getFontMetrics(g.getFont()).stringWidth(scoreText)) / 2, BOARD_HEIGHT / 2);
        }
    }
    private void incrementID(){
        ID++;
    }
    
    /**
     * Zwraca wyniki graczy.
     *
     * @return Mapa z wynikami graczy, gdzie kluczem jest ID gracza, a wartością jest wynik.
     */
    public TreeMap<Integer,Integer> getScores(){
        TreeMap<Integer, Integer> scores = new TreeMap<>();
        for(Playable entity : listOfPlayables){
            scores.put(((Entity) entity).getID(), ((Snake) entity).getScore());
        }
        return scores;
    }
    /**
     * Dodaje nowy obiekt do gry.
     *
     * @param entity Obiekt do dodania.
     */
    public void addEntity(Entity entity){
        observe();
        entity.setPos(gameState.randomFreeSpace());
        if (entity instanceof MovingEntity) {
            listOfMovables.add((MovingEntity)entity);
            ((MovingEntity)entity).setGameState(gameState);
            ((MovingEntity)entity).setMutex(mutex);
        }
        if (entity instanceof Playable) {
            listOfPlayables.add((Playable)entity); 
        }
        if (entity instanceof Edible) {
            listOfEdibles.add((Edible)entity);
        } 
        listOfRenderables.add((Renderable)entity);
        listOfCollidables.add((Collidable)entity);
        entity.setID(ID);
        incrementID();
    }
    /**
     * Usuwa obiekt z gry.
     *
     * @param entity Obiekt do usunięcia.
     */
    public void removeEntity(Entity entity){
        if (entity instanceof Movable) {
            listOfMovables.remove((Movable)entity);
        }
        if (entity instanceof Edible) {
            listOfEdibles.remove((Edible)entity);
        } 
        if (entity instanceof Playable) {
            listOfPlayables.remove((Playable)entity); 
        }
        if (entity instanceof Obstacle) {
            listOfObstacles.remove((Obstacle)entity); 
        }
        listOfCollidables.remove((Collidable)entity);
        listOfRenderables.remove((Renderable)entity);
    }
    
    public void inintializeEntities(int snakes, int aiSnakes, ArrayList<Color> color, int food, int frogs){
        ArrayList<Obstacle> tmpObstacles = ObstacleGenerator.generateHollowRectangle(80, 50,0,0);
        
        listOfObstacles.addAll(tmpObstacles);
        for (Obstacle obstacle : listOfObstacles) {
            Renderable renderable = (Renderable) obstacle;
            listOfRenderables.add(renderable);
            listOfCollidables.add((Collidable) obstacle);
        }
        for (int i = 0; i < snakes; i++){
            Entity entity = new PlayableSnake(1,40,ID);
            entity.setColor(color.get(ID%color.size()));
            addEntity(entity);
        }
        
        for (int i = 0; i < aiSnakes; i++){
            Entity entity = new AISnake(i * 5,40,ID);
            entity.setColor(color.get(ID%color.size()));
            addEntity(entity);
        }
        
        for (int i = 0; i < food; i++){
            Entity entity =  new Food(10,13);
            addEntity(entity);
        }
        
        for (int i = 0; i < frogs; i++){
            Entity entity = new Frog(12,9);
            addEntity(entity);
        }
    }
    
    protected void move() {
        List<Thread> threads = new ArrayList<>();

        for (MovingEntity movable : listOfMovables) {
            Thread thread = new Thread(movable);
            thread.start();
            threads.add(thread);
        }

        // Oczekiwanie na zakończenie wszystkich wątków
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        gameState.clearInput();
    }
    /**
     * Dodaje wciśnięty klawisz do gry.
     *
     * @param key Kod wciśniętego klawisza.
     */
    public void addInput(int key){
        gameState.addInput(key);
    }
    
    protected void collisionTest() {
        ArrayList<Playable> deadSnakes = new ArrayList<>();
        for (Playable playable : listOfPlayables) {
            for (Edible edible : listOfEdibles) {
                if(((Collidable)playable).collidesWith((Entity)edible)){
                    ((Entity)edible).setPos(gameState.randomFreeSpace());
                    ((Snake)playable).increaseSize();
                }
            }
            for (Collidable collidable : listOfCollidables) {
                if((collidable).collidesWith((Entity)playable)){
                    if(collidable instanceof Edible || playable instanceof Edible)
                        break;
                    else
                        deadSnakes.add(playable);
                }
            }
        }
        for (Playable playable : deadSnakes) {
            removeEntity((Entity)playable);
        }
    }
    
    private void observe() {
        gameState.ereaseMap();
        for (Collidable collidable : listOfCollidables) {
            gameState.locateEntities(collidable.locate(),((Entity)collidable).getValue());
        }
        if(listOfPlayables.isEmpty())
            inGame = false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            move();
            observe();
            collisionTest();
        }
        repaint();
    }
}
