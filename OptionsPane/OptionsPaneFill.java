import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionsPaneFill extends OptionsPaneCards {
	ColorPalette colorPalette;
	OptionsPaneModel opm;
	ColorPaletteView colorPaletteView;
	private static final long serialVersionUID = 1L;

	public OptionsPaneFill(OptionsPaneModel opm) {
		this.opm = opm;
		addBackButton();
		//JPanel centeredColorPalette = new JPanel();
		//centeredColorPalette.setLayout(new BoxLayout(centeredColorPalette,BoxLayout.Y_AXIS));

		this.colorPalette = new ColorPalette();
		this.colorPaletteView = this.colorPalette.getColorPaletteView();
		
		//centeredColorPalette.add(Box.createHorizontalGlue());
		
		//centeredColorPalette.add(this.colorPaletteView);

		//centeredColorPalette.add(Box.createHorizontalGlue());

		//this.add(centeredColorPalette);
		this.add(colorPaletteView);
		ColorPaletteModel colorPaletteModel = this.colorPalette.getColorPaletteModel();
		this.colorPaletteView.addMouseListener(new FillController(colorPaletteModel, opm));
		
		JButton noFill = new JButton("no fill");
		noFill.addActionListener(new NoFillListener(opm));
		this.add(noFill);
		this.add(Box.createVerticalGlue());


//		JPanel noFillPanel = new JPanel();
//		noFillPanel.setLayout(new BoxLayout(noFillPanel,BoxLayout.X_AXIS));
//		JCheckBox noFillButton = new JCheckBox();
//		noFillButton.addActionListener(new NoFillListener(opm));
//		noFillPanel.add(noFillButton);
//		noFillPanel.add(new JLabel("No fill"));
//		this.add(noFillPanel);
		
	}
}
