

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class SquareStrategy extends ShapeController {
	Square square;

	public SquareStrategy(View v) {
		super(v);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int length = Math.abs(e.getX() - this.square.getOrigin().getX());
		int width = Math.abs(e.getY() - this.square.getOrigin().getY());
		this.square.setDimensions(length, width);
		square.setTopLeft(Point.mouseEvent(e));
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.square = new Square(Point.mouseEvent(e));
		model.addShape(this.square);
	}

}
