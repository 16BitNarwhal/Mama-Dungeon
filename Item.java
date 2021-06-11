import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Item that can be picked up by player and provides some kind of stat
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Item extends Actor {
    
    private int frame = 0;
    private String imgpath;
    protected ArrayList<GreenfootImage> anim;
    
    protected GreenfootSound collectSound;
    
    public Item() {
        imgpath = "dungeon/";
        collectSound = new GreenfootSound("mixkit-arcade-game-jump-coin-216.wav");
    }
    
    public void act() {        
        Player player = (Player) getOneIntersectingObject(Player.class);
        if (player != null) {
            onCollect(player);
            getWorld().removeObject(this);
        }
        animate();
    } 
    
    protected void onCollect(Player player) {
        collectSound.play();
    }
    
    /*
     * General animation
     */
    protected void animate() {
        int fr = 60 / 4; // framerate
        frame %= fr * anim.size();
        setImage(anim.get(frame / fr));
        frame++;
    }
    
    /*
     * Initialize animation
     */
    protected void initAnim(String file, int frameCnt) {
        anim = new ArrayList<GreenfootImage>();
        for (int f=0;f<frameCnt;f++) { // go through all frames 
            GreenfootImage img = new GreenfootImage(imgpath+file+f+".png");
            img.scale(img.getWidth()*2, img.getHeight()*2);
            anim.add(img);
        }
    }
}
