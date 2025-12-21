package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
public abstract class BaseAutoRoadrunner extends LinearOpMode {
    protected WebcamName webcamName;
    protected CameraMonitor cameraMonitor;

    protected void RunInit()
    {
    }

    protected abstract void RunOpModeInnerLoop();

    @Override
    public void runOpMode() {


        waitForStart();
        if (opModeIsActive()) {
        }

    }
}

