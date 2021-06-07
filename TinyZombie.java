import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tiny Zombie Enemy
 * 
 * @author Eric Zhang 
 * @version (a version number or a date)
 */
public class TinyZombie extends Enemy {
    
    /*
     * TinyZombie  constructors
     */
    public TinyZombie (Room room) {
        this(room, new Vector2(Utils.random(room.getLeftBound(), room.getRightBound()),
                Utils.random(room.getUpBound(), room.getDownBound())));
    }
    
    public TinyZombie (Room room, Vector2 pos) {
        // room | atk|hp|spd| pos |dst1 |dst2|detect | wait
        this(room, 2f, 10, 2.5f, pos, 30,  40,  150,  1f);
    }
    
    public TinyZombie (Room room, float atkDmg, float health, float movespeed, Vector2 pos, 
                    float distClose, float distFar, float detectRange, float waitTime) {
        super(room, atkDmg, health, movespeed, pos, distClose, distFar, detectRange, waitTime);
        
        this.atkType = "melee";
        this.imgpath += "tiny zombie/";
        initIdleAnim("tiny_zombie_idle_anim_f", 4);
        initRunAnim("tiny_zombie_run_anim_f", 4);
    }
    
}
