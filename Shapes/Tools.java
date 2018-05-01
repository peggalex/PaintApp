import java.awt.Graphics2D;
import java.util.Observable;

public class Tools extends Observable{
	protected String name;
	protected ToolController toolController;

	public void paintComponent(Graphics2D g2d) {
		return;
	}
	
	public ToolController getToolController() {
		return this.toolController;
	}
	
	public String getName() {
		return this.name;
	}
}
