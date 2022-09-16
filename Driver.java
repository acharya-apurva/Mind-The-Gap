import javax.swing.UIManager;
/*
 *  @author Apurva Acharya
 */
public class Driver
{
    public static void main(String[] args)
    {
        // create a new GUI window
        try
        {
           UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        }
        catch (Exception e)
        {
           e.printStackTrace();
        
        }
        Window demo = new Window();
    }
}