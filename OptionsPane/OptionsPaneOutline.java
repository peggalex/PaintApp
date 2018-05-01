import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class OptionsPaneOutline extends OptionsPaneCards {
	ColorPalette colorPalette;
	OptionsPaneModel opm;
	ColorPaletteView colorPaletteView;
	private static final long serialVersionUID = 1L;

	public OptionsPaneOutline(OptionsPaneModel opm) {
		addBackButton();
		JPanel centeredColorPalette = new JPanel();
		centeredColorPalette.setLayout(new BoxLayout(centeredColorPalette,BoxLayout.Y_AXIS));
		this.colorPalette = new ColorPalette();
		this.colorPaletteView = this.colorPalette.getColorPaletteView();
		centeredColorPalette.add(Box.createHorizontalGlue());
		centeredColorPalette.add(this.colorPaletteView);
		centeredColorPalette.add(Box.createHorizontalGlue());
		ColorPaletteModel cpm = this.colorPalette.getColorPaletteModel();
		this.colorPaletteView.addMouseListener(new OutlineController(cpm,opm));
		this.add(centeredColorPalette);
		
	}
}
