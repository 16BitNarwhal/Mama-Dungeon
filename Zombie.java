import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Zombie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zombie extends Enemy {
    
    /*
     * Zombie constructors
     */
    public Zombie(Room room) {
        this(room, 1, 1, 1, new Vector2(), 50, 70, 100);
    }
    
    public Zombie(Room room, float atkDmg, float health, int movespeed, Vector2 pos, 
                    float distClose, float distFar, float detectRange) {
        super(room, atkDmg, health, movespeed, pos, distClose, distFar, detectRange);
        this.imgpath += "zombie/";
        initIdleAnim("zombie_idle_anim_f", 4);
        initRunAnim("zombie_run_anim_f", 4);
    }
    
}
