import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OutlineController implements MouseListener {
	ColorPaletteModel colorModel;
	OptionsPaneModel opm;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Selected selected = Model.singleton().getSelected();
		selected.setOutlineColor(this.colorModel.getHoveringColor());
		this.opm.setCurrentCard("main");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public OutlineController(ColorPaletteModel colorModel, OptionsPaneModel opm) {
		this.colorModel = colorModel;
		this.opm = opm;
	}
}
