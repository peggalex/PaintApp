import java.util.Observable;
import java.util.Observer;

public class PaintApp {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PaintApp();
			}
		});
	}
	//made some changes
	Model model; // Model
	View view; // View+Controller

	public PaintApp() {
		// Create MVC components and hook them together

		// Model
		//View+Controller
		model = Model.singleton();
		view = View.singleton();
		model.addObserver((Observer)view.getCanvas());
		view.enable();
	}
}
