

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ToolController implements MouseListener, KeyListener, MouseMotionListener{
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public ToolController getCopy() {
		return new ToolController();
	}
	
	public static ToolController factory(String tool, View v) {
		if (tool=="select") {
			return new SelectToolStrategy(v);
		} else if (tool=="fillshape") {
			return new PaintBucketStrategy(v);
		} else if (tool=="circle") {
			return new CircleStrategy(v);
		} else if (tool=="rectangle") {
			return new RectangleStrategy(v);
		} else if (tool=="square") {
			return new SquareStrategy(v);
		} else if (tool=="polyline") {
			return new PolylineStrategy(v);
		} else if (tool=="scribble") {
			return new ScribbleStrategy(v);
		} else {
			return null;
		}
	}
}
