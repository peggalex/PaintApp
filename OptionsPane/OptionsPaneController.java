import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPaneController implements ActionListener{
	private OptionsPaneModel opm;
	private String cardDesc;
	
	public OptionsPaneController(OptionsPaneModel opm, String cardDesc) {
		this.cardDesc = cardDesc;
		this.opm = opm;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		opm.setCurrentCard(cardDesc);
	}

	
}
