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
public class splineMoveAutoBlue extends LinearOpMode {
    private DcMotor flinger;
    private DcMotor flinger2;
    private static ElapsedTime stopWatch = new ElapsedTime();





    public void shoot()
    {
        stopWatch.reset();
        while (stopWatch.seconds() < 1 )
        {
            flinger.setPower(-1);
            flinger2.setPower(1);

        }

        stopWatch.reset();
        while (stopWatch.seconds() < 0.5 )
        {
            flinger.setPower(1);
            flinger2.setPower(- 1);

        }


    }

    @Override
    public void runOpMode() {


        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(new Vector2d(61,  -12), Math.toRadians( -180)));

        //  DcMotor motor1 = hardwareMap.get(DcMotor.class,  "motor");
        flinger = hardwareMap.get(DcMotor.class, "flinger");
        flinger2 = hardwareMap.get(DcMotor.class, "flinger2");
        flinger.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flinger2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Pose2d endPose = new Pose2d(new Vector2d(-47,  -52 ), Math.toRadians( -490));
        Pose2d beginPose = new Pose2d(new Vector2d(61, -12), Math.toRadians(-180));

        // Delcare Trajectory as such
        Action TrajectoryAction1 = drive.actionBuilder(drive.localizer.getPose())

                .splineToSplineHeading(endPose, 98.5)
                .build();

        Action TrajectoryAction2 = drive.actionBuilder(endPose)

                .strafeTo(new Vector2d(-50, -15))
                .turnTo(3.5)
                .build();


        while (!isStopRequested() && !opModeIsActive()) {

        }

        waitForStart();

        if (isStopRequested()) return;
        sleep(20000);

        Actions.runBlocking(
                new SequentialAction(
                        TrajectoryAction1, // Example of a drive action


                        // Only that this action uses a Lambda expression to reduce complexity
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            shoot();
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        new ParallelAction( // several actions being run in parallel
                                TrajectoryAction2, // Run second trajectory
                                (telemetryPacket) -> { // Run some action

                                    // motor1.setPower(1);
                                    return false;
                                }
                        )

                )
        );


    }

}

