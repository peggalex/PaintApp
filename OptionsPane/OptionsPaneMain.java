import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class OptionsPaneMain extends OptionsPaneCards implements Observer{
	private static final long serialVersionUID = 1L;
	private JButton toolButton;
	private JButton outlineButton;
	private JButton fillButton;
	private JSlider thicknessSlider;
	static int sliderTicks = 100;
	static int heightBetweenButtons = 100;
	static int heightBetweenText = 10;
	static Dimension buttonSize = new Dimension(100,100);
	static Dimension thicknessSliderSize = new Dimension (300, 20);
	static Dimension paneSize = new Dimension(400, 1000);
	static Font defaultFont = new Font("Arial", Font.PLAIN, 24);
	static Color backgroundColor = new Color(0,175,255);

	public OptionsPaneMain() {
		super();
		this.setPreferredSize(paneSize);
		this.setBackground(backgroundColor);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel[] jLabels = {new JLabel("Tool"), new JLabel("Outline"), new JLabel("Fill"),new JLabel("Thickness")};
		for (JLabel l: jLabels) {
			l.setFont(defaultFont);
			l.setAlignmentX(CENTER_ALIGNMENT);
		}
	    this.add(Box.createRigidArea(new Dimension(0,heightBetweenButtons)));
		
		this.add(jLabels[0]);
		this.add(Box.createRigidArea(new Dimension(0,heightBetweenText)));
		this.add(this.toolButton=new JButton());
		this.toolButton.setOpaque(false);
		this.toolButton.setContentAreaFilled(false);
		//this.toolButton.setBorderPainted(false);
	    this.add(Box.createRigidArea(new Dimension(0,heightBetweenButtons)));
		
		this.add(jLabels[1]);
		this.add(Box.createRigidArea(new Dimension(0,heightBetweenText)));
		this.add(this.outlineButton=new JButton());
	    this.add(Box.createRigidArea(new Dimension(0,heightBetweenButtons)));
		
		this.add(jLabels[2]);
		this.add(Box.createRigidArea(new Dimension(0,heightBetweenText)));
		this.add(this.fillButton=new JButton());
	    this.add(Box.createRigidArea(new Dimension(0,heightBetweenButtons)));
		
		this.add(jLabels[3]);
		this.add(Box.createRigidArea(new Dimension(0,heightBetweenText)));
		this.add((this.thicknessSlider = new JSlider(1,sliderTicks,1)));
		this.thicknessSlider.setMaximumSize(thicknessSliderSize);
		this.thicknessSlider.setBackground(backgroundColor);
		this.thicknessSlider.addChangeListener(new ThicknessController(this.thicknessSlider));
		
		JButton[] jButtons = {this.toolButton, this.outlineButton, this.fillButton};
		this.jButtonToString.put(this.toolButton, "tools");
		this.jButtonToString.put(this.outlineButton, "outline");
		this.jButtonToString.put(this.fillButton, "fill");
		for (JButton b: jButtons){
			b.setMaximumSize(buttonSize);
			b.setAlignmentX(CENTER_ALIGNMENT);
			b.setBackground(Color.WHITE);
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		Selected selected = (Selected)o;
		this.outlineButton.setBackground(selected.getOutlineColor());
		// java swing has really weird interactions with opaque components
		// so we're going to manually change the color to what the button should
		// be if it it's supposed to be transparent
		if (selected.getFillColor().getAlpha()==0) {
			this.fillButton.setBackground(backgroundColor);
		} else {
			this.fillButton.setBackground(selected.getFillColor());
		}
		this.toolButton.setIcon(selected.getImageIcon());
	}
	
}
