import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Show player stats at end of game
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stats extends GUI {
    
    private boolean shown;
    private Vector2 pos, offset;
    private ArrayList<Text> texts;
    private ArrayList<Vector2> textsPos;
    
    private World world; // because getWorld is being a meanie
    
    public Stats(World world) { 
        this.world = world;        
        shown = false;
        
        offset = new Vector2(0, Utils.worldHeight - 210);
        pos = new Vector2(Utils.worldWidth/2, 210);
        
        texts = new ArrayList<Text>();
        textsPos = new ArrayList<Vector2>();
        
        int minNum = (int)Player.totalTime / 60;
        int secNum = (int)Player.totalTime % 60;
        String sec = String.valueOf(secNum);
        if (secNum < 10) sec = "0" + sec;
        String min = String.valueOf(minNum);
        addText(String.format("%s:%s", min, sec), Utils.worldWidth/2, 75);
        
        addText("Kills: ", Utils.worldWidth/2-100,125);
        addText(String.valueOf(Player.kills), Utils.worldWidth/2+100,125);
        addText("Coins: ", Utils.worldWidth/2-100,225);
        addText(String.valueOf(Player.coins), Utils.worldWidth/2+100,225);
        addText("Health potions: ", Utils.worldWidth/2-50,325);
        addText(String.valueOf(Player.healthPots), Utils.worldWidth/2+100,325);
        
    }
    
    public void act() { 
        if (shown) {
            getImage().setTransparency(255);
            for (int i=0;i<texts.size();i++) 
                if (offset.getY()+textsPos.get(i).getY() < Utils.worldHeight)
                    texts.get(i).getImage().setTransparency(255); 
                
            if (offset.getY()+210 > 210) 
                offset.setY(offset.getY()-3);
        } else {
            if (offset.getY()+210 < Utils.worldHeight) 
                offset.setY(offset.getY()+3);
                
            for (int i=0;i<texts.size();i++)
                    if (offset.getY()+textsPos.get(i).getY() >= Utils.worldHeight)
                        texts.get(i).getImage().setTransparency(0);
                        
            if (offset.getY()+210 >= Utils.worldHeight) {
                getImage().setTransparency(0);
                for (Text t : texts) 
                    t.getImage().setTransparency(0);
            }
        }
        setLocation(pos.getX() + offset.getX(), pos.getY() + offset.getY());
        for (int i=0;i<texts.size();i++) {
            texts.get(i).setLocation(textsPos.get(i).getX() + offset.getX(),
                                     textsPos.get(i).getY() + offset.getY());                        
        }
    }
    
    private void addText(String text, int x, int y) {
        Text t = new Text(text, 40, Color.BLACK);
        texts.add(t);
        textsPos.add(new Vector2(x, y));
        world.addObject(t, x, y);
    }
    
    public boolean isShown() { return shown; }
    public void flipShown() { shown = !shown; }
}
