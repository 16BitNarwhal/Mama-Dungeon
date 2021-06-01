import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Weapons for entities
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Weapon extends Actor { 
    
    protected Entity user;
    protected Vector2 offset, target;
    private float atkDmg; // adds to atkDmg of entity
    private float posDist; // posDist used to calc pos
    
    // image + animation settings
    protected String imgpath; // subclass
    private int frame = 0;
    private int dir; // 1 for left, 0 for right (direction)
    protected String state, prevState;
    protected ArrayList<GreenfootImage>[] idleAnim, atkAnim;
    protected boolean hurt;
    
    /*
     * Weapon constructors
     */
    public Weapon(Entity user, float atkDmg, float dist) {
        this.user = user;
        this.atkDmg = atkDmg; 
        this.posDist = dist;
        this.offset = new Vector2(0, 10);
        
        this.state = "idle";
        this.hurt = false;
    }
    
    public void act() {
        animate();
        
    }

    protected void handleAttack(Class enemyClass) {
        if (this.state == "attack" && this.hurt) {
            List<Entity> enemies = getIntersectingObjects(enemyClass);
            
            for (Entity e : enemies) {
                float dmg = Utils.random(atkDmg*1/2, atkDmg*3/2);
                e.loseHealth(atkDmg + user.getAtkDmg());
            }
        }
    }
    
    protected void updatePos(Vector2 target) {
        Vector2 tempOffset = offset.get();
        if (target.getX() < getX()) {
            tempOffset.setX(-tempOffset.getX());
        }
        Vector2 pos = Vector2.add(user.getPos(), tempOffset);
        Vector2 move = Vector2.sub(target, pos);
        
        if (move.mag() > posDist) {
            move = move.normalize().mult(posDist);
        }
        if (move.getX() > 0) {
            dir = 0;
        } else if (move.getX() < 0) {
            dir = 1;
        }
        
        pos = Vector2.add(pos, move);
        setLocation(pos.getX(), pos.getY());
    }
    
    protected void animate() {
        if (state=="idle") {
            int fr = 60 / 6; // framerate
            frame %= fr * idleAnim[dir].size();
            setImage(idleAnim[dir].get(frame / fr));
            
            hurt = false;
        } else if (state=="attack") {
            int fr = 60 / 6; // framerate
            frame %= fr * atkAnim[dir].size();
            setImage(atkAnim[dir].get(frame / fr));
            
            hurt = willHurt(frame, fr);
        }
        prevState = state;
        frame++;
    }
    
    /*
     * Will set when attack hurts (for example downswing vs upswing)
     */
    protected boolean willHurt(int frame, int fr) {
        return true;
    }
    
    /*
     * Initialize animations 
     */
    protected void initIdleAnim(String file, int frameCnt) {
        idleAnim = new ArrayList[2];
        idleAnim[0] = new ArrayList<GreenfootImage>(); // face right
        idleAnim[1] = new ArrayList<GreenfootImage>(); // face left
        for (int f=0;f<frameCnt;f++) { // go through all frames 
            GreenfootImage img = new GreenfootImage(imgpath+file+f+".png");
            img.scale(img.getWidth()/5, img.getHeight()/5);
            idleAnim[0].add(new GreenfootImage(img)); // facing right
            img.mirrorHorizontally();
            idleAnim[1].add(img); // facing left
        }
    }
    
    protected void initAtkAnim(String file, int frameCnt) {
        atkAnim = new ArrayList[2];
        atkAnim[0] = new ArrayList<GreenfootImage>(); // face right
        atkAnim[1] = new ArrayList<GreenfootImage>(); // face left
        for (int f=0;f<frameCnt;f++) { // go through all frames
            GreenfootImage img = new GreenfootImage(imgpath+file+f+".png");
            img.scale(img.getWidth()/5, img.getHeight()/5);
            atkAnim[0].add(new GreenfootImage(img)); // facing right 
            img.mirrorHorizontally(); // facing left
            atkAnim[1].add(img);
        }
    } 
    
    /*
     * Getters & Setters
     */
    public boolean isAttacking() { return state=="attack"; }
    
    protected void setOffset(Vector2 offset) { this.offset = offset; }
    
}
