
import java.lang.Math;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

public class PaintBucketStrategy extends ShapeController {
	
	public PaintBucketStrategy(View v) {
		super(v);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point cursorPoint = new Point(e.getX(), e.getY());
		int paintPanelWidth = (int)View.canvasSize.getWidth();
		int paintPanelHeight = (int)View.canvasSize.getHeight();
		BufferedImage bufferedImage = new BufferedImage(paintPanelWidth, paintPanelHeight,
	            BufferedImage.TYPE_INT_ARGB); //takes what's effectively a screenshot which can be analysed pixel by pixel
		view.getCanvas().paint(bufferedImage.getGraphics());
		BufferedImage filledArea = new BufferedImage(paintPanelWidth, paintPanelHeight,
	            BufferedImage.TYPE_INT_ARGB);
		BufferedImage filledAreaSelected = new BufferedImage(paintPanelWidth, paintPanelHeight,
	            BufferedImage.TYPE_INT_ARGB);
		Color seeThrough = new Color(0,0,0,0);
		Graphics2D g2dFilledArea = filledArea.createGraphics();
		Graphics2D g2dFilledAreaSel = filledAreaSelected.createGraphics();
		g2dFilledArea.setColor(seeThrough);
		g2dFilledAreaSel.setColor(seeThrough);
		g2dFilledArea.fillRect(0, 0, paintPanelWidth, paintPanelHeight);
		g2dFilledAreaSel.fillRect(0, 0, paintPanelWidth, paintPanelHeight);
		//create two blank bufferedImages - same size as the original screenshot, but they are filled with a
		// transparent color. I'll paint over them when I identify correct pixels
		Color fillColor = new Color(bufferedImage.getRGB(cursorPoint.getX(), cursorPoint.getY())); //get the pixel we're trying to fill
		FillShape dotShape = new FillShape();
		setFilledShape(dotShape, bufferedImage, cursorPoint, fillColor, filledArea, filledAreaSelected);
		model.addShape(dotShape);
		// we want to set the shape after, because DotShape does not actually setChanged
		// and notifyObservers when dots are added to improve runtime, as there are many dots added
	}

	
	private void setFilledShape(FillShape dotShape, BufferedImage bufferedImage, Point cursorPoint, Color fillColor, BufferedImage filledArea, BufferedImage filledAreaSelected) {
		
		//long startTime = System.currentTimeMillis();
		boolean[][] yXCheckedColor = new boolean[bufferedImage.getHeight()][bufferedImage.getWidth()];
		boolean[][] yXIsInBound = new boolean[bufferedImage.getHeight()][bufferedImage.getWidth()];
		Point[] directions = {new Point(1,0), new Point(0,1), new Point(-1,0), new Point(0,-1)};
		// these are ordered specifically to go counter clockwise
		Stack<Point> pointStack = new Stack<>();
		pointStack.add(cursorPoint);
		int numPoints=0;
		Point minXY = null; Point maxXY = null;
		Point currentPoint = cursorPoint;
		Point previousPoint, newPoint;
		
		while (!(pointStack.isEmpty())) {
			previousPoint = currentPoint;
			currentPoint = pointStack.pop();
			
			for (int direction=0; direction<4 ; direction++) {
				int dx = directions[direction].getX();
				int dy = directions[direction].getY();	
				newPoint = new Point(currentPoint.getX()+dx, currentPoint.getY()+dy);
				
				if (newPoint.getX()>=0 && newPoint.getY()>=0 && 
						newPoint.getX()<bufferedImage.getWidth()-1 && 
						newPoint.getY()<bufferedImage.getHeight()-1 && 
						!(newPoint.equals(previousPoint))) {
					
							if (!(yXCheckedColor[newPoint.getY()][newPoint.getX()])) {
								Color bufferedImageColor = new Color(bufferedImage.getRGB(newPoint.getX(), newPoint.getY()));
								
								if (fillColor.equals(bufferedImageColor)) {
									
									if (minXY==null) {
										minXY = newPoint.getCopy();
										maxXY = newPoint.getCopy();
									} else {
										updateMinMax(minXY, maxXY, newPoint);
									}
									
									numPoints++;
									pointStack.add(newPoint);
									yXIsInBound[newPoint.getY()][newPoint.getX()] = true;
									int colorToFill = model.getSelected().getFillColor().getRGB();
									filledArea.setRGB(newPoint.getX(), newPoint.getY(), colorToFill);
									// this bufferedImage is going to be colored as expected
									filledAreaSelected.setRGB(newPoint.getX(), newPoint.getY(), View.selectedColor.getRGB());
									// this bufferedImage is colored transparent blue - the color of a 
									// selected component in my app
								}
								yXCheckedColor[newPoint.getY()][newPoint.getX()] = true;
								}
							}
			}
		}
//		for (int y=minXY.getY();y<=maxXY.getY();y++) {
//			for (int x=minXY.getX();x<=maxXY.getX();x++) {
//				if (!yXIsInBound[y][x]) {
//					bufferedImage.setRGB(x, y,new Color(0,0,0,0).getRGB());
//				}
//			}
//		}
		int width = maxXY.getX()-minXY.getX()+1;
		int height = maxXY.getY()-minXY.getY()+1;
		BufferedImage croppedFilledArea = filledArea.getSubimage(minXY.getX(), minXY.getY(), width, height);
		BufferedImage croppedFilledAreaSel = filledAreaSelected.getSubimage(minXY.getX(), minXY.getY(), width, height);
		dotShape.setBufferedImage(croppedFilledArea,croppedFilledAreaSel,minXY.getCopy(),numPoints);
		dotShape.setDotsInBound(yXIsInBound);
	}

	private void updateMinMax(Point oldMin, Point oldMax, Point currentP) {
		if (currentP.getX()<oldMin.getX()) {
			oldMin.setX(currentP.getX());
		} else if (currentP.getX()>oldMax.getX()) {
			oldMax.setX(currentP.getX());
		}
		if (currentP.getY()<oldMin.getY()) {
			oldMin.setY(currentP.getY());
		} else if (currentP.getY()>oldMax.getY()) {
			oldMax.setY(currentP.getY());
		}
	}

}
