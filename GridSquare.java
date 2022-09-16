import java.awt.*;
import javax.swing.*;


/*
 *  A GUI component
 *
 *  Gridsquare allows to set color, switch color, check color and check if the Panel is red
 *
 *  @author Apurva Acharya
 */
public class GridSquare extends JPanel
{
    private int xcoord, ycoord;  // location in the grid
    // constructor takes the x and y coordinates of this square
    public GridSquare(int xcoord, int ycoord)
    {
    super();
    this.setSize(50,50);
    this.xcoord = xcoord;
    this.ycoord = ycoord;
    }
    
    //changes the color of the panel to white
    public void setColor()
    {
        this.setBackground(Color.white);
    }
    
    // if player 1 is passed change the panel to red and if player 2 is passed change the panel to blue
    public void switchColor(int player)
    {
        if (player==1){
            this.setBackground(Color.red);}
        else{
            this.setBackground(Color.blue);}
    }
    
    //check if the panel is colored with red or blue
    public boolean isColored(){
        if ((getBackground()==Color.red)|(getBackground()==Color.blue)){
            return true;
        }
        else{ return false;}
    }
    
    // check if the panel is red
    public boolean isRed(){
        if (getBackground()==Color.red){return true;}
        else{return false;}
    }
    
    // simple setters and getters for the x and y coordinates
    public void setXcoord(int value)    { xcoord = value; }
    public void setYcoord(int value)    { ycoord = value; }
    public int getXcoord()              { return xcoord; }
    public int getYcoord()              { return ycoord; }
}
