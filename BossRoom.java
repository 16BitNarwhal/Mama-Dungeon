
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Boss room that ends the game when boss is defeated
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class BossRoom extends Room {
    
    private Boss boss;
    private float timer;
    
    /**
     * Constructor for objects of class BossRoom.
     */
    public BossRoom(int rad, Room left, Room right, Room up, Room down) {
        super(rad, left, right, up, down);
        
        boss = new ZombieBoss(this, new Vector2(Utils.worldWidth/2, Utils.worldHeight/2));
        addObject(boss, 0, 0);
        
        timer = 2f;
    }
    
    public void act() {
        if (boss.isDead()) { 
            timer -= 1f / Utils.FPS; // update time
            timer = Math.max(timer, 0); 
            if (timer == 0) {
                themeSong.stop();
                Greenfoot.setWorld(new WinWorld());
            
            }
        }
        
    }
    
    /*
     * Getter
     */
    public boolean deadBoss() { return this.boss.isDead(); }
}
