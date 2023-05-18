import java.util.Random;
import java.awt.Point;

public class Food extends Entity {
    
    public Food() {
        int posX = generatePos(GameBoard.BOARD_WIDTH);
        int posY = generatePos(GameBoard.BOARD_HEIGHT);
        pos = new Point(posX, posY);
    }

    private int generatePos(int size) {
        Random random = new Random();
        return random.nextInt(size / GameBoard.PIXEL_SIZE) * GameBoard.PIXEL_SIZE;
    }
}
