import java.awt.Graphics; 

public abstract class GameObject {

	//these are the objects' location in the gameCourt
	private int px;
	private int py; 

	//these are the objects' width and height in the gameCourt
	private int width;
	private int height;

	//these are the objects' speed in the x and y directions in the gameCourt
	private int vx; 
	private int vy; 

	//these are the bounds set on an object which will follow the court height and width
	private int maxX; 
	private int maxY; 
	
	public GameObject(int vx, int vy, int px, int py, int width, int height, int courtWidth, int courtHeight) {
        this.vx = vx;
        this.vy = vy;
        this.px = px;
        this.py = py;
        this.width  = width;
        this.height = height;
        this.maxX = courtWidth - width / 2;
        this.maxY = courtHeight - height;
	}
	
	
	 /*** GETTERS **********************************************************************************/
 	public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }
    
    public int getVx() {
        return this.vx;
    }
    
    public int getVy() {
        return this.vy;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
/*** SETTERS **********************************************************************************/
    public void setPx(int px) {
        this.px = px;
        clip();
    }
    public void setPy(int py) {
        this.py = py;
        clip();
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

	public void setWidth(int w){
	this.width = w;
	}
	
	public void setHeight(int h){
	this.height = h;
	}

    /*** UPDATES AND OTHER METHODS ****************************************************************/

    /**
     * Prevents the object from going outside of the bounds of the area designated for the object.
     * (i.e. Object cannot go outside of the active area the user defines for it).
     */ 
   
    private void clip() {
        this.px = Math.min(Math.max(this.px, 0  + this.width / 2), this.maxX);
        this.py = Math.min(Math.max(this.py, 0), this.maxY);
    }

    public void move() {
        this.px += this.vx;
        this.py += this.vy;

        clip();
    }

    /**
     * Determine whether this game object is currently intersecting another object.
     * 
     * Intersection is determined by comparing bounding boxes. If the bounding boxes overlap, then
     * an intersection is considered to occur.
     */
   
    public boolean intersects(GameObject that) {
        return (this.px + (this.width/2) >= (that.px - that.width/2)
            && this.py + (this.height/2) >= (that.py - that.height/2)
            && that.px + (that.width/2) >= (this.px - this.width/2)
            && that.py + (that.height/2) >= (this.py - this.height/2));
    }


    /**
     * Determine whether this game object will intersect another in the next time step, assuming
     * that both objects continue with their current velocity.
     * 
     * Intersection is determined by comparing bounding boxes. If the  bounding boxes (for the next
     * time step) overlap, then an intersection is considered to occur.
     */
    public boolean willIntersect(GameObject that) {
        int thisNextX = this.px + this.vx;
        int thisNextY = this.py + this.vy;
        int thatNextX = that.px + that.vx;
        int thatNextY = that.py + that.vy;
    
        return (thisNextX + this.width >= thatNextX
            && thisNextY + this.height >= thatNextY
            && thatNextX + that.width >= thisNextX 
            && thatNextY + that.height >= thisNextY);
    }


    /**
     * Update the velocity of the object in response to hitting an obstacle in the given direction.
     * If the direction is null, this method has no effect on the object.
     */
    public void bounce(Direction d) {
        if (d == null) return;
        
        switch (d) {
        case UP:
            this.vy = Math.abs(this.vy);
            break;  
        case DOWN:
            this.vy = -Math.abs(this.vy);
            break;
        case LEFT:
            this.vx = Math.abs(this.vx);
            break;
        case RIGHT:
            this.vx = -Math.abs(this.vx);
            break;
        }
    }

    /**
     * Determine whether the game object will hit a wall in the next time step. If so, return the
     * direction of the wall in relation to this game object.
     */
    public Direction hitWall() {
        if (this.px + this.vx < 0) {
            return Direction.LEFT;
        } else if (this.px + this.vx > this.maxX) {
           return Direction.RIGHT;
        }

        if (this.py + this.vy < 0) {
            return Direction.UP;
        } else if (this.py + this.vy > this.maxY) {
            return Direction.DOWN;
        } else {
            return null;
        }
    }

    /**
     * Determine whether the game object will hit another object in the next time step. If so,
     * return the direction of the other object in relation to this game object.
     */
    public Direction hitObj(GameObject that) {
        if (this.willIntersect(that)) {
            double dx = that.px + that.width / 2 - (this.px + this.width / 2);
            double dy = that.py + that.height / 2 - (this.py + this.height / 2);

            double theta = Math.acos(dx / (Math.sqrt(dx * dx + dy *dy)));
            double diagTheta = Math.atan2(this.height / 2, this.width / 2);

            if (theta <= diagTheta) {
                return Direction.RIGHT;
            } else if (theta > diagTheta && theta <= Math.PI - diagTheta) {
                // Coordinate system for GUIs is switched
                if (dy > 0) {
                    return Direction.DOWN;
                } else {
                    return Direction.UP;
                }
            } else {
                return Direction.LEFT;
            }
        } else {
            return null;
        }
    }

    /**
     * Default draw method that provides how the object should be drawn in the GUI. This method does
     * not draw anything. Subclass should override this method based on how their object should
     * appear.
     */
    public abstract void draw(Graphics g);
}