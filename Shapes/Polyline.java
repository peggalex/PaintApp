public class Polyline extends Scribble {

	public Polyline(Point origin) {
		super(origin);
	}
	
	public void changeTail(Point p) {
		this.scribble.get(this.scribble.size()-1).setX(p.getX());
		this.scribble.get(this.scribble.size()-1).setY(p.getY());
		this.setChanged();
		this.notifyObservers();
	}
	
	@Override
	public Shape copyShape() {
		Polyline copiedPolyline = new Polyline(this.scribble.get(0).getCopy());
		for (int i=1; i<this.scribble.size();i++) {
			copiedPolyline.addPoint(this.scribble.get(0).getCopy());
		}
		return copyAttributes(copiedPolyline);
	}
}
