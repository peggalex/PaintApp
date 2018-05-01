import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OptionsPaneTools extends OptionsPaneCards{
	private static final long serialVersionUID = 1L;
	static Dimension buttonSize = new Dimension(100,100);
	static int backButtonSize = 50;
	
	public OptionsPaneTools(View v) {
		super();
		addBackButton();
		JPanel buttonPanel = new JPanel();
		HashMap<String,ImageIcon> icons  = Model.icons;
		int size = icons.keySet().size();
		int rows = (size%2==0) ? size/2 : (size+1)/2;
		buttonPanel.setLayout(new GridLayout(rows,2));
		for (String s: icons.keySet()) {
			JButton jb = new JButton(icons.get(s));
			this.jButtonToString.put(jb,"main");
			jb.setBackground(Color.WHITE);
			buttonPanel.add(jb);
			jb.addActionListener(new ToolListener(s, v));
		}
		this.add(buttonPanel);
		this.add(Box.createVerticalGlue());
	}
	
}
