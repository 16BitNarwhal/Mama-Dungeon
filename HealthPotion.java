import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Health Potion (gives health) class
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class HealthPotion extends Item {
    
    private float addedHp;
    
    public HealthPotion() {
        super();
        initAnim("flask_red", 1);
        addedHp = 5f;
    }
    
    protected void onCollect(Player player) {
        super.onCollect(player);
        float hp = Math.min(player.getHealth() + addedHp, 100f);
        // System.out.println("Set " + hp + " hp to player"); // debug
        player.setHealth(hp);
        Player.healthPots++;
    }
    
}
