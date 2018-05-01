import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Observable;


public class Shape extends Tools{

	protected Color fillColor;
	protected Color outlineColor;
	protected int lineThickness;
	private boolean isSelected;
	protected Point shift;
	
	/**
	 * returns the color of this shape
	 * @return
	 */

	public Shape() {
		this.shift = new Point(0,0);
	}
	
	public Color getFillColor() {
		return this.fillColor;
	}
	
	public Color getOutlineColor() {
		return this.outlineColor;
	}
	
	
	/**
	 * Set the color of this Shape
	 * 
	 * @param color - the color of the shape
	 */
	public void setFillColor(Color color) {
		this.fillColor = color;
		setChanged();
		notifyObservers();
	}
	
	public void setOutlineColor(Color c) {
		this.outlineColor = c;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * return the size of line thickness
	 * 
	 * @return line - the line thickness
	 */
	public int getLineThickness() {
		return this.lineThickness;
	}
	
	/**
	 * set the line thickness of this shape
	 * 
	 * @param line
	 */
	public void setLineThickness(int line) {
		this.lineThickness = line;
		setChanged();
		notifyObservers();
	}
	public boolean getPointInShape(Point p){
		return false;
	}

	public void setIsSelected(boolean b) {
		this.isSelected = b;
		this.setChanged();
		this.notifyObservers();
	}
	
	public boolean getIsSelected() {
		return this.isSelected;
	}
	
	public void setShift(Point p) {
		this.shift = p.getCopy();
	}
	
	public void shift(Point p) {
		this.shift = Point.addPoints(p, this.shift);
		this.setChanged();
		this.notifyObservers();
	}
	
	public Point getShift() {
		return this.shift;
	}
	
	public Shape copyShape() {
		return null;
	}
	public Shape copyAttributes(Shape s) {
		s.setFillColor(this.fillColor);
		s.setOutlineColor(this.outlineColor);
		s.setLineThickness(this.lineThickness);
		return s;
	}
	
	public static Color mixColorWithAlphaBlue(Color c) {
		// a*RGBfront + (1-a) * RGBbottom
		// a = 1/2
		int r = c.getRed()/2;
		int g = c.getGreen()/2;
		int b = 255/2+(c.getBlue()/2);
		int a = c.getAlpha()/2;
		return new Color(r,g,b,a);
	}
}
