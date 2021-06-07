import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Room with lotsa traps and spikes and stuff 
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class TrapRoom extends Room {

    /**
     * Construct TrapRoom
     */
    public TrapRoom(int rad, Room l, Room r, Room u, Room d) {
        super(rad, l, r, u, d);
        
        int numTraps = Utils.random(25, 35);
        for (int i=0;i<numTraps;i++) {
            addObject(new FloorSpike(),0,0);
        }
        
        int numHealthPots = Utils.random(1, 2);
        for (int i=0;i<numHealthPots;i++) {
            Vector2 pos = new Vector2(
                Utils.random(Room.getLeftBound() + Utils.tileSize, Room.getRightBound() - Utils.tileSize),
                Utils.random(Room.getUpBound() + Utils.tileSize, Room.getDownBound() - Utils.tileSize));
            for (int n=0;n<3;n++) // for higher potency
                addObject(new HealthPotion(), pos.getX(), pos.getY());
        }
        
        int numCoins = Utils.random(2, 4);
        for (int i=0;i<numCoins;i++) {
            Vector2 pos = new Vector2(
                Utils.random(Room.getLeftBound() + Utils.tileSize, Room.getRightBound() - Utils.tileSize),
                Utils.random(Room.getUpBound() + Utils.tileSize, Room.getDownBound() - Utils.tileSize));
            addObject(new Coin(), pos.getX(), pos.getY());
        }
        
    }
    

    
}
