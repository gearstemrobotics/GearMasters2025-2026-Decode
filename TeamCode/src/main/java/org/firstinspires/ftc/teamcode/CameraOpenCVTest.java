package org.firstinspires.ftc.teamcode;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

public class CameraOpenCVTest {

    OpenCvCamera camera; 
    static
    {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args)
    {

System.out.println("Open CV version: " + Core.VERSION);

Mat mat = Mat.eye(3,3,CvType.CV_32FC1);
System.out.println("mat = "+mat.dump());

    }


}
