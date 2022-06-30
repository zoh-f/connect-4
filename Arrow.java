import java.awt.*;
/**
* The Arrow class is used to draw downward pointing arrows on the top of each column in the 4
in a Row game
*/
public class Arrow extends Polygon {
   private int x; //the x-coordinate of the upper-left corner of the arrow
   private final int y = 20; //the y-coordinate of the upper-left corner of the arrow (fixed vertical position)
   private final int WIDTH = 20, HEIGHT = 25; //the dimensions of the "box" portion of the arrow
   private final Color color; //the color of the arrow
   private static int arrowsMade;
/**
* Creates an arrow with a randomly filled color. Sets the coordinates of all points in the
* arrow based on the incoming x coordinate and the fixed y coordinate
* @param x the upper left corner of the "box" portion of the arrow
*/

   public static void setArrows(int x)
   {
      arrowsMade += x;
   }
   
   public Arrow(int x){
      this.x = x;
      this.setArrows(20);
   //create the shape
      addPoint(x, y); //upper left of "box" portion
      addPoint(x + WIDTH, y); //upper right of "box" portion
      addPoint(x + WIDTH, y + HEIGHT); //lower right of "box" portion
      addPoint(x + WIDTH + WIDTH / 2, y + HEIGHT); //right point of triangle
      addPoint(x + WIDTH / 2, y + HEIGHT + HEIGHT / 2 + 5); //bottom point of triangle
      addPoint(x - WIDTH / 2, y + HEIGHT); //left point of triangle
      addPoint(x, y + HEIGHT); //lower left of "box" portion
   //Set arrow to be filled with a random color
      //color = new Color((int) (Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
      color = new Color((int)(arrowsMade/3), (int)(arrowsMade/3), (int)(arrowsMade*1.5));
   
   }
/**
* Draws the arrow onto the screen
* @param g the Graphics object used for drawing
*/
   public void draw(Graphics g){
   //draw a solid colored arrow
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(color);
      g2.fillPolygon(this);
   //draw a black border around the arrow
      g2.setStroke(new BasicStroke(3));
      g2.setColor(Color.BLACK);
      g2.drawPolygon(this);
   }
}