import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Weapons for entities
 * collision detection: https://www.greenfoot.org/topics/60742/0
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
        if (state=="attack") attackSound(frame, 60 / 6);
    }

    protected void handleAttack(Class enemyClass) {
        if (this.state == "attack" && this.hurt) {
            List<Entity> enemies = getCollidingObjects(enemyClass);
            
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
     * Play attack sound (override in subclasses)
     */
    protected void attackSound(int frame, int fr) { }
    
    /*
     * Getters & Setters
     */
    public boolean isAttacking() { return state=="attack"; }
    
    protected void setOffset(Vector2 offset) { this.offset = offset; } 
    
    /*
     * Totally my code
     * just kidding - https://www.greenfoot.org/topics/60742/0
     */
    public <E extends Entity> List<E> getCollidingObjects(Class<E> cls){
        List<E> objects = getIntersectingObjects(cls);
        List<E> fObjects = new ArrayList(); //finalObjects list. this will contain any objects properly intersecting with this object.
        if(objects.size()>0){
            int x = getImage().getWidth();
            int y = getImage().getHeight();
            int d = (int) Math.ceil(Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
            double scaleFactor = x>y ? (double) d / (double) y : (double) d / (double) x; // this coupled with the previous 3 lines set up the math for scaling our image to prevent cropping
            GreenfootImage img = new GreenfootImage((int) Math.ceil(x*scaleFactor), (int) Math.ceil(y*scaleFactor)); //this is the objects image so we can scale it to a large enough size that rotation won't crop it.
            img.drawImage(getImage(), (img.getWidth()-getImage().getWidth())/2, (img.getHeight()-getImage().getHeight())/2); //puts our image into the center of the enlarged image for rotating
            img.rotate(getRotation()); //rotating the image to match direction
            boolean[][] bitmask = new boolean[img.getWidth()][img.getHeight()];//Not sure on the terminology, but "bitmask" appears to be how to refer to a 1/0 version of an image which is what this is, 1 (true) being opaque (or partially transparent), 0 (false) being fully transparent
            for(int col = 0; col < bitmask.length; col++){ //Cycles through every x value of image
                for(int row = 0; row < bitmask[0].length; row++){ //Cycles through every y value of image
                    bitmask[col][row] = (img.getColorAt(col, row).getAlpha()>0); //alpha 0 is transparent, this sets the bitmask pixel to the proper transparency t/f state
                }
            }
            for(E a : objects){ //Cycles through every "intersecting" object so we can check if it really is intersecting
                if(isCollidedWith(a, bitmask)){
                    fObjects.add(a);
                }
            }
        }
        return fObjects;
    }

    public boolean isCollidedWith(Actor atr, boolean[][] bitmask){
        int x2 = atr.getImage().getWidth();
        int y2 = atr.getImage().getHeight();
        int d2 = (int) Math.ceil(Math.sqrt(Math.pow(x2,2)+Math.pow(y2,2)));
        double scaleFactor2 = x2>y2 ? (double) d2 / (double) y2 : (double) d2 / (double) x2; // this coupled with the previous 3 lines set up the math for scaling our image to prevent cropping
        GreenfootImage img2 = new GreenfootImage((int) Math.ceil(x2*scaleFactor2), (int) Math.ceil(y2*scaleFactor2)); //this is the objects image so we can scale it to a large enough size that rotation won't crop it.
        img2.drawImage(atr.getImage(), (img2.getWidth()-atr.getImage().getWidth())/2, (img2.getHeight()-atr.getImage().getHeight())/2); //puts our image into the center of the enlarged image for rotating
        img2.rotate(atr.getRotation()); //rotating the image to match direction
        boolean[][] bitmask2 = new boolean[img2.getWidth()][img2.getHeight()];//new bitmask for the image of colliding object, 1 (true) being opaque (or partially transparent), 0 (false) being fully transparent
        for(int col = 0; col < bitmask.length && col < img2.getWidth(); col++){ //Cycles through every x value of image
            for(int row = 0; row < bitmask[0].length && row < img2.getHeight(); row++){ //Cycles through every y value of image
                bitmask2[col][row] = img2.getColorAt(col, row).getAlpha()>0; //alpha 0 is transparent, this sets the bitmask pixel to the proper transparency t/f state
            }
        }
         
        int xOffset = 0; //the col of THIS to start with if - it changes start of atr
        int yOffset = 0; //the row of THIS to start with, if - it changes start of atr
        xOffset += 0-(getX()-atr.getX()); //position offset
        yOffset += 0-(getY()-atr.getY()); //position offset
        xOffset += (int) Math.ceil((double) ((bitmask.length-img2.getWidth())/2.0)); //image offset - goes right 1 pixel for each pixel/2 rounded up
        yOffset += (int) Math.ceil((double) ((bitmask[0].length-img2.getHeight())/2.0)); //image offset - goes right 1 pixel for each pixel/2 rounded up
        int cLength = (xOffset>0 ? (bitmask.length-xOffset > bitmask2.length ? bitmask2.length : bitmask.length-xOffset) : (bitmask2.length+xOffset > bitmask.length ? bitmask.length : bitmask2.length+xOffset)); //amount of cols to process
        int rLength = (yOffset>0 ? (bitmask[0].length-yOffset > bitmask2[0].length ? bitmask2[0].length : bitmask[0].length-yOffset) : (bitmask2[0].length+yOffset > bitmask[0].length ? bitmask[0].length : bitmask2[0].length+yOffset)); //amount of rows to process
        int startX = xOffset>0 ? xOffset : 0;
        int startX2 = xOffset>0 ? 0 : 0-xOffset;
        for(int xChange = 0; xChange < cLength; xChange++){
            int startY = yOffset>0 ? yOffset : 0;
            int startY2 = yOffset>0 ? 0 : 0-yOffset;
            for(int yChange = 0; yChange < rLength; yChange++){
                if(bitmask[xChange+startX][yChange+startY] && bitmask2[xChange+startX2][yChange+startY2]){
                    return true;
                }
            }
        }
         
        return false;
    }
    
}
