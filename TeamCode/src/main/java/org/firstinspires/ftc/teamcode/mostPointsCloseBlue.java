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
public class mostPointsCloseBlue extends LinearOpMode {
    private DcMotor flinger;
    private DcMotor flinger2;
    private DcMotor shooter;
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
        while (stopWatch.seconds() < 0.5  )
        {
            flinger.setPower(1);
            flinger2.setPower(- 1);

        }


    }

    @Override
    public void runOpMode() {


        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(new Vector2d(-47, -51), Math.toRadians(-490)));

        //  DcMotor motor1 = hardwareMap.get(DcMotor.class,  "motor");
        flinger = hardwareMap.get(DcMotor.class, "flinger");
        flinger2 = hardwareMap.get(DcMotor.class, "flinger2");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flinger.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flinger2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Pose2d endPose = new Pose2d(new Vector2d(-47, -52), Math.toRadians(-490));
        //Pose2d beginPose = new Pose2d(new Vector2d(61, 12), Math.toRadians(180));
        Pose2d beginPose =   new Pose2d(new Vector2d(-47, -51), Math.toRadians(-490));
        Pose2d test = new Pose2d(10,-23,-90);
        Pose2d firstBalls = new Pose2d(-11,-24,-1.57);
        Pose2d secondBalls = new Pose2d(12,-24,-1.57);

        // Delcare Trajectory as such
        Action collectBalls1 = drive.actionBuilder(drive.localizer.getPose())
                // first ball collection
                .strafeTo(new Vector2d(-46, -47))
                .splineToSplineHeading(firstBalls,100.1)
                .strafeTo(new Vector2d(-9, -35))
                .waitSeconds(0.1)
                .strafeTo(new Vector2d(-9, -42 ))
                .waitSeconds(0.1)
                .strafeTo(new Vector2d(-9, -54))

                .strafeTo(new Vector2d(-9, -24))
                .turnTo(0.8)
                .splineToSplineHeading(beginPose,110)
                .build();



        Action park = drive.actionBuilder(beginPose)

                .strafeTo(new Vector2d(-50, -15))
                .turnTo(4.7)
                .build();

                /*
                // second ball collection
                .strafeTo(new Vector2d(-45, 52))
                .splineToSplineHeading(secondBalls,-10)
                .strafeTo(new Vector2d(12, 40))
                .splineToSplineHeading(beginPose,-110)
                .build();


                 */

        while (!isStopRequested() && !opModeIsActive()) {

        }

        waitForStart();

        if (isStopRequested()) return;
        shoot();
        shooter.setPower(-1);
        Actions.runBlocking(
                new SequentialAction(

                        collectBalls1, // Example of a drive action

                        // Only that this action uses a Lambda expression to reduce complexity
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            sleep(500);
                            shoot();
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        new ParallelAction( // several actions being run in parallel
                                park,
                                //  collectBalls2, // Run second trajectory
                                (telemetryPacket) -> { // Run some action

                                    //    shoot();
                                    // motor1.setPower(1);
                                    return false;
                                }
                        )

                )
        );


    }

}

