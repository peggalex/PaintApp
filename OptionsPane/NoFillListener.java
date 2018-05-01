import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoFillListener implements ActionListener{
	OptionsPaneModel opm;

	public NoFillListener(OptionsPaneModel opm) {
		this.opm = opm;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Selected selected = Model.singleton().getSelected();
		selected.setFillColor(new Color(0,0,0,0));
		this.opm.setCurrentCard("main");
	}

}
