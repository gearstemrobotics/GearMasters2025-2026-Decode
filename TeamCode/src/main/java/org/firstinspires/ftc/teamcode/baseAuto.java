package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class baseAuto extends LinearOpMode {




    protected DcMotor flinger;
    protected DcMotor flinger2;
    protected DcMotor shooter;
    protected static ElapsedTime stopWatch = new ElapsedTime();
    protected VoltageSensor voltageSensor;

    protected Pose2d beginPose = null;



    baseAuto() {}


    protected void startPose(double x, double y, double rad)
    {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(new Vector2d(x, y), Math.toRadians(rad)));
        Pose2d beginPose =   new Pose2d(new Vector2d(x, y), Math.toRadians(rad));


    }




    protected void shoot()
    {

        double voltage = voltageSensor.getVoltage();
        double voltageScaler = 12/voltage;
        stopWatch.reset();
        while (stopWatch.seconds() < 0.4 )
        {

            flinger.setPower(-voltageScaler);
            flinger2.setPower(voltageScaler);

        }

        stopWatch.reset();
        while (stopWatch.seconds() < 0.2 )
        {
            flinger.setPower(voltageScaler);
            flinger2.setPower(-voltageScaler);

        }


    }
    protected void RunInit()
    {
    }

    protected abstract void RunOpModeInnerLoop();

    @Override
    public void runOpMode() {

        Map();

        RunInit();

        waitForStart();
        if (opModeIsActive()) {
           PrepMotor();
            RunOpModeInnerLoop();
        }
    }


    protected void Map() {
        flinger = hardwareMap.get(DcMotor.class, "flinger");
        flinger2 = hardwareMap.get(DcMotor.class, "flinger2");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
    }

    protected void PrepMotor() {

        flinger.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flinger2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}



