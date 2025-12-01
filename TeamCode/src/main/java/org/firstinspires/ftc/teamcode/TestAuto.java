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


@Autonomous
public class TestAuto extends LinearOpMode {


    @Override
    public void runOpMode() {


        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(new Vector2d(60, -12), Math.toRadians(-180)));

      //  DcMotor motor1 = hardwareMap.get(DcMotor.class,  "motor");

        Pose2d endPose = new Pose2d(new Vector2d(-45, -45), Math.toRadians(-135));
        Pose2d beginPose = new Pose2d(new Vector2d(60, -12), Math.toRadians(-180));

        // Delcare Trajectory as such
        Action TrajectoryAction1 = drive.actionBuilder(drive.localizer.getPose())

                .splineToSplineHeading(endPose, -134.5)
                .build();

        Action TrajectoryAction2 = drive.actionBuilder(new Pose2d(15,20,0))

                //.splineTo(new Vector2d(5,5), Math.toRadians(90))
                .build();


        while(!isStopRequested() && !opModeIsActive()) {

        }

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        TrajectoryAction1, // Example of a drive action

                        // This action and the following action do the same thing
                        new Action() {
                            @Override
                            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                                telemetry.addLine("Action!");
                                telemetry.update();
                                return false;
                            }
                        },
                        // Only that this action uses a Lambda expression to reduce complexity
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        new ParallelAction( // several actions being run in parallel
                                TrajectoryAction2, // Run second trajectory
                                (telemetryPacket) -> { // Run some action
                                   // motor1.setPower(1);
                                    return false;
                                }
                        )
                        /*
                        drive.actionBuilder(new Pose2d(15,10,Math.toRadians(125))) // Another way of running a trajectory (not recommended because trajectories take time to build and will slow down your code, always try to build them beforehand)
                                .splineTo(new Vector2d(25, 15), 0)
                                .build()

                         */

                )
        );


    }

}

        /*
        Pose2d beginPose = new Pose2d(new Vector2d(60, -12), Math.toRadians(-180));
        Pose2d origin = new Pose2d(new Vector2d(0, 0), Math.toRadians(-90));
        Pose2d endPose = new Pose2d(new Vector2d(-45, -45), Math.toRadians(-135));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);



        waitForStart();
        if (opModeIsActive()) {
            TrajectoryActionBuilder tab1 = drive.actionBuilder(beginPose) //new Pose2d(30, 0, 90))
                // .strafeTo(origin.position)
                .splineToSplineHeading(endPose, -134.5)
                //.splineToSplineHeading(origin, 0)
                //.splineToSplineHeading(endPose, 0)
                //.splineToSplineHeading(new Vector2d(60, 60))
                // .strafeTo(new Vector2d(40, 40))
                //.lineToXSplineHeading(40, Math.toRadians(90))
                .build());



        }


    }
}
*/
