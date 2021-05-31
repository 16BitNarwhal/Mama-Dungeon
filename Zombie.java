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
    public Zombie(Room room, Vector2 pos) {
        // room | atk|hp|spd| pos |dst1 |dst2|detect | wait
        this(room, 10, 20, 1.5f, pos, 40,   50,   150,  1.7f);
    }
    
    public Zombie(Room room, float atkDmg, float health, float movespeed, Vector2 pos, 
                    float distClose, float distFar, float detectRange, float waitTime) {
        super(room, atkDmg, health, movespeed, pos, distClose, distFar, detectRange, waitTime);
        
        this.atkType = "melee";
        this.imgpath += "zombie/";
        initIdleAnim("zombie_idle_anim_f", 4);
        initRunAnim("zombie_run_anim_f", 4);
    }
    
}
