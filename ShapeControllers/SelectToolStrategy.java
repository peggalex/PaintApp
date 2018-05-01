
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class SelectToolStrategy extends ShapeController{
	boolean dragging;
	boolean shiftPressed;
	Point origin;
	
	public SelectToolStrategy(View v) {
		super(v);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point cursorPoint = new Point(e.getX(),e.getY());
		if (this.model.getSelectedShapes().size()!=0 && this.dragging) {
			Point shiftAmt = Point.subPoints(cursorPoint, origin);
			for (Shape s: this.model.getSelectedShapes()) {
				s.shift(shiftAmt);
			}
			this.origin = cursorPoint.getCopy();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) { 
		Point cursorPoint = new Point(e.getX(), e.getY());
		Shape shape = model.selectShapeAtPoint(cursorPoint);
		if (shape!= null) {
			if (!shape.getIsSelected()) {
				if (!this.shiftPressed) {
					this.model.unselectShapes();
				}
				this.model.selectShape(shape);
			}
			if (!(this.shiftPressed || shape.getIsSelected())) {
				this.model.unselectShapes();		
			}
			this.dragging = true;
			this.origin = cursorPoint.getCopy();
		} else {
			this.dragging = false; 
			this.model.unselectShapes();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_SHIFT) {
            this.shiftPressed = true;
        } 
        if (ke.getKeyCode() == KeyEvent.VK_DELETE || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
        	this.model.deleteSelected();
        }
	}
	
	public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_SHIFT) {
            this.shiftPressed = false;
        } 
	}

}
