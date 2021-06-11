import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Healthbar for player in top left
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class PlayerHealthBar extends HealthBar {  
    
    /*
     * PlayerHealthBar constructors
     */
    public PlayerHealthBar(Entity e) {
        this(e, "bar");
    }
    
    public PlayerHealthBar(Entity e, String type) {
        super(e, type, 2, "player_hp_bar", "player_hp_bg");
    }
    
    public void act() {
        updateBar();
    }
}
