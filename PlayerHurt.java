import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Red screen whenever player is hurt
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class PlayerHurt extends GUI {
    private float initTime, timer;
    
    public PlayerHurt() {
        setImage("player/player hurt.png");
        initTime = 0.5f;
        timer = initTime;
    }
    
    public void act() {
        timer -= 1.0 / Utils.FPS;
        int t = Math.round(200f * timer/initTime);
        getImage().setTransparency(t);
        
        if (timer < 0) {
            getWorld().removeObject(this);
        }
    }
}
