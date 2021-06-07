import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Death screen image
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class DeathScreen extends GUI {
    
    private int t; // transparency
    private Room room;
    private boolean finished;
    private GreenfootSound loseSound = new GreenfootSound("mixkit-player-losing-or-failing-2042.wav");
    
    public DeathScreen(Room room) {
        GreenfootImage image = new GreenfootImage("DeathScreen.png"); 
        setImage(image);
        
        this.room = room;
        finished = false;
        t = 0;
        
        room.themeSong.stop();
        loseSound.play();
    }
    
    public void act() {
        if (getY() > 200)
            setLocation(getX(), getY()-3);
        if (t < 255) {
            t += 3;
            t = Math.min(t, 255);
            getImage().setTransparency(t);
        } else if (!finished) {
            room.addObject(new ExitButton(), Utils.worldWidth/2, 350);
            finished = true;
        }
        
    }
    
}