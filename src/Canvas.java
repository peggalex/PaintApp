import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Canvas extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;
	private Model model;
	static Dimension canvasSize = new Dimension(1000,1000);
	
	public Canvas(){
		super();
		this.setBackground(Color.white);
		this.setPreferredSize(canvasSize);
		this.model = Model.singleton();
		this.model.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {	
        super.paintComponent(g); 
        Object[] shapes = model.getShapes();
        Graphics2D g2d = (Graphics2D) g;
        for (Object o: shapes) {
        	((Shape)o).addObserver(this);
        	((Shape)o).paintComponent(g2d);
        }
	}

}
