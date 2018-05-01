

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class RectangleStrategy extends ShapeController{
	Rectangle rectangle;

	public RectangleStrategy(View v) {
		super(v);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		int width = Math.abs(e.getX() - this.rectangle.getOrigin().getX());
		int height = Math.abs(e.getY() - this.rectangle.getOrigin().getY());
		this.rectangle.setDimensions(width, height);
		this.rectangle.setTopLeft(Point.mouseEvent(e));
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.rectangle = new Rectangle(Point.mouseEvent(e));
		this.model.addShape(this.rectangle);
	}

}
