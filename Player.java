import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Player script
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Player extends Actor { 
    private float atkDmg, health;
    private int movespeed;
    private int dir; // 1 for left, 0 for right (direction)

    private String imgpath = "player/"; 
    private int frame = 0;
    private String state, prevState;
    private ArrayList<GreenfootImage>[] idleAnim, runAnim;
    
    /*
     * Player constructors
     */
    public Player() {
        state = "idle";
        prevState = state;
        movespeed = 2;
        
        initIdleAnim();
        initRunAnim();
    }
    
    public void act() {
        move();
        animate();
        
    }    
    
    /*
     * Player movement + key handling
     */
    public void move() {
        boolean up = Greenfoot.isKeyDown("w");
        boolean down = Greenfoot.isKeyDown("s");
        boolean left = Greenfoot.isKeyDown("a");
        boolean right = Greenfoot.isKeyDown("d");
        if (up) {
            setLocation(getX(), getY() - movespeed);
        }
        if (down) {
            setLocation(getX(), getY() + movespeed);
        }
        if (left) {
            setLocation(getX() - movespeed, getY());
            dir = 1;
        }
        if (right) {
            setLocation(getX() + movespeed, getY());
            dir = 0;
        } 
        
        if (up || down || left || right) {
            state = "run";
        } else {
            state = "idle";
        }
    }
    
    private void animate() { 
        if (state != prevState) {
            frame = 0;
        }
        if (state=="idle") {
            int fr = 60 / 4; // framerate
            frame %= fr * idleAnim[dir].size();
            setImage(idleAnim[dir].get(frame / fr));
        } else if (state=="run") {
            int fr = 60 / 12; // framerate
            frame %= fr * runAnim[dir].size();
            setImage(runAnim[dir].get(frame / fr));
        }
        prevState = state;
        frame++;
    }
    
    /*
     * Initialize player animations
     */
    private void initIdleAnim() {
        idleAnim = new ArrayList[2];
        idleAnim[0] = new ArrayList<GreenfootImage>(); // face right
        idleAnim[1] = new ArrayList<GreenfootImage>(); // face left
        for (int f=0;f<=3;f++) { // go through all 4 frames
            GreenfootImage img = new GreenfootImage(imgpath+"knight_m_idle_anim_f"+f+".png");
            img.scale(img.getWidth()*2, img.getHeight()*2);
            idleAnim[0].add(new GreenfootImage(img)); // create copy of image
            img.mirrorHorizontally();
            idleAnim[1].add(img);
        }
    }
    
    private void initRunAnim() {
        runAnim = new ArrayList[2];
        runAnim[0] = new ArrayList<GreenfootImage>(); // face right
        runAnim[1] = new ArrayList<GreenfootImage>(); // face left
        for (int f=0;f<=3;f++) { // go through all 4 frames
            GreenfootImage img = new GreenfootImage(imgpath+"knight_m_run_anim_f"+f+".png");
            img.scale(img.getWidth()*2, img.getHeight()*2);
            runAnim[0].add(new GreenfootImage(img)); // create copy of image
            img.mirrorHorizontally();
            runAnim[1].add(img);
        }
    }
}
