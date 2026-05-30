package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous
public class AlamoBlueMP extends baseAuto {

    @Override
    protected void RunInit() {
      // color =  -1; // isBlue(true);
       // setBeginPoseAndInitDrive(-52.05, -47.05, -487);
        setBeginPoseAndInitDrive(-50.05, -45.05,-487);

    }

    @Override
    protected void RunOpModeInnerLoop() {

        // Delcare Trajectory as such


        Action initialMoveBack = drive.actionBuilder(beginPoseBlue)
                //goes to the first balls
                .strafeTo(new Vector2d(-50.05, -45.05))
                .build();




        Action collectBalls1 = drive.actionBuilder(drive.localizer.getPose())
                //goes to the first balls
                .strafeTo(new Vector2d(-46.8, -42.1))//*color))
                .splineToSplineHeading(firstBallsBlue,100.1)
                //.strafeTo(new Vector2d(-11.4, -50))
                //.strafeTo(new Vector2d(-11.4, -35))
              //  .waitSeconds(0.1)
                //.strafeTo(new Vector2d(-11.4, -42))
              //  .waitSeconds(0.1)
                .strafeTo(new Vector2d(-13, -57))

                .strafeTo(new Vector2d(-11.4, -24))
                .build();

        Action shootBalls1 = drive.actionBuilder(firstBallsBlue)
                // goes back to the goal to shoot
                .strafeTo(new Vector2d(-11, -24))
                .splineToSplineHeading(beginPose,280.1)
                .build();


        Action collectBalls2 = drive.actionBuilder(beginPose)
                // goes to second balls
                .strafeTo(new Vector2d(-46.8, -42.1))
                .splineToSplineHeading(secondBallsBlue,100.1)
                .strafeTo(new Vector2d(15, -63))
                .build();

        Action shootBalls2 = drive.actionBuilder(secondBallsBlue)
                // goes back to the goal to shoot
                .strafeTo(new Vector2d(12.3, -24))
                .splineToSplineHeading(beginPose,280.1)
                .build();


        Action collectBalls3 = drive.actionBuilder(beginPose)
                // goes and gets the third balls
                .strafeTo(new Vector2d(-46.8, -42.1))
                .splineToSplineHeading(thirdBallsBlue ,100.1)
                .strafeTo(new Vector2d(35.8, -50))
                .build();

        Action shootBalls3 = drive.actionBuilder(thirdBallsBlue)
                // goes back and shoots
                .strafeTo(new Vector2d(35.8, -24))
                .splineToSplineHeading(beginPose,280.1)
                .build();


        Action park = drive.actionBuilder(beginPose)
                .strafeTo(new Vector2d(-57, -41))
                .build();



        Actions.runBlocking(
                new SequentialAction(
                        initialMoveBack,
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            sleep(500);
                            // flinger.setPower(-1);
                            shootNoRebound();
                            shooter.setPower(-1);
                            intakeServo1.setPower(1);
                            intakeServo2.setPower(-1);
                            // flinger2.setPower(1);
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },

                        collectBalls1, // Example of a drive action
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            sleep(500);
                           // flinger.setPower(-1);
                           // flinger2.setPower(1);
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        shootBalls1,
                        // Only that this action uses a Lambda expression to reduce complexity
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            shooter.setPower(0);
                            sleep(500);
                            shoot();
                            shooter.setPower(-1);
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },







                        collectBalls2,
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            sleep(500);
                           // flinger.setPower(-1);
                            //flinger2.setPower(1);
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        shootBalls2,
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            shooter.setPower(0);
                            sleep(500);
                            shootNoWait();
                            //shooter.setPower(-1);
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        park
                        /*,




                        collectBalls3,
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            sleep(500);
                           // flinger.setPower(-1);
                            //flinger2.setPower(1);
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        shootBalls3,
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            shooter.setPower(0);
                            sleep(500);
                            shoot();
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        }

                         */


                )
        );
    }
}

