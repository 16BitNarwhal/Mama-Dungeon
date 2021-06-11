import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Introduction / story
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Intro extends GUI {
    
    public void act() {
        if (getY() > 210)
            setLocation(getX(), getY()-3);
        
    }
    
}
