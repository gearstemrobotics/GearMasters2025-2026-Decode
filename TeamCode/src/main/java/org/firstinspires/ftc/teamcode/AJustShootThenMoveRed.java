package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Vector2d;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous
public class AJustShootThenMoveRed extends baseAuto {

    @Override
    protected void RunInit() {
        color = 1; // isBlue(false);
        setBeginPoseAndInitDrive(-52.05, 47.05 * color, 487 * color);


    }

    @Override
    protected void RunOpModeInnerLoop() {


        Action park = drive.actionBuilder(beginPose)
                .strafeTo(new Vector2d(-57, 41 ))
                .build();

        shootNoRebound();

        Actions.runBlocking(
                new SequentialAction(



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

