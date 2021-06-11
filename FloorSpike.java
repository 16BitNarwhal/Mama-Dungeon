import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Floorspike that damages player when touched
 * collision detection - https://www.greenfoot.org/topics/60742/0
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class FloorSpike extends Environment {
    
    private ArrayList<Vector2> allPos;
    private Vector2 pos;
    
    private boolean canHurt; // spikes are out / not out
    private boolean recentlyHurt; // spikes already hit player (per cycle)
    
    private int frame;
    private ArrayList<GreenfootImage> anim; 
    
    /*
     * Floor spike constructor
     */
    public FloorSpike() {
        initAnim("dungeon/floor_spikes_anim_f", 5);
        
        int x1 = Room.getLeftBound() + Utils.tileSize;
        int x2 = Room.getRightBound() - Utils.tileSize;
        int y1 = Room.getUpBound() + Utils.tileSize;
        int y2 = Room.getDownBound() - Utils.tileSize;
        allPos = new ArrayList<Vector2>();
        for (int x = x1; x <= x2; x += Utils.tileSize) {
            for (int y = y1; y <= y2; y += Utils.tileSize) {
                allPos.add(new Vector2(x, y)); 
            }
        }
        int idx = Utils.random(allPos.size()-1);
        pos = new Vector2(allPos.get(idx).getX(), allPos.get(idx).getY());
        
        frame = pos.getX()/5;
    }
    
    public void act() {
        setLocation(pos.getX(), pos.getY());
        animate();
        action();
    }
    
    /*
     * Check player collision and attack player if touch
     */
    private void action() {
        if (recentlyHurt) return;
        
        List<Player> lst = getCollidingObjects(Player.class);
        if (lst.size() > 0) { 
            Player player = lst.get(0);
            if (!player.isDead() && canHurt) {
                player.loseHealth(5f);
                recentlyHurt = true;
            }
        }
        
    }
    
    /*
     * Animate method
     */
    private void animate() {
        // debug fr
        int fr = 60 / 2; // framerate 
        frame %= fr * anim.size();
        setImage(anim.get(frame / fr));
        if (frame / fr >= 3) {
            canHurt = true;
        } else {
            canHurt = false;
            recentlyHurt = false;
        }
        frame++;
    }
    
    /*
     * Initialize animations
     */
    private void initAnim(String file, int frameCnt) {
        anim = new ArrayList<GreenfootImage>();
        for (int f=0;f<frameCnt;f++) { // go through all frames 
            GreenfootImage img = new GreenfootImage(file+f+".png");
            img.scale(img.getWidth()*2, img.getHeight()*2); 
            anim.add(img);
        }
    }

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
