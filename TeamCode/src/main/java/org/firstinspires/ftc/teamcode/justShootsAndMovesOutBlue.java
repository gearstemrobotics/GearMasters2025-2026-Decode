package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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


@Autonomous
public class justShootsAndMovesOutBlue extends LinearOpMode {
    private DcMotor flinger;
    private DcMotor flinger2;
    private static ElapsedTime stopWatch = new ElapsedTime();





    public void shoot()
    {
        stopWatch.reset();
        while (stopWatch.seconds() < 1.5 )
        {
            flinger.setPower(-1);
            flinger2.setPower(1);

        }

        stopWatch.reset();
        while (stopWatch.seconds() < 1 )
        {
            flinger.setPower(1);
            flinger2.setPower(- 1);

        }


    }

    @Override
    public void runOpMode() {


        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(new Vector2d(-48, -48), Math.toRadians(-490)));

        //  DcMotor motor1 = hardwareMap.get(DcMotor.class,  "motor");
        flinger = hardwareMap.get(DcMotor.class, "flinger");
        flinger2 = hardwareMap.get(DcMotor.class, "flinger2");
        flinger.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flinger2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

       // Pose2d endPose = new Pose2d(new Vector2d(10, -23), Math.toRadians(80));
      //  Pose2d beginPose = new Pose2d(new Vector2d(60, -12), Math.toRadians(-180));


        // Delcare Trajectory as such
        Action TrajectoryAction1 = drive.actionBuilder(drive.localizer.getPose())
                .strafeTo(new Vector2d(-50, -15))
                .turnTo(3.5)
                .build();

       // Action TrajectoryAction2 = drive.actionBuilder(new Pose2d(15, 20, 0))

                //.splineTo(new Vector2d(5,5), Math.toRadians(90))
               // .build();


        while (!isStopRequested() && !opModeIsActive()) {

        }

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new ParallelAction(
                        (telemetryPacket) -> { // Run some action
                            shoot();
                            // motor1.setPower(1);
                            return false;
                        },
                        TrajectoryAction1, // Example of a drive action

                        // Only that this action uses a Lambda expression to reduce complexity
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        }


                )
        );


    }

}

