
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Room with enemies spawns in
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class EnemyRoom extends Room {

    /**
     * Construct EnemyRoom
     */
    public EnemyRoom(int rad, Room l, Room r, Room u, Room d) {
        super(rad, l, r, u, d);
            
        int numEnemies = Utils.random(3, 6);
        for (int i=0;i<numEnemies;i++) {
            Enemy e = Utils.newEnemy(this);
            enemies.add(e);
            addObject(e, 0, 0);
        } 
    }
    

    
}
