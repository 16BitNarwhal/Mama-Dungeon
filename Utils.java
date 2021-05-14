import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Extra utilities
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */

public class Utils {
    public static int tileSize = 32,
        worldWidth = 640,
        worldHeight = 416;
    
    /*
     * Method to help place objects at good position
     */
    public static void locate(Actor a) {
        System.out.print(a);
        System.out.println("(" + a.getX() + "," + a.getY() + ")");
    }
}
