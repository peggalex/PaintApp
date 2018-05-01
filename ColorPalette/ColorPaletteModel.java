import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;

import javax.swing.JLabel;

public class ColorPaletteModel extends Observable{
	int diameter;
	int selectorSize;
	Boolean mouseInCircle = false;
	Point centre;
	Point hoveringPoint;
	Color hoveringColor;
	
	public void setHoveringColor(Color c) {
		this.hoveringColor = c;
	}
	
	public Color getHoveringColor() {
		return this.hoveringColor;
	}

	public void mouseEnteredCircle(Point p) {
		this.mouseInCircle = true;
		this.hoveringPoint = p.getCopy();
		setChanged();
		notifyObservers();
	}
	
	public Point getHoveringPoint() {
		return this.hoveringPoint;
	}
	
	public boolean getMouseInCircle() {
		return this.mouseInCircle;
	}
	
	public void mouseExitedCircle(){
		this.mouseInCircle = false;
		setChanged();
		notifyObservers();
	}
}
