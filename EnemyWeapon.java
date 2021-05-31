import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemy Weapon (not in use for now)
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class EnemyWeapon extends Weapon { 
    
    public EnemyWeapon(Enemy user, Player player) {
        this(user, 1, 1, player);
    }
    
    public EnemyWeapon(Enemy user, float atkDmg, float dist, Player player) {
        super(user, atkDmg, dist);
        
    }
}
