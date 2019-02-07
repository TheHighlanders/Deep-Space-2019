/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.grip;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.cameraserver.CameraServer;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

import frc.robot.grip.JavaPipeline;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * Add your docs here.
 */
public class VisionProcessing {

Thread visionThread;
NetworkTableEntry lineQuant;

    public VisionProcessing() {
        visionThread = new Thread(() -> {
            // Get the Axis camera from CameraServer
            AxisCamera camera
                = CameraServer.getInstance().addAxisCamera("10.62.1.15");
            // Set the resolution
            camera.setResolution(640, 480);
      
            // Get a CvSink. This will capture Mats from the camera
            CvSink cvSink = CameraServer.getInstance().getVideo();
            // Setup a CvSource. This will send images back to the Dashboard
            CvSource outputStream = CameraServer.getInstance().putVideo("Output", 640, 480);
      
            // Mats are very memory expensive. Lets reuse this Mat.
            Mat mat = new Mat();
      
            JavaPipeline pipe = new JavaPipeline();
            // This cannot be 'true'. The program will never exit if it is. This
            // lets the robot stop this thread when restarting robot code or
            // deploying.

            NetworkTableInstance inst = NetworkTableInstance.getDefault();
            NetworkTable visionTable = inst.getTable("visionTable");
            lineQuant = visionTable.getEntry("lineQuant");


            while (!Thread.interrupted()) {
              // Tell the CvSink to grab a frame from the camera and put it
              // in the source mat.  If there is an error notify the output.
              if (cvSink.grabFrame(mat) == 0) {
                // Send the output the error.
                outputStream.notifyError(cvSink.getError());
                // skip the rest of the current iteration
                continue;
              }
              
              pipe.process(mat);

              DriverStation.reportWarning("Number of qualifying lines found: " + pipe.filterLinesOutput().size(), false);
              //lineQuant.setDouble(pipe.filterLinesOutput().size());
              lineQuant.setDouble(3.14159265358979);


              // Give the output stream a new image to display
              outputStream.putFrame(mat);
            }
          });
          visionThread.setDaemon(true);
          visionThread.start();
    }
}
