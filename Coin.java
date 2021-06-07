import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Coin class (increases player damage)
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Coin extends Item {
    
    float addedDmg;
    
    public Coin() {
        super();
        initAnim("coin_anim_f", 4);
        addedDmg = 0.2f;
    }
    
    protected void onCollect(Player player) {
        super.onCollect(player);
        float dmg = Math.min(player.getAtkDmg() + addedDmg, 100f);
        System.out.println("Set " + dmg + " dmg to player"); // debug
        player.setAtkDmg(dmg);
    }
    
}
