

public class SelectTool {
	private final static SelectTool first_instance = new SelectTool();
	private Shape shapeselected;
	
	
	public SelectTool() {
		
	}
	public static SelectTool getInstance() {
		return first_instance;
	}
	public Shape getShapeSelected() {
		return shapeselected;
	}
	public void setShapeSelected(Shape shape) {
		this.shapeselected = shape;
	}
}
