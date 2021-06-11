import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * When player wins, this world play like an 'animation'
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class WinWorld extends World {
 
    private float timers;
    private GreenfootSound winSong = new GreenfootSound("mixkit-video-game-win-2016.wav");
    
    public WinWorld() {     
        super(Utils.worldWidth, Utils.worldHeight, 1);
        addObject(new WinPlayer(), 500, 350);
        
        winSong.play();
        
        setPaintOrder(Text.class);
    }
}
