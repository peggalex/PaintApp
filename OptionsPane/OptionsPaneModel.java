import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.JButton;

public class OptionsPaneModel extends Observable{
	private String currentCard;
	
	public void setCurrentCard(String s) {
		this.currentCard = s;
		this.setChanged();
		this.notifyObservers(s);
	}
	
	public String getCurrentCard() {
		return this.currentCard;
	}
}
