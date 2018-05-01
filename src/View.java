import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame{
	protected static final long serialVersionUID = 1L;
	private static View view = new View();
	private OptionsPane optionsPane;
	private Canvas canvas;
	static Dimension canvasSize = new Dimension(1000,1000);
	static Color selectedColor= new Color(0,0,255,128);
	
	private View() {
		super("Paint"); // set the title and do other JFrame init
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(new MenuBar()); // add menubar
		//this.setPreferredSize(new Dimension(1000,1000));
		Container c=this.getContentPane();
		c.setBackground(OptionsPane.backgroundColor);
		c.setLayout(new BoxLayout(c,BoxLayout.X_AXIS));
		c.add(this.optionsPane = new OptionsPane(this)); // add options pane
		c.add(this.canvas = new Canvas()); // add canvas
		this.canvas.setFocusable(true);
		this.pack();
		//this.setSize(new Dimension(1200,1000));
		enable();
	}

	public JPanel getCanvas() {
		return this.canvas;
	}
	
	public void enable() {
		this.setVisible(true);
	}
	
	public static View singleton() {
		return view;
	}
	
}
