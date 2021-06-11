import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Skelet: slow, low health, very strong dmg
 * 
 * @author Eric Zhang 
 * @version (a version number or a date)
 */
public class Skelet extends Enemy {
    
    /*
     * Skelet constructors
     */
    public Skelet(Room room) {
        this(room, new Vector2(Utils.random(room.getLeftBound(), room.getRightBound()),
                Utils.random(room.getUpBound(), room.getDownBound())));
    }
    
    public Skelet(Room room, Vector2 pos) {
        // room | atk|hp|spd| pos |dst1 |dst2|detect | wait
        this(room, 25f, 10f, 0.8f, pos, 40,  50,  200,  2f);
    }
    
    public Skelet(Room room, float atkDmg, float health, float movespeed, Vector2 pos, 
                    float distClose, float distFar, float detectRange, float waitTime) {
        super(room, atkDmg, health, movespeed, pos, distClose, distFar, detectRange, waitTime);
        
        this.atkType = "melee";
        this.imgpath += "skelet/";
        initIdleAnim("skelet_idle_anim_f", 4);
        initRunAnim("skelet_run_anim_f", 4);
    }
    
}
