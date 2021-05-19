import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Create a door
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Door extends Environment {
    
    private Room target;
    private String type;
    
    
    public Door(Room target, String type) {
        this.target = target;
        this.type = type;
        GreenfootImage image = new GreenfootImage("null.png");
        if (type=="left") {
            image = new GreenfootImage(Tile.imgpath + "entrance_left.png");
        } else if (type=="right") {
           
        } else if (type=="top") {
            
        } else if (type=="bottom") {
            
        }
        image.scale(image.getWidth()*2, image.getHeight()*2);
        setImage(image);
    }
    
    public void act() { 
        Actor player = getOneIntersectingObject(Player.class);
        
        if (player != null) {
            Greenfoot.setWorld(target);
        }
    }
}
