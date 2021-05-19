/**
 * 2D Vector
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Vector2 { 
    private float x, y;
 
    // constructors
    public Vector2() {
        this(0f, 0f);
    }
    
    public Vector2(float x, float y) {
        this.x = x; this.y = y;
    } 
    
    // getters and setters
    public float getX() { return this.x; }
    public float getY() { return this.y; }
    
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    
    // returns copy of this vector
    public Vector2 get() { return new Vector2(x, y); } 
    
}
