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
        setPaintOrder(Enemy.class, Player.class, MoveHelp.class, AtkHelp.class);
 
        for (int i=0;i<5;i++) {
            Zombie zombie = new Zombie(this, new Vector2(100,100));
            enemies.add(zombie);
            addObject(zombie, 0, 0); 
        }
        
    }
    
}
