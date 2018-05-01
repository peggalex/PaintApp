import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ThicknessController implements ChangeListener{
	private JSlider slider;
	private Selected selected;
	
	public ThicknessController(JSlider slider) {
		this.slider = slider;
		this.selected = Model.singleton().getSelected();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		this.selected.setLineThickness(this.slider.getValue());	
	}

}
