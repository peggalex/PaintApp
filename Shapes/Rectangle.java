import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

//creates a rectangle with a corner value which it the point where the rectangles starts
//as well as a length and width
public class Rectangle extends Shape {
	protected Point origin;
	protected int height;
	protected int width;
	protected Point topLeft;
	
	//initializes the rectangle with the given properties
	public Rectangle(Point origin){
		super();
		this.width = 0;
		this.height = 0;
		this.origin = origin.getCopy();
		this.topLeft = origin.getCopy();
	}
	//returns the initial point of the rectangle
	public Point getTopLeft() {
		return this.topLeft;
	}
	
	public Point getOrigin() {
		return this.origin;
	}
	//changes the initial point of the rectangle
	public void setTopLeft(Point newTopLeft) {
		int newX = Math.min(origin.getX(), newTopLeft.getX());
		int newY = Math.min(origin.getY(), newTopLeft.getY());
		this.topLeft.setX(newX);
		this.topLeft.setY(newY);
		setChanged();
		notifyObservers();
	}
	
	public void setOrigin(Point p) {
		this.origin = p;
	}
	
	//returns the width value
	public int getWidth() {
		return this.width;
	}
	//return the length value
	public int getHeight() {
		return this.height;
	}
	//changes width and below changes length of rectangle
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
		setChanged();
		notifyObservers();
	}
	@Override
	public boolean getPointInShape(Point point) {
		int range = (this.lineThickness>Model.minRange) ? this.lineThickness : Model.minRange;
		Point p = Point.subPoints(point, this.shift);
		boolean minx = p.getX() >= this.topLeft.getX() - (range);
		boolean maxx = p.getX() <= this.topLeft.getX() + this.width + (range);
		boolean miny = p.getY() >= this.topLeft.getY() - (range);
		boolean maxy = p.getY() <= this.topLeft.getY()+ this.height + (range);
		return minx && maxx && miny&&maxy;
	}
	
	@Override
	public Shape copyShape() {
		Rectangle copiedRectangle = new Rectangle(this.origin.getCopy());
		copiedRectangle.setDimensions(this.width, this.height);
		copiedRectangle.setTopLeft(this.topLeft.getCopy());
		return copyAttributes(copiedRectangle);
	}
	
	@Override
	public void paintComponent(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(this.lineThickness));
		Rectangle r = this;
		int topLeftX = r.getTopLeft().getX()+r.getShift().getX();
		int topLeftY = r.getTopLeft().getY()+r.getShift().getY();
		int width = r.getWidth();
		int height = r.getHeight();
		Color outlineColor = (r.getIsSelected()) ? View.selectedColor : r.getOutlineColor();
		Color fillColor = (r.getIsSelected()) ? Shape.mixColorWithAlphaBlue(r.getFillColor()): r.getFillColor();
		g2d.setColor(fillColor);
		g2d.fillRect(topLeftX, topLeftY, width, height);			
		int lineThickness = r.getLineThickness();
		if (lineThickness%2!=0) {
			lineThickness+=1;
		}
		g2d.setStroke(new BasicStroke(lineThickness));
		g2d.setColor(outlineColor);
		g2d.drawRect(topLeftX-(lineThickness/2), topLeftY-(lineThickness/2), width+lineThickness, height+lineThickness);
	}

}

