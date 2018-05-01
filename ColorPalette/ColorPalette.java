
public class ColorPalette {
	ColorPaletteModel m;
	ColorPaletteView v;
	ColorPaletteController c;
	
	public ColorPalette() {
		this.m = new ColorPaletteModel();
		this.v = new ColorPaletteView(400);
		this.c = new ColorPaletteController(Model.singleton(),v,m);
		m.addObserver(v);
		v.addMouseMotionListener(c);
	}
	
	public ColorPaletteView getColorPaletteView() {
		return this.v;
	}
	
	public ColorPaletteModel getColorPaletteModel() {
		return this.m;
	}
}
