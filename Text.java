import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * General text object
 * 
 * @author Eric Zhang 
 * @version (a version number or a date)
 */
public class Text extends GUI { 
    
    public Text(String text, int size, Color fcolor, Color bcolor) {
        GreenfootImage img = new GreenfootImage(text, size, fcolor, bcolor);
        setImage(img);
    }
    
}
