import java.awt.*;
/**
* The Space class models one cell/space in the game of Connect 4
* Spaces are either blank or occupied. The color of the center
* of the space determines which player occupies it
*/
public class Space {
   private int x, y; //coordinates of the upper-left corner of the space
   public static final int WIDTH = 75; //the width of the space (same as height)
   private Color color; //the current color of the space
   private static Color blank = FourInARow.FIELD_COLOR; //the color used when the space is blank
   private boolean occupied; //indicates whether the space is occupied
/**
* Creates a blank space
* @param x the x-coordinate of the upper-left corner
* @param y the y-coordinate of the upper left corner
*/
   public Space(int x, int y){
      this.x = x;
      this.y = y;
      color = blank;
   }
/**
* Accepts a player's "piece" into the space
* @param color the color of the player's token
*/
   public void accept(Color color){
      this.color = color;
      occupied = true;
   }
/**
*
* @return true if the space is occupied, false otherwise
*/
   public boolean occupied(){
      return occupied;
   }
/**
*
* @return the color in the center of the space
*/
   public Color getColor(){
      return color;
   }
/**
* Sets the space to be blank and unoccupied
*/
   public void clear(){
      color = blank;
      occupied = false;
   }
/**
* Draws the space as a blue square with a black border and a round "cutout" with the color of
the piece
* @param g the graphics object used for drawing
*/
   public void draw(Graphics g){
   //draw the blue square
      g.setColor(Color.BLUE);
      g.fillRect(x, y, WIDTH, WIDTH);
   //draw the black border around the square
      Graphics2D g2 = (Graphics2D)(g);
      g2.setStroke(new BasicStroke(2));
      g2.setColor(Color.BLACK);
      g2.drawRect(x, y, WIDTH, WIDTH);
   //draw the colored round cutout
      g.setColor(color);
      g.fillOval(x + 5, y + 5, WIDTH - 10, WIDTH - 10);
   }
}