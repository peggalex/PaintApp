

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class Scribble extends Shape{
	protected ArrayList<Point> scribble;
	
	public Scribble(Point origin) {
		super();
		this.scribble = new ArrayList<>();
		addPoint(origin);
	}
	
	public void addPoint(Point p) {
		this.scribble.add(p);
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Point> getScribble() {
		return this.scribble;
	}
	
	@Override
	public boolean getPointInShape(Point p) {
		int range = (this.lineThickness>Model.minRange) ? this.lineThickness : Model.minRange;
		for(int i=0;i<this.scribble.size()-1; i++){
			Point p1=this.scribble.get(i);
			Point p2=scribble.get(i+1);
			Point[] pointsOnLine = getPointsOnLine(p1,p2);
			if (pointsOnLine==null){
				continue;
			}
			for (Point poL: pointsOnLine) {
				if (Point.getDistance(Point.subPoints(p, this.shift), poL)<=range) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Shape copyShape() {
		Scribble copiedScribble = new Scribble(this.scribble.get(0).getCopy());
		for (int i=1; i<this.scribble.size();i++) {
			copiedScribble.addPoint(this.scribble.get(0).getCopy());
		}
		return copyAttributes(copiedScribble);
	}
	
	@Override
	public void paintComponent(Graphics2D g2d) {
		super.paintComponent(g2d);
		Scribble s = this;
		ArrayList<Point> scribbleInstance = s.getScribble();
		if (s.getIsSelected()) {
			g2d.setColor(View.selectedColor);
		} else {
			g2d.setColor(this.outlineColor);
		}
		g2d.setStroke(new BasicStroke(this.lineThickness));
		for(int i=0;i<scribbleInstance.size()-1; i++){
			Point p1=Point.addPoints(scribbleInstance.get(i),s.getShift());
			Point p2=Point.addPoints(scribbleInstance.get(i+1),s.getShift());
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
	}
	
	public Point[] getPointsOnLine(Point origin, Point endPoint) {
		int width = Math.abs(endPoint.getX()-origin.getX());
		int height = Math.abs(endPoint.getX()-origin.getX());
		int size = Math.max(width, height);
		if (size==0) {
			return null;
		}
		Point[] pointsOnLine = new Point[size+1];
		int rise = endPoint.getY() - origin.getY();
		int run = endPoint.getX() - origin.getX();
		for (int i=0; i<=size; i++) {
			int x = (int)(((run*i)/size)+origin.getX());
			int y = (int)(((rise*i)/size)+origin.getY());
			pointsOnLine[i] = new Point(x,y);
		}
		return pointsOnLine;
	}
	
}
