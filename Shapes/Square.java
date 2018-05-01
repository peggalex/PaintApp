import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

//creates a rectangle with a corner value which it the point where the rectangles starts
//as well as a length and width
public class Square extends Rectangle {

	public Square(Point origin) {
		super(origin);
	}
	
	@Override
	public void setTopLeft(Point p) {
		super.setTopLeft(p);
		int newLength = Math.min(Math.abs(p.getX()-this.origin.getX()),Math.abs(p.getY()-this.origin.getY()));
		this.topLeft.setX(Math.max(this.origin.getX()-newLength, this.topLeft.getX()));
		this.topLeft.setY(Math.max(this.origin.getY()-newLength, this.topLeft.getY()));
		setChanged();
		notifyObservers();
	}
	public int getSquareLength() {
		return Math.min(this.height, this.width);
	}
	@Override
	public boolean getPointInShape(Point point) {
		int range = (this.lineThickness>Model.minRange) ? this.lineThickness : Model.minRange;
		Point p = Point.subPoints(point,this.shift);
		boolean minx = p.getX() >= this.topLeft.getX() - range;
		boolean maxx = p.getX() <= this.topLeft.getX() + getSquareLength() + range;
		boolean miny = p.getY() >= this.topLeft.getY() -range;
		boolean maxy = p.getY() <= this.topLeft.getY()+ getSquareLength() + range;
		return minx && maxx && miny&&maxy;
	}
	
	public Shape copyShape() {
		Square copiedSquare = new Square(this.origin.getCopy());
		copiedSquare.setDimensions(this.width, this.height);
		copiedSquare.setTopLeft(this.topLeft.getCopy());
		return copyAttributes(copiedSquare);
	}
	
	@Override
	public void paintComponent(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(this.lineThickness));
		Square s = this;
		int topLeftX = s.getTopLeft().getX()+s.getShift().getX();
		int topLeftY = s.getTopLeft().getY()+s.getShift().getY();
		int sideLength = s.getSquareLength();
		Color outlineColor = (s.getIsSelected()) ? View.selectedColor : s.getOutlineColor();
		Color fillColor = (s.getIsSelected()) ? Shape.mixColorWithAlphaBlue(s.getFillColor()): s.getFillColor();
		g2d.setColor(fillColor);
		g2d.fillRect(topLeftX, topLeftY, sideLength, sideLength);			
		int lineThickness = s.getLineThickness();
		if (lineThickness%2!=0) {
			lineThickness+=1;
		}
		g2d.setStroke(new BasicStroke(lineThickness));
		g2d.setColor(outlineColor);
		g2d.drawRect(topLeftX-(lineThickness/2), topLeftY-(lineThickness/2), sideLength+lineThickness, sideLength+lineThickness);
	}

}
