/**
 * 2D Vector of the form (x,y)
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
     * Return addition of vector a and vector b
     */
    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }
    /*
     * Return subtraction of vector b from vector a
     */
    public static Vector2 sub(Vector2 a, Vector2 b) {
        return new Vector2(a.x - b.x, a.y - b.y);
    }
    /*
     * Return multiplication of this vector by a constant c
     */
    public Vector2 mult(float c) { 
        return new Vector2(x * c, y * c);
    }
    /*
     * Return magnitude of this vector
     */
    public float mag() { 
        return (float)Math.sqrt(x*x + y*y);
    }
    /*
     * Return distance between vector a and vector b
     */
    public static float distance(Vector2 a, Vector2 b) {
        Vector2 temp = Vector2.sub(a,b);
        return temp.mag();
    }
    /*
     * Normalizes vector
     */
    public Vector2 normalize() {
        return new Vector2(x / mag(), y / mag());
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
        
    /*
     * Override tostring method
     */
    public String toString() { return (this.x + " " + this.y); }
    
}
