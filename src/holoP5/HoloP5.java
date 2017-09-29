package holoP5;

import camera3D.Camera3D;
import holoP5.generators.HologramGenerator;
import holoP5.generators.HologramGenerator2;
import processing.core.PApplet;

public class HoloP5 extends Camera3D {
    private PApplet parent;

    public HoloP5(PApplet parent) {
		super(parent);
        this.parent = parent;
	}
	
    public HologramGenerator renderHologram() {
        HologramGenerator generator = new HologramGenerator(parent.width, parent.height);

        setGenerator(generator);

        return generator;
    }

    public HologramGenerator2 renderHologram2() {
        HologramGenerator2 generator = new HologramGenerator2(parent.width, parent.height);

        setGenerator(generator);

        return generator;
    }
}
