

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ScribbleStrategy extends ShapeController {
	Scribble scribble;

	public ScribbleStrategy(View v) {
		super(v);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.scribble.addPoint(new Point(e.getX(),e.getY()));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.scribble = new Scribble(Point.mouseEvent(e));
		model.addShape(this.scribble);
	}

}
