import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Weapons for entities
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Weapon extends Actor { 
    
    private Entity user;
    private Vector2 offset, target;
    private float atkDmg; // adds to atkDmg of entity
    private float atkRange, posDist; // posDist used to calc pos
    
    // image + animation settings
    private String imgpath; // subclass
    private int frame = 0;
    protected int dir; // 1 for left, 0 for right (direction)
    private String state, prevState;
    private ArrayList<GreenfootImage>[] idleAnim, atkAnim;
    
    /*
     * Weapon constructors
     */
    public Weapon(Entity user, float atkDmg, float range, float dist) {
        this.user = user;
        this.atkDmg = atkDmg;
        this.atkRange = range;
        this.posDist = dist;
        this.offset = new Vector2(0, -5);
        
        this.imgpath = "weapon/regular_sword/";
        this.state = "idle";
        initIdleAnim("weapon_regular_sword", 4);
        initAtkAnim("weapon_regular_sword", 4);
    }
    
    public void act() {
        animate();
        
        System.out.println(getX() + " " + getY());
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) 
            target = new Vector2(mouse.getX(), mouse.getY());
        updatePos(new Vector2(target.getX(), target.getY()));
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
    
    private void animate() {
        if (state=="idle") {
            int fr = 60 / 6; // framerate
            frame %= fr * idleAnim[dir].size();
            setImage(idleAnim[dir].get(frame / fr));
        } else if (state=="attack") {
            int fr = 60 / 6; // framerate
            frame %= fr * atkAnim[dir].size();
            setImage(atkAnim[dir].get(frame / fr));
        } 
        prevState = state;
        frame++;
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
     * Setters
     */
    protected void setOffset(Vector2 offset) { this.offset = offset; }
    
}
