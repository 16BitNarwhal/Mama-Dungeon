import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Non-controllable player for Win World animation 
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class WinPlayer extends Player {
    
    private float time = 0f;
    private boolean finished;
    
    public WinPlayer() {
        super(null);
        pos = new Vector2(590, 343);
        finished = false;
    }
    
    public void act() {        
        actions();   
        animate();
        updateTime();
        updatePos();
    }
    
    private void actions() {
        if (time < 4.2f) {
            dir = 1;
            state = "run";
            moveX(-1.5f);
        } else if (!finished) {
            state = "idle";
            getWorld().addObject(new WinScreen(), Utils.worldWidth/2, Utils.worldHeight);
            finished = true;
        }
    }
    
    private void updateTime() {
        time += 1f / Utils.FPS;
    }
}
