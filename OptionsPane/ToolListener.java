import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ToolListener implements ActionListener{
	String tool;
	View v;
	
	public ToolListener(String s, View v) {
		this.tool = s;
		this.v = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Selected selected = Model.singleton().getSelected();
		selected.setTool(this.tool);
		ToolController tl = ToolController.factory(this.tool, this.v);
		JPanel jpanel = this.v.getCanvas();
		if (jpanel.getMouseListeners().length!=0){
			for (MouseListener ml: jpanel.getMouseListeners()) {
				jpanel.removeMouseListener(ml);
			}
			for (MouseMotionListener mml: jpanel.getMouseMotionListeners()) {
				jpanel.removeMouseMotionListener(mml);
			}
			for (KeyListener kl: jpanel.getKeyListeners()) {
				jpanel.removeKeyListener(kl);
			}
			Model.singleton().unselectShapes();
		}
		jpanel.addMouseListener(tl);
		jpanel.addMouseMotionListener(tl);
		jpanel.addKeyListener(tl);
	}
	
}
