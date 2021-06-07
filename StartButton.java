import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button that starts game
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class StartButton extends GUI {
    
    private int clicks; // # of times button was clicked
    
    public StartButton() {
        GreenfootImage image = new GreenfootImage("Icons/playbuttonpdn.png");
        image.scale(image.getWidth()/10, image.getHeight()/10);
        setImage(image);
        
        clicks = 0;
    }
    
    public void act() {
         if(Greenfoot.mousePressed(this)) {
             clicks += 1;
             if (clicks == 1) {
                 getWorld().addObject(new Intro(), Utils.worldWidth/2, Utils.worldHeight);
                 setLocation(Utils.worldWidth - 40, Utils.worldHeight - 40);
             } else {
                 Greenfoot.setWorld(new SpawnRoom());
             }
         }
    } 
    
}
