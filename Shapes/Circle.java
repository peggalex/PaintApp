import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Shape {
	
	public Circle(Point centre) {
		super();
		this.centre = centre;
		this.radius = 0;
	}

	private Point centre;
	private int radius;

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
		setChanged();
		notifyObservers();
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
		setChanged();
		notifyObservers();
	}
	
	@Override
	public boolean getPointInShape(Point p) {
		int pRadius = Point.getDistance(p, Point.addPoints(getCentre(),this.shift));
		int range = (this.lineThickness>Model.minRange) ? this.lineThickness : Model.minRange;
		return pRadius <= getRadius()+(range);
	}
	
	@Override
	public Shape copyShape() {
		Circle copiedCircle = new Circle(this.centre.getCopy());
		copiedCircle.setRadius(this.radius);
		return copyAttributes(copiedCircle);
	}

	@Override
	public void paintComponent(Graphics2D g2d) {
		super.paintComponent(g2d);
		Circle c = this;
		int radius = c.getRadius();
		int x = (-1*radius)+c.getCentre().getX()+c.getShift().getX();
		int y = (-1*radius)+c.getCentre().getY()+c.getShift().getY();
		int width = radius*2;
		int height = width;
		Color fillColor = (this.getIsSelected()) ? Shape.mixColorWithAlphaBlue(this.fillColor) : this.fillColor;
		Color outlineColor = (this.getIsSelected()) ? Shape.mixColorWithAlphaBlue(this.outlineColor) : this.outlineColor;
		g2d.setColor(fillColor);
		g2d.fillOval(x, y, width, height);
		int lineThickness = c.getLineThickness();
		if (lineThickness%2!=0) {
			lineThickness+=1;
		}
		g2d.setStroke(new BasicStroke(lineThickness));
		g2d.setColor(outlineColor);
		g2d.drawOval(x-(lineThickness/2), y-(lineThickness/2), width+lineThickness, height+lineThickness);
	}
}
