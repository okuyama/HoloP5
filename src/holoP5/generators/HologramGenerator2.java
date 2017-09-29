package holoP5.generators;

import processing.core.PApplet;

public class HologramGenerator2 extends HologramGenerator {

	public HologramGenerator2(int width, int height) {
		super(width, height);
	}

    public void prepareForDraw(int frameNum, PApplet parent) {
    	float cameraPositionX, cameraPositionY, cameraPositionZ, cameraUpX, cameraUpY, cameraUpZ;

		// bottom
		// (400.000000,400.000000,692.820313),(400.000000,400.000000,0.000000,),(0.000000,1.000000,0.000000)

    	cameraPositionX = config.cameraPositionX;
		cameraPositionY = config.cameraPositionY;
		cameraPositionZ = config.cameraPositionZ;

		cameraUpX = 0; cameraUpY = -1; cameraUpZ = 0;

    	if (frameNum == 1) {
    		// top
    		cameraUpX = 0; cameraUpY = 1; cameraUpZ = 0;
    	} else if (frameNum == 2) {
            // left
    		cameraUpX = -1; cameraUpY = 0; cameraUpZ = 0;
        } else if (frameNum == 3) {
            // right
    		cameraUpX = 1; cameraUpY = 0; cameraUpZ = 0;
        }
    	
    	parent.camera(cameraPositionX, cameraPositionY, cameraPositionZ,
                config.cameraTargetX, config.cameraTargetY, config.cameraTargetZ,
                cameraUpX, cameraUpY, cameraUpZ);

        parent.frustum(config.frustumLeft, config.frustumRight,
        		config.frustumBottom, config.frustumTop,
        		config.frustumNear, config.frustumFar);
    }
}
