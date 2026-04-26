package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Vector2d;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous
public class AlamoRedMP extends baseAuto {

    @Override
    protected void RunInit() {
       color = 1; // isBlue(false);
        setBeginPoseAndInitDrive(-52.05, 47.05 * color, 487 * color);


    }

    @Override
    protected void RunOpModeInnerLoop() {

        // Delcare Trajectory as such

        Action collectBalls1 = drive.actionBuilder(drive.localizer.getPose())
                //goes to the first balls
                .strafeTo(new Vector2d(-46.8, 42.1*color))
                .splineToSplineHeading(firstBallsRed,-100.1*color)
                //.strafeTo(new Vector2d(-11.4, 50*color))
              //  .strafeTo(new Vector2d(-11.4, 35*color))
               // .waitSeconds(0.1)
              //  .strafeTo(new Vector2d(-11.4, 42*color))
                //.waitSeconds(0.1)
                .strafeTo(new Vector2d(-13, 57*color))

                .strafeTo(new Vector2d(-11.4, 24*color))
                .build();

        Action shootBalls1 = drive.actionBuilder(firstBallsRed)
                // goes back to the goal to shoot
                .strafeTo(new Vector2d(-11, 24*color))
                .splineToSplineHeading(beginPose,-280.1*color)
                .build();


        Action collectBalls2 = drive.actionBuilder(beginPose)
                // goes to second balls
                .strafeTo(new Vector2d(-46.8, 42.1*color))
                .splineToSplineHeading(secondBallsRed,-100.1*color)
                .strafeTo(new Vector2d(15, 63*color))
                .build();

        Action shootBalls2 = drive.actionBuilder(secondBallsRed)
                // goes back to the goal to shoot
                .strafeTo(new Vector2d(12.3, 24*color))
                .splineToSplineHeading(beginPose,-280.1*color)
                .build();


        Action collectBalls3 = drive.actionBuilder(beginPose)
                // goes and gets the third balls
                .strafeTo(new Vector2d(-46.8, 42.1*color))
                .splineToSplineHeading(thirdBallsRed ,-100.1*color)
                .strafeTo(new Vector2d(35.8, 50*color))
                .build();

        Action shootBalls3 = drive.actionBuilder(thirdBallsRed)
                // goes back and shoots
                .strafeTo(new Vector2d(35.8, 24*color))
                .splineToSplineHeading(beginPose,-280.1*color)
                .build();


        Action park = drive.actionBuilder(beginPose)
                .strafeTo(new Vector2d(-57, 41 *color))
                        .build();

         shootNoRebound();
        shooter.setPower(-1);
        Actions.runBlocking(
                new SequentialAction(

                        collectBalls1, // Example of a drive action
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                           // shooter.setPower(0);
                            sleep(500);
                           // flinger.setPower(-1);
                            //flinger2.setPower(1);
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        shootBalls1,
                        // Only that this action uses a Lambda expression to reduce complexity
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            //shooter.setPower(0);
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
                           // shooter.setPower(0);
                           // flinger.setPower(-1);
                           // flinger2.setPower(1);
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        shootBalls2,
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                           // shooter.setPower(0);
                            sleep(500);
                           shootNoWait();
                           // shooter.setPower(-1);
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        park





/*   ,
                        collectBalls3,
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            sleep(500);
                          //  shooter.setPower( 0);
                          //  flinger.setPower(-1);
                           // flinger2.setPower(1);
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        shootBalls3,
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                           // shooter.setPower(0);
                            sleep(500);
                             shoot();

                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        }

 */


                )
        );
    }
}

