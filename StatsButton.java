import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button to show stats / hide stats
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class StatsButton extends GUI {  
    
    private Stats stats;
    private GreenfootImage upImage, downImage;
    
    public StatsButton(Stats stats) {
        this.stats = stats;
        
        upImage = new GreenfootImage("icons/up.png");
        upImage.scale(upImage.getWidth()/10, upImage.getHeight()/10);
        
        downImage = new GreenfootImage("icons/down.png");
        downImage.scale(downImage.getWidth()/10, downImage.getHeight()/10);
        
        setImage(upImage);
    } 
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
           if (stats.isShown()) {
               setImage(upImage); 
           } else {
               setImage(downImage);
           }
           stats.flipShown();
        }
    }
}
