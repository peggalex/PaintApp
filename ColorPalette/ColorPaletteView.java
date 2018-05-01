

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorPaletteView extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;

	
	JLabel selectedColorLabel;
	int diameter = 200;
	int selectorSize=10;
	Boolean mouseInCircle = false;
	Point centre;
	BufferedImage bi;
	Point hoveringPoint;
	static int width = 200;
	static Point circleSize = new Point(width,width);
	float shade = 1.0f;
	Color hoveringColor;
	
	public ColorPaletteView(int diameter) {
		this.diameter = diameter;
	}
	
	@Override
	public void paintComponent(Graphics graphic) {
		this.setPreferredSize(new Dimension(width,width));
		this.setAlignmentX(CENTER_ALIGNMENT);
        super.paintComponent(graphic); 
        Graphics2D g2d = (Graphics2D) graphic;
		int hueState = 0;
		int toWhite = 0;
		int r=240; int g=0; int b=0;
		int rgbIterationPerDegree = (255*12)/360;
		int dr,db,dg;
		dr = db = dg = 0;
		for (int degree=0; degree<360; degree++) {
			toWhite = ((int)(degree/(360/12)))%2;
			hueState = degree/(360/6);
			switch(toWhite) {
				case(0):
					//converging to white
					switch(hueState) {
						case(0):
							//red
							dr = 0;
							dg = 1;
							db = 1;
							//Color(255,0,0);
							break;
						case(1):
							//yellow
							dr = 0;
							dg = 0;
							db = 1;
							//Color(255,255,0);
							break;
						case(2):
							//green
							dr = 1;
							dg = 0;
							db = 1;
							//Color(0, 255, 0);
							break;
						case(3):
							//cyan (or turquoise)
							dr = 1;
							dg = 0;
							db = 0;
							//Color(0, 255, 255);
							break;
						case(4):
							//blue
							dr = 1;
							dg = 1;
							db = 0;
							//Color(0,0,255);
							break;
						case(5):
							//purple
							dr = 0;
							dg = 1;
							db = 0;
							//Color(255,0, 255);
							break;
					}
					break;
				
				case(1):
					// diverging from white
					switch(hueState) {
						case(0):
							//white to yellow
							dr = 0;
							dg = 0;
							db = -1;
							break;
						case(1):
							//ywhite to green
							dr = -1;
							dg = 0;
							db = -1;
							break;
						case(2):
							//white to cyan
							dr = -1;
							dg = 0;
							db = 0;
							break;
						case(3):
							//white to blue
							dr = -1;
							dg = -1;
							db = 0;
							break;
						case(4):
							//white to purple
							dr = 0;
							dg = -1;
							db = 0;
							break;
						case(5):
							//white to red
							dr = 0;
							dg = -1;
							db = -1;
							break;
					}
					break;
			}
			r+=rgbIterationPerDegree*dr;
			g+=rgbIterationPerDegree*dg;
			b+=rgbIterationPerDegree*db;
			Color c = new Color((int)(r*this.shade),(int)(g*this.shade),(int)(b*this.shade));
    		g2d.setColor(c);
	        g2d.fillArc(0, 0, this.diameter, this.diameter, degree, 1);	
		}
		if (this.bi == null) {
			bi = new BufferedImage(this.diameter, this.diameter, BufferedImage.TYPE_INT_ARGB);
			this.paint(bi.getGraphics());
		}
        if (this.mouseInCircle) {
        	this.hoveringColor = new Color(bi.getRGB(this.hoveringPoint.getX(), this.hoveringPoint.getY()));
			g2d.setColor(this.hoveringColor);
			int x = this.hoveringPoint.getX()-(this.selectorSize/2);
			int y = this.hoveringPoint.getY()-(this.selectorSize/2);
			g2d.fillOval(x,y,this.selectorSize, this.selectorSize);
			g2d.setColor(Color.BLACK);
			g2d.drawOval(x,y,this.selectorSize, this.selectorSize);
        }

        g2d.dispose();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		ColorPaletteModel m = (ColorPaletteModel)arg0;
		if ((this.mouseInCircle = m.getMouseInCircle())) {
			this.hoveringPoint = m.getHoveringPoint();
		}
		this.repaint();
		m.setHoveringColor(this.hoveringColor);
	}

}
