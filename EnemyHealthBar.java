import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Healthbar for enemy, follows enemy
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class EnemyHealthBar extends HealthBar {
    
    private Vector2 offset;
    
    /*
     * EnemyHealthBar constructors
     */
    public EnemyHealthBar(Entity e) {
        this(e, "bar");
    }
    
    public EnemyHealthBar(Entity e, String type) {
        // too lazy to duplicate the images for different names
        super(e, type, 1, "player_hp_bar", "player_hp_bg");
        offset = new Vector2(0, -20);
    }
    
    public void act() {
        updateBar();
        moveBar();
    }
    
    private void moveBar() {
        Vector2 pos = Vector2.add(entity.getPos(), offset);
        setLocation(pos.getX(), pos.getY());
    }
}
