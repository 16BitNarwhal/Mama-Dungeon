import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Floortile image
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class FloorTile extends Tile {
    
    public FloorTile() {
        // 8 types of floor tiles, may add them with random function
        GreenfootImage image = new GreenfootImage("dungeon/floor_1.png");
        image.scale(image.getWidth()*2, image.getHeight()*2);
        setImage(image);
        
    }
    
}
