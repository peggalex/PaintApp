

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class CircleStrategy extends ShapeController {
	Circle circle;
	
	public CircleStrategy(View v) {
		super(v);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.circle.setRadius(Point.getDistance(Point.mouseEvent(e), circle.getCentre()));
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.circle = new Circle(Point.mouseEvent(e));
		model.addShape(this.circle);
	}

}
