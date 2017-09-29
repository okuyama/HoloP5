package holoP5.generators;

import java.util.Arrays;

import camera3D.generators.HoloP5Generator;
import processing.core.PApplet;
import processing.core.PConstants;

public class HologramGenerator extends HoloP5Generator implements PConstants {
    int width;
    int height;
 
    public HologramGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }

	@Override
	public void generateCompositeFrame(int[] pixelDest, int[][] pixelStorage) {
        for (int y = height / 3; y < height * 2 / 3; ++y) {
            // remove original image.
            Arrays.fill(pixelDest, y * width + (width / 3), y * width + (width * 2 / 3), 0);

            // right
            System.arraycopy(pixelStorage[3], y * width + (width / 3),
            		pixelDest, y * width + (width * 2 / 3), width / 3);

        	// left
            System.arraycopy(pixelStorage[2], y * width + (width / 3),
            		pixelDest, y * width, width / 3);
        
            // top
        	System.arraycopy(pixelStorage[1], y * width + (width / 3),
                    pixelDest, (y - (height / 3)) * width + (width / 3), width / 3);

            // bottom
        	System.arraycopy(pixelStorage[0], y * width + (width / 3),
                    pixelDest, (y + (height / 3)) * width + (width / 3), width / 3);
        }

	}

    public int getComponentCount() {
        return 4;
    }

    public String getComponentFrameName(int frameNum) {
        if (frameNum == 0) {
            return "bottom";
        } else if (frameNum == 1) {
            return "top";
        } else if (frameNum == 2) {
            return "left";
        } else if (frameNum == 3) {
            return "right";
        } else {
            return "";
        }
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
    		cameraPositionZ = -config.cameraPositionZ;

    		cameraUpX = 0; cameraUpY = 1; cameraUpZ = 0;
    	} else if (frameNum == 2) {
            // left
    		cameraPositionX = config.cameraPositionX + config.cameraPositionZ;
    		cameraPositionZ = 0;

    		cameraUpX = 0; cameraUpY = 0; cameraUpZ = 1;
        } else if (frameNum == 3) {
            // right
    		cameraPositionX = config.cameraPositionX - config.cameraPositionZ;
    		cameraPositionZ = 0;

    		cameraUpX = 0; cameraUpY = 0; cameraUpZ = 1;
        }
    	
    	parent.camera(cameraPositionX, cameraPositionY, cameraPositionZ,
                config.cameraTargetX, config.cameraTargetY, config.cameraTargetZ,
                cameraUpX, cameraUpY, cameraUpZ);

        parent.frustum(config.frustumLeft, config.frustumRight,
        		config.frustumBottom, config.frustumTop,
        		config.frustumNear, config.frustumFar);
    }

    public void completedDraw(int frameNum, PApplet parent) {
        // do nothing
    }

    public void cleanup(PApplet parent) {
        parent.camera(config.cameraPositionX, config.cameraPositionY,
                config.cameraPositionZ, config.cameraTargetX,
                config.cameraTargetY, config.cameraTargetZ, config.cameraUpX,
                config.cameraUpY, config.cameraUpZ);

        parent.frustum(config.frustumLeft, config.frustumRight,
                config.frustumBottom, config.frustumTop, config.frustumNear,
                config.frustumFar);
    }

	@Override
	protected void recalculateCameraSettings() {
        // do nothing
	}

}
