import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

import javax.swing.ImageIcon;


public class Model extends Observable{
	private static Model model = new Model();
	static HashMap<String,ImageIcon> icons = getIcons();
	static HashMap<String, Shape> StringToShapes = initiateShapes();
	static int minRange=5;
	static Color selectedColor= new Color(0,0,255,128);
	private ArrayList<Shape> shapes;
	private Selected selected;
	private ArrayList<Shape> selectedShapes;
	
	private Model() {
		this.selected = new Selected();
		this.selectedShapes = new ArrayList<>();
		shapes = new ArrayList<>();
	}
	
	public void deleteSelected() {
		this.shapes.removeAll(this.selectedShapes);
		setChanged();
		notifyObservers();
	}
	
	public void selectShape(Shape s) {
		s.setIsSelected(true);
		this.selectedShapes.add(s);
		setChanged();
		notifyObservers();
	}
	
	public void unselectShapes() {
		for (Shape s: this.selectedShapes) {
			s.setIsSelected(false);
		}
		this.selectedShapes.clear();
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Shape> getSelectedShapes(){
		return this.selectedShapes;
	}
	
	public Selected getSelected() {
		return this.selected;
	}
	
	public void addShape(Shape s) {
		s.setFillColor(selected.getFillColor());
		s.setOutlineColor(selected.getOutlineColor());
		s.setLineThickness(selected.getLineThickness());
		shapes.add(s);
		this.setChanged();
		this.notifyObservers();
	}
	
	public Shape selectShapeAtPoint(Point p) {
		for(int i = this.shapes.size()-1;i>-1; i-- ) {
			// ArrayLists are fastest appended rather than prepended
			// so shapes are FILO - therefore we have to iterate backwards
			// if we want to access recent-most shapes first
			if(this.shapes.get(i).getPointInShape(p)) {
				Shape newFocus = this.shapes.get(i);
				this.shapes.remove(i);
				this.shapes.add(newFocus);
				return newFocus;
				
			}
		}
		return null;
	}
	
	public static Model singleton() {
		return model;
	}
	
	public Shape getShapeAtPoint(Point p) {
		for(int i = this.shapes.size()-1;i>-1; i-- ) {
			// ArrayLists are fastest appended rather than prepended
			// so shapes are FILO - therefore we have to iterate backwards
			// if we want to access recent-most shapes first
			if(this.shapes.get(i).getPointInShape(p)) {
				return this.shapes.get(i);
				
			}
		}
		return null;
	}
	
	private static HashMap<String,ImageIcon> getIcons() {
		HashMap<String, ImageIcon> icons = new HashMap<>();
		addIcon("Image1.png","circle",icons);
		addIcon("Image2.png","rectangle",icons);
		addIcon("Image3.png","square",icons);
		addIcon("Image4.png","scribble",icons);
		addIcon("Image5.png","polyline",icons);
		addIcon("paintBucketIcon.png","fillshape",icons);
		addIcon("cursor_PNG78.png","select",icons);
		return icons;
	}

	
	private static void addIcon(String fileName, String desc, HashMap<String,ImageIcon> hm) {
		Image i = new ImageIcon(OptionsPane.class.getResource(fileName), desc).getImage();
		Dimension buttonSize = OptionsPaneTools.buttonSize;
		Image iScaled = i.getScaledInstance(buttonSize.width, buttonSize.height, java.awt.Image.SCALE_SMOOTH);
		hm.put(desc, new ImageIcon(iScaled));
	}
	
	private static HashMap<String, Shape> initiateShapes(){
		HashMap<String, Shape> hm = new HashMap<>();
		Point nullPoint = new Point(0,0);
		hm.put("circle", new Circle(nullPoint));
		hm.put("rectangle", new Rectangle(nullPoint));
		hm.put("square", new Square(nullPoint));
		hm.put("polyline", new Polyline(nullPoint));
		hm.put("dotshape", new FillShape());
		hm.put("scribble", new Scribble(nullPoint));
		return hm;
	}
	
	public Object[] getShapes() {
		return this.shapes.toArray();
	}
	
}
