import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WallTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WallTile extends Tile {
    
    public WallTile(String type) {
        // location of tile images
        String path = "dungeon/";
        // initialize default image
        GreenfootImage image = new GreenfootImage(path + "/wall_mid.png");
        
        if (type=="top") {
            image = new GreenfootImage(path + "wall_mid.png");
        } else if (type=="bottom") {
            image = new GreenfootImage(path + "wall_mid.png");
        } else if (type=="left") {
            image = new GreenfootImage(path + "wall_right.png");
        } else if (type=="right") {
            image = new GreenfootImage(path + "wall_left.png");
        } else if (type=="top left") {
            
        } else if (type=="top right") {
            
        } else if (type=="bottom left") {
            
        } else if (type=="bottom right") {
            
        }
        
        image.scale(image.getWidth()*2, image.getHeight()*2);
        setImage(image);
    }
    
}
