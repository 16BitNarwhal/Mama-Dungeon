import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Win screen/text image
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class WinScreen extends GUI {
    
    private int t; // transparency 
    private boolean finished;
    
    public WinScreen() {
        GreenfootImage image = new GreenfootImage("WinScreen.png"); 
        setImage(image);
        
        finished = false;
        t = 0;
    }
    
    public void act() {
        if (getY() > 200)
            setLocation(getX(), getY()-3);
        if (t < 255) {
            t += 3;
            t = Math.min(t, 255);
            getImage().setTransparency(t);
        } else if (!finished) {
            getWorld().addObject(new ExitButton(), 600, 376);
            finished = true;
        }
        
    }
    
}