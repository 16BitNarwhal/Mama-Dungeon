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
    public static int FPS = 60;
        
    /*
     * Method to help place objects at good position
     */
    public static void locate(Actor a) {
        System.out.print(a);
        System.out.println("(" + a.getX() + "," + a.getY() + ")");
    }
    
    /*
     * Method to get random float [0, 1] (inclusize)
     */
    public static float random() {
        // [0, 10001) ==> [0, 10000]
        float f = Greenfoot.getRandomNumber(10001);
        return f / 10000;
    }
    
    /*
     * Method to get random float [0, a] (inclusive)
     */
    public static float random(float a) {
        // [0, 1] * a ==> [0, a]
        return random()*a;
    }
    
    /*
     * Method to get random float [a, b] (inclusive)
     */
    public static float random(float a, float b) {
        // [0+a, b-a+a] ==> [a, b]
        return random(b-a) + a;
    }
    
    /*
     * Method to get random number [0, a] (inclusize)
     */
    public static int random(int a) {
       // [0, a+1) ==> [0, a]
       return Greenfoot.getRandomNumber(a+1);
    }
    
    /*
     * Method to get random between [a, b]
     */
    public static int random(int a, int b) {
       // [0 + a, b-a+1 + a) ==> [a, b]
       return Greenfoot.getRandomNumber(b-a+1) + a;
    }
}
