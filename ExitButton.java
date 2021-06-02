import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Exit to main menu button
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class ExitButton extends GUI {
    
    public ExitButton() {
        GreenfootImage image = new GreenfootImage("icons/exitdoor.png"); 
        image.scale(image.getWidth()/10, image.getHeight()/10);
        setImage(image);
    }

    public void act() {
         if(Greenfoot.mousePressed(this)) { 
             Greenfoot.setWorld(new Menu());
         }
    }
}