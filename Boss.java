import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Boss class
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Boss extends Enemy { 
    
    private float phaseTimer, phaseWait;
    protected float spawnTimer, spawnWait; // for spawning enemies / minions
    protected int maxPhases, phase;
    
    /*
     * Boss constructor
     */
    public Boss(Room room, float atkDmg, float health, float movespeed, Vector2 pos, 
                        float distClose, float distFar, float detectRange, float atkWait, int maxPhases) {
        super(room, atkDmg, health, movespeed, pos, distClose, distFar, detectRange, atkWait);
        this.maxPhases = maxPhases;
        
        phaseWait = 5f;
        phaseTimer = 0;
        
        spawnWait = 2f;
    }
    
    /*
     * Boss's attack/move cycle
     */
    protected void action() {
        if (phase == 0) {
            // use enemy's standard movement
            super.action();
        } 
    }
    
    /*
     * Update timers based on FPS
     */
    protected void updateTime() {
        super.updateTime();
        if (phaseTimer>0) phaseTimer -= 1f / Utils.FPS;
        else {
            phaseTimer = Utils.random(phaseWait*1/2, phaseWait*3/2);
            phase = Utils.random(maxPhases - 1);
        }
        if (phase==1 && spawnTimer>0) spawnTimer -= 1f / Utils.FPS; 
        
    }
}
