

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FillShape extends Shape {
	boolean[][] dotsInBound;
	Point[] dotBorders;
	BufferedImage filledArea;
	BufferedImage filledAreaSelected;
	Point imgOffset;
	int numPoints;

	public FillShape() {
		super();
	}
	
	public void setDotsInBound(boolean[][] dotsInBound) {
		this.dotsInBound = dotsInBound;
	}
	
	public void setDotBorder(Point[] dotBorders) {
		this.dotBorders = dotBorders;
	}
	
	public Point[] getDotBorders() {
		return this.dotBorders;
	}
	
	public void setBufferedImage(BufferedImage bi, BufferedImage biSelectColor, Point imgOffset, int numPoints) {
		this.filledArea = bi;
		this.filledAreaSelected = biSelectColor;
		this.imgOffset = imgOffset.getCopy();
		this.numPoints = numPoints;
	}
	
	public int getNumPoints() {
		return numPoints;
	}
	
	public BufferedImage getFilledArea() {
		return this.filledArea;
	}
	
	public BufferedImage getFilledAreaSelected() {
		return this.filledAreaSelected;
	}
	
	public Point getImgOffset() {
		return this.imgOffset;
	}
	
	public FillShape getCopy() {
		FillShape copy = new FillShape();
		copy.setBufferedImage(this.filledArea,this.filledAreaSelected, this.imgOffset,this.numPoints);
		return copy;
	}
	
	@Override
	public boolean getPointInShape(Point p) {
		int xReal = p.getX()-this.shift.getX();
		int yReal = p.getY()-this.shift.getY();
		if (yReal>=imgOffset.getY() && yReal<dotsInBound.length &&
				xReal>=imgOffset.getX() && xReal<dotsInBound[0].length) {
				return dotsInBound[yReal][xReal];
		} else {
			return false;
		}
	}
	
	@Override
	public void paintComponent(Graphics2D g2d) {
		super.paintComponent(g2d);
		FillShape ds = this;
		Point offsetShiftSum = Point.addPoints(ds.getImgOffset(),ds.getShift());
		g2d.drawImage(ds.getFilledArea(), offsetShiftSum.getX(), offsetShiftSum.getY(), null);
		if (ds.getIsSelected()) {
			g2d.drawImage(ds.getFilledAreaSelected(), offsetShiftSum.getX(), offsetShiftSum.getY(), null);
		}
	}
	
	
}
