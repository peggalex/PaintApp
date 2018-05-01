import java.awt.Image;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class OptionsPaneCards extends JPanel{
	private static final long serialVersionUID = 1L;
	protected HashMap<JButton, String> jButtonToString;
	static int backButtonSize = 50;
	
	public OptionsPaneCards() {
		this.jButtonToString = new HashMap<>();
	}
	
	public void installOptionsPaneModel(OptionsPaneModel opm) {
		for (JButton jb: jButtonToString.keySet()) {
			String s = jButtonToString.get(jb);
			jb.addActionListener(new OptionsPaneController(opm, s));
		}
	}
	
	public void addBackButton() {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JPanel backButton = new JPanel();
		backButton.setLayout(new BoxLayout(backButton,BoxLayout.X_AXIS));
		Image i = new ImageIcon(OptionsPane.class.getResource("backArrowTransparent.png"), "back").getImage();
		Image iScaled = i.getScaledInstance(backButtonSize, backButtonSize, java.awt.Image.SCALE_SMOOTH);
		JButton jb = new JButton(new ImageIcon(iScaled));
		this.jButtonToString.put(jb,"main");
		jb.setOpaque(false);
		jb.setContentAreaFilled(false);
		jb.setBorderPainted(false);
		backButton.add(jb);
		backButton.add(Box.createHorizontalGlue());
		backButton.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(backButton);
	}
}
