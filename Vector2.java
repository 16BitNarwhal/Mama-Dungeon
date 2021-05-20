/**
 * 2D Vector
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Vector2 { 
    private float x, y;
 
    /*
     * Vector2 constructors
     */
    public Vector2() {
        this(0, 0);
    }
    
    public Vector2(float x, float y) {
        this.x = x; this.y = y;
    } 
    
    public Vector2(int x, int y) {
        this.x = x; this.y = y;
    }        
    
    /*
     * Getters and setters
     */
    public int getX() { return Math.round(this.x); }
    public int getY() { return Math.round(this.y); }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    
    // returns copy of this vector
    public Vector2 get() { return new Vector2(x, y); } 
    
}
