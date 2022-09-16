import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Random;
/*
 *  The main window of the gui.
 *  @author Apurva Acharya
 */
public class Window extends JFrame implements ActionListener, MouseListener
{
    // gui components that are contained in this frame:
    private JPanel topPanel, bottomPanel;    // top and bottom panels in the main window
    private JLabel instructionLabel;        // a text label to tell the user what to do
    private JButton topButton;                // a PlayGame button that appears in the top panel
    private GridSquare [][] gridSquares;    // squares to appear in grid formation in the bottom panel
    private int rows,columns;  // the size of the grid
    private boolean gameActive; //variable that stores a boolean value based on if the game is running
    private Random rand; // variable used to generate a random number
    private int playerNum; // variable stores which player's turn it is
    /*
     *  constructor method takes as input how many rows and columns of gridsquares to create
     *  it then creates the panels, their subcomponents and puts them all together in the main frame
     *  it makes sure that action listeners are added to selectable items
     *  it makes sure that the gui will be visible
     */
    public Window()
        {
        rand= new Random();
        
        this.rows = 4;
        this.columns = 4;
        this.setSize(600,600);
        
        gameActive=false;
        
        // first create the panels
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
         
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(rows, columns));
        bottomPanel.setSize(500,500);
        
        // then create the components for each panel and add them to it
        
        // for the top panel:
        instructionLabel = new JLabel("Don't select neighbour squares! Click 'New Game' to begin.");
        topButton = new JButton("New Game");
        topButton.addActionListener(this);            // IMPORTANT! Without this, clicking the square does nothing.
        
        topPanel.add(instructionLabel);
        topPanel.add (topButton);
        
    
        // for the bottom panel:    
        // create the squares and add them to the grid
        gridSquares = new GridSquare[rows][columns];
        Border blackline= BorderFactory.createLineBorder(Color.lightGray,3);
        for ( int x = 0; x < columns; x ++)
        {
            for ( int y = 0; y < rows; y ++)
            {
                gridSquares[x][y] = new GridSquare(x, y);
                gridSquares[x][y].setSize(20, 20);
                gridSquares[x][y].setBorder(blackline); //setting border
                gridSquares[x][y].setColor();
                gridSquares[x][y].addMouseListener(this);        
                bottomPanel.add(gridSquares[x][y]);
            }
        }
        
        // add the top and bottom panels to the main frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(bottomPanel, BorderLayout.CENTER);       
        
        // housekeeping : behaviour
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    
    /*
     *  handles actions performed in the gui
     *  this method must be present to correctly implement the ActionListener interface
     */
    public void actionPerformed(ActionEvent aevt)
    {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();
                
        // if resetting the squares' colours is requested then do so
        if ( selected.equals(topButton) )
        {
            playerNum=rand.nextInt(2)+1; //generate a random number 1 or 2 if the button is pressed
            for ( int x = 0; x < columns; x ++)
            {
                for ( int y = 0; y < rows; y ++)
                {
                    gridSquares [x][y].setColor(); //set all the squares in the grid to white
                }    
                }
                    instructionLabel.setText("Player "+playerNum+"'s turn...");
                    gameActive=true; //start the game if the button is pressed
            }

}



    // Mouse Listener events
    public void mouseClicked(MouseEvent mevt)
    {
        // get the object that was selected in the gui
        Object selected = mevt.getSource();
        
        if (selected instanceof GridSquare)
        {
            if (gameActive==true)
            {
                GridSquare square = (GridSquare) selected;
                if (square.isColored()==false){ //is the square already colored
                            square.switchColor(playerNum); // if not change the color of the square according the playerNum
                            int x = square.getXcoord();
                            int y = square.getYcoord();
                            if (playerNum==1){ //switch players
                                playerNum=2;}
                            else {playerNum=1;}
                            instructionLabel.setText("Player "+playerNum+"'s turn...");
                            
                            int[] offSetX= {-1,-1,-1,0,0,1,1,1}; //x and y offset to check the neighbouring squares of any square
                            int[] offSetY= {-1,0,1,-1,1,-1,0,1};
                            for ( int l = 0; l < 8; l ++)
                            {
                                int nx= x + offSetX[l]; 
                                int ny= y + offSetY[l];
                                if (!(nx<0 | nx>3 | ny<0 | ny>3)){ //if the cell does not exist
                                    if (square.isRed()==true){ //player 2 wins
                                        if(gridSquares [nx][ny].getBackground()==Color.red){
                                            gameActive=false;
                                            instructionLabel.setText("Player 2 Wins! Click to play again...");
                                        }
                                    }
                                    if (square.isRed()==false){ //player 1 wins
                                        if(gridSquares [nx][ny].getBackground()==Color.blue){
                                            gameActive=false;
                                            instructionLabel.setText("Player 1 Wins! Click to play again...");
                                        }
                                    }    
                                }

                    }

                }

            }
        }
    }

    
    
    // not used but must be present to fulfil MouseListener contract
    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
}
