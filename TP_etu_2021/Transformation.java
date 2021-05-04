
import algebra.*;

/**
 * author: cdehais
 */
public class Transformation  {

    Matrix worldToCamera;
    Matrix projection;
    Matrix calibration;

    public Transformation () {
        try {
            worldToCamera = new Matrix ("W2C", 4, 4);
            projection = new Matrix ("P", 3, 4);
            calibration = Matrix.createIdentity (3);
            calibration.setName ("K");
        } catch (InstantiationException e) {
            /* should not reach */
        }
    }

    public void setLookAt (Vector3 cam, Vector3 lookAt, Vector3 up) {
        try {
        // compute rotation
        e1_c = up.cross(lookAt);
        e2_c = up;
        e3_c = lookAt;

        // normalize
        e1_c.normalize();
        e2_c.normalize();
        e3_c.normalize();
        
        Matrix N = new Matrix("N", 3, 3);
        N.set(0,0) = e1_c.getX();
        N.set(1,0) = e1_c.getY();
        N.set(2,0) = e1_c.getZ();

        N.set(0,1) = e2_c.getX();
        N.set(1,1) = e2_c.getY();
        N.set(2,1) = e2_c.getZ();

        N.set(0,2) = e3_c.getX();
        N.set(1,2) = e3_c.getY();
        N.set(2,2) = e3_c.getZ();

        // compute translation
        Vector3 t = N.transpose().multiply(cam);
        t.scale(-1);    // multiply by -1
        
        // assemble worldToCamera
        for (int i=0; i<N.nRows; i++) {
            for (int j=0; j<N.nCols; j++) {
                worldToCamera.set(i,j,N.get(i,j));
            }
        }

        worldToCamera.set(0,3,t.getX());
        worldToCamera.set(1,3,t.getY());
        worldToCamera.set(2,3,t.getZ());
        worldToCamera.set(3,3,1);
        
        } catch (Exception e) { /* unreached */ };
        
        System.out.println ("Modelview matrix:\n" + worldToCamera);
    }

    public void setProjection () {


	/* A COMPLETER */

        System.out.println ("Projection matrix:\n" + projection);
    }

    public void setCalibration (double focal, double width, double height) {


	/* à compléter */

	System.out.println ("Calibration matrix:\n" + calibration);
    }

    /**
     * Projects the given homogeneous, 4 dimensional point onto the screen.
     * The resulting Vector as its (x,y) coordinates in pixel, and its z coordinate
     * is the depth of the point in the camera coordinate system.
     */  
    public Vector3 projectPoint (Vector p)
        throws SizeMismatchException, InstantiationException {
	Vector ps = new Vector(3);

        /* à compléter */

	return new Vector3 (ps);
    }
    
    /**
     * Transform a vector from world to camera coordinates.
     */
    public Vector3 transformVector (Vector3 v)
        throws SizeMismatchException, InstantiationException {
        /* Doing nothing special here because there is no scaling */
        Matrix R = worldToCamera.getSubMatrix (0, 0, 3, 3);
        Vector tv = R.multiply (v);
        return new Vector3 (tv);
    }
    
}

