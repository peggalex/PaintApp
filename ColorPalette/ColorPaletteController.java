import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ColorPaletteController implements MouseMotionListener{
	Point circleCenter;
	int radius;
	Model model;
	ColorPaletteView colorView;
	ColorPaletteModel colorModel;
	
	public ColorPaletteController(Model model, ColorPaletteView colorView, ColorPaletteModel colormodel) {
		this.radius = colorView.diameter/2;
		this.circleCenter = new Point(this.radius,this.radius);
		this.model = model;
		this.colorView = colorView;
		this.colorModel = colormodel;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Point.getDistance(circleCenter, Point.mouseEvent(e))<=radius) {
			this.colorModel.mouseEnteredCircle(Point.mouseEvent(e));
		} else {
			this.colorModel.mouseExitedCircle();
		}
		
	}
	

}
