import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

public class Selected extends Observable{
	private Color outlineColor;
	private Color fillColor;
	private ShapeController shapeController;
	private int lineThickness;
	ImageIcon imageIcon;
	Shape selectedShape;
	
	public Selected() {
		this.outlineColor = Color.BLACK;
		this.fillColor = new Color(0,0,0,0);
		this.lineThickness = 1;
	}
	
	public Color getOutlineColor() {
		return this.outlineColor;
	}
	
	public Color getFillColor() {
		return this.fillColor;
	}
	
	public int getLineThickness() {
		return this.lineThickness;
	}
	
	public void setLineThickness(int lt) {
		this.lineThickness = lt;
	}
	
	public ShapeController getShapeController() {
		return this.shapeController;
	}
	
	public void setOutlineColor(Color c) {
		this.outlineColor = c;
		setChanged();
		notifyObservers();
	}
	
	public void setFillColor(Color c) {
		this.fillColor =c;
		setChanged();
		notifyObservers();
	}
	
	public void setShapeController(ShapeController sc) {
		this.shapeController = sc;
	}

	public void setTool(String s) {
		this.imageIcon = Model.icons.get(s);
		this.selectedShape = Model.StringToShapes.get(s);
		setChanged();
		notifyObservers();
	}
	
	public ImageIcon getImageIcon() {
		return this.imageIcon;
	}
	
	@Override
	public void addObserver(Observer o) {
		super.addObserver(o);
		o.update(this, null);
	}
}
