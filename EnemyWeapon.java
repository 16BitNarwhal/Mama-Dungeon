import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyWeapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyWeapon extends Weapon { 
    
    public EnemyWeapon(Enemy user, Player player) {
        this(user, 1, 1, 1, player);
    }
    
    public EnemyWeapon(Enemy user, float atkDmg, float range, float dist, Player player) {
        super(user, atkDmg, range, dist);
        
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
