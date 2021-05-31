import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Room that player spawns in
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class SpawnRoom extends Room {

    /**
     * Construct StartRoom
     */
    public SpawnRoom() {
        super();
        addObject(new MoveHelp(), 170, 280);
        addObject(new AtkHelp(), 470, 290);
        
    }
    
}
