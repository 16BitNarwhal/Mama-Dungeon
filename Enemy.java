import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Generaly enemy / mob, attacks player
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Enemy extends Entity {
    
    /*
     * Enemy constructor
     */
    public Enemy(Room room, float atkDmg, float health, int movespeed, Vector2 pos) {
        super(room, atkDmg, health, movespeed, pos);
        this.maxHealth = 50; // change in subclasses
        
    }
    
    public void act() {
        
    }    
}

/*
 * Todo:
 * initializers
 * movement
 * attack
 * 
 * First mob
 * 
 */