package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous
public class mostPointsCloseBlue extends baseAuto {

    @Override
    protected void RunInit() {

        setBeginPoseAndInitDrive(-47, -51, -490);
        isBlue(true);
    }

    @Override
    protected void RunOpModeInnerLoop() {
        Pose2d firstBalls = new Pose2d(-11, color * 24, color * 1.57);
        Pose2d secondBalls = new Pose2d(12, color * 24, color * 1.57);

        // Delcare Trajectory as such

        Action collectBalls1 = drive.actionBuilder(drive.localizer.getPose())
                // first ball collection
                .strafeTo(new Vector2d(-46, color * 47))
                .splineToSplineHeading(firstBalls, color * -100.1)
                .strafeTo(new Vector2d(-9, color * 35))
                .waitSeconds(0.1)
                .strafeTo(new Vector2d(-9, color * 42))
                .waitSeconds(0.1)
                .strafeTo(new Vector2d(-9, color * 54))
                .strafeTo(new Vector2d(-9, color * 24))
                .turnTo(color * -0.8)
                .splineToSplineHeading(super.getBeginPose(), color * -110)
                .build();

        Action park = drive.actionBuilder(beginPose)
                .strafeTo(new Vector2d(-50, color * 15))
                .turnTo(color * -4.7)
                .build();

                /*
                // second ball collection
                .strafeTo(new Vector2d(-45, 52))
                .splineToSplineHeading(secondBalls,-10)
                .strafeTo(new Vector2d(12, 40))
                .splineToSplineHeading(beginPose,-110)
                .build();


                 */


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

                                    return false;
                                }
                        )

                )
        );
    }
}

