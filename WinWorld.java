import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * When player wins, this world will be an 'animation'
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class WinWorld extends World {
 
    private float timers;
    
    public WinWorld() {     
        super(Utils.worldWidth, Utils.worldHeight, 1);
        addObject(new WinPlayer(), 500, 350);
    }
    
    private void updateTime() {
        
    }
}
