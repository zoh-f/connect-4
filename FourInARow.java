import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
* FourInARow functions as a remake of the classic board game Connect Four. This is a graphical two-player game
* in which players take turns dropping pieces until one player matches four pieces of the same color either
* horizontally, vertically, or diagonally.
*
*/
public class FourInARow extends JPanel implements MouseListener {
   private Space[][] spaces = new Space[6][7];
   private Arrow[] arrows = new Arrow[7];
   private boolean player1, playing;
   public final static Color FIELD_COLOR = Color.LIGHT_GRAY, P1_COLOR = Color.RED, P2_COLOR = Color.YELLOW;
   private final ResetButton reset = new ResetButton();
   private String statusMessage;
   private int yellowWins = 0;
   private int redWins = 0;
/**
* Instantiates all spaces and all arrows. Initializes game to Player 1's (Red) turn
*/
   public FourInARow() {
      
      for (int i = 0; i < 6; i++) {
         for (int j = 0; j < 7; j++) {
            spaces[i][j] = new Space ((j+1)*Space.WIDTH, (i+1)*Space.WIDTH);
         }
      }
         
      for (int i = 0; i < 7; i++) {
         arrows[i] = new Arrow((i+1)*Space.WIDTH + (Space.WIDTH / 3));
      }
      
      addMouseListener(this);
      player1 = playing = true;
      statusMessage = "Red's Turn";
      repaint();
   }
   public void paintComponent(Graphics g) {
   //color the background
      g.setColor(FIELD_COLOR);
      g.fillRect(0, 0, getWidth(), getHeight());
   
      for (Arrow a : arrows) {
         a.draw(g);
      }
      for (Space[] column : spaces) {
         for (Space s : column) {
            s.draw(g);
         }
      }
   
   //display the status message
      g.setColor(Color.BLACK);
      g.setFont(new Font("Arial", Font.BOLD, 30));
      g.drawString(statusMessage, 700, 100);
   //draw the reset button
      reset.draw(g);
   }
/**
* Resets the game into playing mode and clears all spaces
*/
   public void resetGame() {
   
      for (Space[] column : spaces) {
         for (Space s : column) {
            s.clear();
         }
      }
      
      playing = true;
      if (player1 = true)
         statusMessage = "Red's Turn";
      else
         statusMessage = "Yellow's Turn";
   }
   
   public boolean checkWin(Color color) {
   
      boolean diagonal = (diagonalWinSWtoNE(color) || diagonalWinNWtoSE(color));
      boolean horizontal = false;
      boolean vertical = false;
                  
      for (int i = 0; i < 6; i++) {
         if (horizontalWin(i, color)) {
            horizontal = true;
            System.out.print("Horizontal Win by ");
         }
      }
                                       
      for (int c = 0; c <= 6; c++) {
         for (int r = 0; r <= 2; r++) {
            if (verticalWin(r, c, color)) {
               vertical = true;
               System.out.print("Vertical Win by ");
            }
         }
      }
      
      if (diagonal)
         System.out.print("Diagonal win by ");
                  
      if (vertical || horizontal || diagonal) {
         return true;          
      }
      
      return false;
   }
/**
* Determines if there is an upper-left to lower-right diagonal win
*
* @param color The player color to match
* @return true if there is an upper-left to lower-right diagonal win, false otherwise
*/
   public boolean diagonalWinNWtoSE(Color color) {
         
      for (int i = 0; i <= 2; i++){
         for (int j = 0; j <= 3; j++){
            if (spaces[i][j].getColor().equals(spaces[i+1][j+1].getColor())){
               if (spaces[i+1][j+1].getColor().equals(spaces[i+2][j+2].getColor())){
                  if (spaces[i+2][j+2].getColor().equals(spaces[i+3][j+3].getColor())){
                     if (spaces[i+3][j+3].getColor().equals(color)){
                        return true;
                     }
                  }
               }
            }
         }
      }
      return false;
   }
   
