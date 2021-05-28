import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Entity / character class
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Entity extends Actor {
    // health + dmg settings
    protected float atkDmg, health, maxHealth; 
    
    // move settings
    protected float movespeed;
    protected int dir; // 1 for left, 0 for right (direction)
    protected Vector2 pos;
    
    // image + animation settings
    protected String imgpath; // subclass
    protected int frame = 0;
    protected String state, prevState;
    protected ArrayList<GreenfootImage>[] idleAnim, runAnim;
    
    // references
    protected Room room;
    
    /*
     * Entity constructors
     */
    public Entity(Room room, float atkDmg, float health, float movespeed, Vector2 pos) {
        this.room = room;
        this.atkDmg = atkDmg;
        this.health = health;
        this.movespeed = movespeed;
        
        // initialize animations and related info
        state = "idle";
        prevState = state;
        
        this.pos = pos.get();
    } 
    
    /*
     * General animation
     */
    protected void animate() { 
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
     * Update pos according to vector
     */
    protected void updatePos() {
        setLocation(pos.getX(), pos.getY());
    }
    
    /*
     * Move functions
     */
    protected void move(Vector2 v) {
        this.pos = Vector2.add(this.pos, v); 
    }
    
    protected void move(float x, float y) {
        moveX(x);
        moveY(y);
    } 
     
    protected void moveX(float x) {
        this.pos.setX(pos.getX() + x);
    }
    
    protected void moveY(float y) {
        this.pos.setY(pos.getY() + y);
    }
    
    /*
     * Initialize animations (overload in subclass)
     */
    protected void initIdleAnim(String file, int frameCnt) {
        idleAnim = new ArrayList[2];
        idleAnim[0] = new ArrayList<GreenfootImage>(); // face right
        idleAnim[1] = new ArrayList<GreenfootImage>(); // face left
        for (int f=0;f<frameCnt;f++) { // go through all frames 
            
            GreenfootImage img = new GreenfootImage(imgpath+file+f+".png");
            img.scale(img.getWidth()*2, img.getHeight()*2);
            idleAnim[0].add(new GreenfootImage(img)); // facing right
            img.mirrorHorizontally();
            idleAnim[1].add(img); // facing left
        }
    }
    
    protected void initRunAnim(String file, int frameCnt) {
        runAnim = new ArrayList[2];
        runAnim[0] = new ArrayList<GreenfootImage>(); // face right
        runAnim[1] = new ArrayList<GreenfootImage>(); // face left
        for (int f=0;f<frameCnt;f++) { // go through all frames
            GreenfootImage img = new GreenfootImage(imgpath+file+f+".png");
            img.scale(img.getWidth()*2, img.getHeight()*2);
            runAnim[0].add(new GreenfootImage(img)); // facing right 
            img.mirrorHorizontally(); // facing left
            runAnim[1].add(img);
        }
    }
    
    /*
     * Getters & Setters
     */
    public float getAtkDmg() { return this.atkDmg; }
    public float getMaxHealth() { return this.maxHealth; }
    public float getHealth() { return this.health; }
    public float getSpeed() { return this.movespeed; }
    public int getDir() { return this.dir; }
    
    public void setAtkDmg(float dmg) { this.atkDmg = dmg; }
    public void setHealth(float hp) { this.health = hp; }
    public void setSpeed(float spd) { this.movespeed = spd; }
    
    /*
     * Help transfer player stats from one room to another
     */ 
    public void setStats(Player p) {
        // copy of stats from player p to this
        this.atkDmg = p.getAtkDmg();
        this.health = p.getHealth();
        this.movespeed = p.getSpeed();
        this.dir = p.getDir(); 
    } 
}
