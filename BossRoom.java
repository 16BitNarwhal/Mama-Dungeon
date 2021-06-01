import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Boss room that ends the game
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class BossRoom extends Room {

    /**
     * Constructor for objects of class BossRoom.
     */
    public BossRoom(int rad, Room left, Room right, Room up, Room down) {
        super(rad, left, right, up, down);
        
        // debug boss room
        addObject(new Text("BOSSSS", 50, Color.RED, Color.BLACK), Utils.worldWidth/2, Utils.worldHeight/2);
        
        addObject(new ZombieBoss(this, new Vector2(Utils.worldWidth/2, Utils.worldHeight/2)), 0, 0);
    }
}
