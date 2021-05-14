import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button that starts game
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class StartButton extends GUI {
    public StartButton() {
        GreenfootImage image = new GreenfootImage("Icons/playbuttonpdn.png");
        image.scale(image.getWidth()/10, image.getHeight()/10);
        setImage(image);
        
    }
    
    public void act() {
         if(Greenfoot.mousePressed(this)) { 
             Greenfoot.setWorld(new SpawnRoom());
         }
    }
}
