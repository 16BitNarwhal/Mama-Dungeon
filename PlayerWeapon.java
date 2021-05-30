import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerWeapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerWeapon extends Weapon { 
    
    public PlayerWeapon(Player user, float atkDmg, float range, float dist) {
        super(user, atkDmg, range, dist);
        
    }
    
    public void act() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        updatePos(new Vector2(mouse.getX(), mouse.getY()));
        
    }    
     
}