   public boolean diagonalWinSWtoNE(Color color) {
                 
      for (int i = 3; i <= 5; i++){
         for (int j = 0; j <= 3; j++){
            if (spaces[i][j].getColor().equals(spaces[i-1][j+1].getColor())){
               if (spaces[i-1][j+1].getColor().equals(spaces[i-2][j+2].getColor())){
                  if (spaces[i-2][j+2].getColor().equals(spaces[i-3][j+3].getColor())){
                     if (spaces[i-3][j+3].getColor().equals(color)){
                        return true;
                     }
                  }
               }
            }
         }
      }
   
      return false;
   }
/**
* Determines if there is a horizontal win in the given row
*
* @param row the row to inspect
* @param color the player color to match
* @return true if there is a horizontal win in the given row, false otherwise
*/
   public boolean horizontalWin(int row, Color color) {
            
      for (int i = 0; i <= 3; i++) {
         if (spaces[row][i].getColor().equals(spaces[row][i+1].getColor())){
            if (spaces[row][i+1].getColor().equals(spaces[row][i+2].getColor())){
               if (spaces[row][i+2].getColor().equals(spaces[row][i+3].getColor())){
                  if (spaces[row][i+3].getColor().equals(color)){
                     return true;
                  }
               }
            }
         }
      }
      return false;
   }
/**
* Checks for a vertical win in the given column.
*
* @param row the row where the last piece was placed
* @param col the column to inspect
* @param color the player color to match
* @return true if there is a vertical win in the given row, false otherwise
*/
   public boolean verticalWin(int row, int col, Color color) {
   
      if (row <= 2){
         if (spaces[row][col].getColor().equals(color)){
            if (spaces[row+1][col].getColor().equals(color)){
               if (spaces[row+2][col].getColor().equals(color)){
                  if (spaces[row+3][col].getColor().equals(color)){
                     return true;
                  }
               }
            }
         }
      }
      return false;
   }
   public boolean dropPiece(int col, Color color) {
        
      for (int row = 5; row >= 0; row--){
         if (spaces[row][col].occupied() == false){
            spaces[row][col].accept(color);
            return true;
         }
      }
   
      return false;
   }
   public static void main(String[] args) {
      FourInARow game = new FourInARow();
      JFrame frame = new JFrame("4 In A Row");
      frame.setSize(1000, 600);
      frame.setLocation(50, 50);
      frame.setContentPane(game);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   
   }
/**
* Handles mouse clicks
*
* @param e contains data on where/how the mouse was clicked
*/
   @Override
   public void mouseClicked(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
   //column clicked during gameplay
      if (playing) {
         if (x >= Space.WIDTH && x <= spaces[0].length * Space.WIDTH + Space.WIDTH) {
            if (y >= 20 && y <= spaces.length * Space.WIDTH + Space.WIDTH) {
               int col = x / Space.WIDTH - 1;
            //check for a successful piece drop
               if (player1 ? dropPiece(col, P1_COLOR) : dropPiece(col, P2_COLOR)) {
                                 
                  if (player1 && checkWin(P1_COLOR) == true) {
                     statusMessage = "Red Wins!";
                     System.out.println("Red");
                     redWins++;
                     playing = false;
                  }
                  else if (!player1 && checkWin(P2_COLOR) == true) {
                     statusMessage = "Yellow Wins!";
                     System.out.println("Yellow");
                     yellowWins++;
                     playing = false;
                  }
                  else {
                     player1 = !player1;
                     if (player1)
                        statusMessage = "Red's Turn";
                     else
                        statusMessage = "Yellow's Turn";
                  }
               }
            }
         }
      }
      
   //reset button clicked
      if (x >= reset.X && x <= reset.X + reset.WIDTH && y >= reset.Y && y <= reset.Y + reset.HEIGHT) {
         resetGame();
      }
      repaint();
   }
   @Override
   public void mousePressed(MouseEvent e) {}
   @Override
   public void mouseReleased(MouseEvent e) {}
   @Override
   public void mouseEntered(MouseEvent e) {}
   @Override
   public void mouseExited(MouseEvent e) {}
/**
* The ResetButton inner class maintains data and drawing instructions for the FourInARow's reset button
*/
   private class ResetButton {
      public static final int X = 700, Y = 400, WIDTH = 150, HEIGHT = 50;
   /**
   * Draw the reset button
   * @param g the graphics object used for drawing
   */
      public void draw(Graphics g) {
         Graphics2D g2 = (Graphics2D) g;
      //draw the box
         g2.setColor(Color.DARK_GRAY);
         g2.fillRect(X, Y, WIDTH, HEIGHT);
      //draw the border
         g2.setColor(Color.BLACK);
         g2.setStroke(new BasicStroke(3));
         g2.drawRect(X, Y, WIDTH, HEIGHT);
      //add the text
         g2.setColor(Color.WHITE);
         g2.setFont(new Font("Arial Black", Font.PLAIN, 20));
         g.drawString("Reset Game", X + 10, Y + 30);
      //print number of wins for each player
         g.drawString("Yellow Wins: " + yellowWins, X, 200);
         g.drawString("Red Wins: " + redWins, X, 230);
      }
   }
}