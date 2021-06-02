
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Boss room that ends the game
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
        
        timer = -1;
    }
    
    public void act() {
        if (boss.isDead()) {
            if (timer == -1) {
                timer = 2f;
            } else {
                timer -= 1f / Utils.FPS;
                timer = Math.max(timer, 0);
                if (!boss.getRoom().getPlayer().isDead()) {
                    if (timer == 0) {
                        Greenfoot.setWorld(new WinWorld());
                    }
                }
            }
        }
        
    }
}
