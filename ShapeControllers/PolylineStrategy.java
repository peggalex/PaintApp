

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PolylineStrategy extends ShapeController {
	boolean drawingPolyline;
	Polyline polyline;

	public PolylineStrategy(View v) {
		super(v);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (this.polyline!=null) {
			this.polyline.changeTail(Point.mouseEvent(e));
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.polyline!=null) {
				this.polyline.addPoint(Point.mouseEvent(e));
		} else {
			this.polyline = new Polyline(Point.mouseEvent(e));
			this.polyline.addPoint(Point.mouseEvent(e));
			model.addShape(this.polyline);

		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			this.polyline = null;
		}
	}

}
