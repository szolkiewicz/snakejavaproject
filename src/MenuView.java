
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 * Klasa reprezentująca menu gry.
 */
public class MenuView extends javax.swing.JPanel {
    private boolean valid = false;
    /**
     * Creates new form MenuView
     */
    public MenuView() {
        initComponents();
        setScoreLabel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        frogsinput = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        foodinput = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        snakesinput = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        validInfo = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        scorelabel = new javax.swing.JLabel();

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setBackground(java.awt.Color.darkGray);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setPreferredSize(new java.awt.Dimension(570, 100));
        jPanel1.add(jLabel1, new java.awt.GridBagConstraints());

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.rowHeights = new int[] {10};
        jPanel3.setLayout(jPanel3Layout);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        jPanel8.setLayout(new java.awt.GridLayout(0, 1));

        jLabel11.setText("Frogs:");
        jPanel8.add(jLabel11);

        frogsinput.setText("1");
        frogsinput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frogsinputActionPerformed(evt);
            }
        });
        jPanel8.add(frogsinput);

        jPanel6.add(jPanel8);

        jPanel9.setLayout(new java.awt.GridLayout(0, 1));

        jLabel7.setText("Apples:");
        jPanel9.add(jLabel7);

        foodinput.setText("1");
        jPanel9.add(foodinput);

        jPanel6.add(jPanel9);

        jPanel10.setLayout(new java.awt.GridLayout(0, 1));

        jLabel3.setText("Snakes:");
        jPanel10.add(jLabel3);

        snakesinput.setText("1");
        snakesinput.setToolTipText("");
        snakesinput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snakesinputActionPerformed(evt);
            }
        });
        jPanel10.add(snakesinput);

        jPanel6.add(jPanel10);

        jPanel4.add(jPanel6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        jPanel3.add(jPanel4, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

        jPanel2.setLayout(new java.awt.CardLayout());

        jButton1.setText("play");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, "card2");

        jPanel7.add(jPanel2);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jButton3.setText("exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel5);

        validInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        validInfo.setText("values: 1-9");
        jPanel7.add(validInfo);

        jLabel4.setText("Best Score:");
        jPanel11.add(jLabel4);

        scorelabel.setText("jLabel2");
        jPanel11.add(scorelabel);

        jPanel7.add(jPanel11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        jPanel3.add(jPanel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel1.add(jPanel3, gridBagConstraints);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SnakeGame topFrame = (SnakeGame) SwingUtilities.getWindowAncestor(this);
        int snakes = 0;
        int food = 0;
        int frogs= 0;
        try {
                snakes = Integer.parseInt(snakesinput.getText());
                food = Integer.parseInt(foodinput.getText());
                frogs = Integer.parseInt(frogsinput.getText());
            } catch (NumberFormatException nfe) {
            }
        if(snakes > 0 && snakes < 10 && food > -1 && food < 10 && frogs > -1 && frogs < 10)
            valid = true;
        if(valid){
            topFrame.setValues(snakes, food, frogs);
            topFrame.InitializeGame();
            valid = false;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void setScoreLabel() {
        final String FILE_PATH = "best_score.txt";
        try {
            // Read the current best score from the file
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line = reader.readLine();
            if (line != null) {
                String bestScore = line;
                scorelabel.setText(bestScore);
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    private void snakesinputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snakesinputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_snakesinputActionPerformed

    private void frogsinputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frogsinputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frogsinputActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField foodinput;
    private javax.swing.JTextField frogsinput;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private static javax.swing.JLabel scorelabel;
    private javax.swing.JTextField snakesinput;
    private javax.swing.JLabel validInfo;
    // End of variables declaration//GEN-END:variables
}
