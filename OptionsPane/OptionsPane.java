import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class OptionsPane extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;
	static Font defaultFont = new Font("Arial", Font.PLAIN, 24);
	static Color backgroundColor = new Color(0,175,255);
	static Dimension paneSize = new Dimension(400, 1000);
	private OptionsPaneModel optionsModel;
	private HashMap<String, OptionsPaneCards> optionPanes;
	
	public OptionsPane(View v) {
		super();
		this.setPreferredSize(paneSize);
		this.setBackground(backgroundColor);
		this.setLayout(new CardLayout());
		this.optionsModel = new OptionsPaneModel();
		optionPanes = new HashMap<>();
		optionPanes.put("main",new OptionsPaneMain());
		optionPanes.put("tools", new OptionsPaneTools(v));
		optionPanes.put("outline", new OptionsPaneOutline(this.optionsModel));
		optionPanes.put("fill", new OptionsPaneFill(this.optionsModel));
		Selected selected = Model.singleton().getSelected();
		selected.addObserver((OptionsPaneMain)optionPanes.get("main"));
		for (String s: optionPanes.keySet()) {
			this.add(optionPanes.get(s),s);
		}
		this.setAlignmentY(CENTER_ALIGNMENT);
		this.optionsModel.addObserver(this);
		for (OptionsPaneCards c: optionPanes.values()) {
			c.installOptionsPaneModel(this.optionsModel);
		}
		CardLayout cl = (CardLayout)this.getLayout();
		cl.show(this, "main");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		CardLayout cl = (CardLayout)this.getLayout();
		cl.show(this, (String)arg1);
	}
	
	
}
