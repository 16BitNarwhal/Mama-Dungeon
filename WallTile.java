import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Wall tile
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class WallTile extends Tile {   
    // set image based on type of wall
    public WallTile(String type) {
        // default wall type
        GreenfootImage image = new GreenfootImage("null.png");
        
        if (type=="top") {
            image = new GreenfootImage(imgpath + "wall_top_mid.png");
        } else if (type=="bottom") {
            image = new GreenfootImage(imgpath + "wall_top_mid.png");
        } else if (type=="left") {
            image = new GreenfootImage(imgpath + "wall_side_mid_right.png");
        } else if (type=="right") {
            image = new GreenfootImage(imgpath + "wall_side_mid_left.png");
        } else if (type=="top left") {
            image = new GreenfootImage(imgpath + "wall_side_top_left.png");
        } else if (type=="top right") {
            image = new GreenfootImage(imgpath + "wall_side_top_right.png");
        } else if (type=="bottom left") {
            image = new GreenfootImage(imgpath + "wall_inner_corner_l_top_left.png");
        } else if (type=="bottom right") {
            image = new GreenfootImage(imgpath + "wall_inner_corner_l_top_rigth.png");
        }
        
        image.scale(image.getWidth()*2, image.getHeight()*2);
        setImage(image);
    }
    
    // no given type of wall so use the 'general' type
    public WallTile() {
        GreenfootImage image = new GreenfootImage(imgpath + "/wall_mid.png");
        image.scale(image.getWidth()*2, image.getHeight()*2);
        setImage(image);
    }
    
}
