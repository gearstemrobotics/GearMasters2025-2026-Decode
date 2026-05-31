
   // .strafeTo(new Vector2d(49.5, -13.5 *color))
     //       .turnTo(-2.75)
   package org.firstinspires.ftc.teamcode;

   import com.acmerobotics.roadrunner.Vector2d;

   import com.acmerobotics.roadrunner.Action;
   import com.acmerobotics.roadrunner.ParallelAction;
   import com.acmerobotics.roadrunner.SequentialAction;
   import com.acmerobotics.roadrunner.ftc.Actions;
   import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


   @Autonomous
   public class ASingleShootAutoRed extends baseAuto {

       @Override
       protected void RunInit() {
           color = 1; // isBlue(false);
           setBeginPoseAndInitDrive(61.5, -12,180);


       }

       @Override
       protected void RunOpModeInnerLoop() {


           Action park = drive.actionBuilder(beginPose)
                    .strafeTo(new Vector2d(49.5, -13.5))
                          .turnTo(2.70)
                   .build();

           Action parkInCorner = drive.actionBuilder(beginPose)
                   .turnTo(-3.13)
                   .strafeTo(new Vector2d(61.5, 60.8))
                   .build();



           Actions.runBlocking(
                   new SequentialAction(

                           (telemetryPacket) -> {
                               telemetry.addLine("Action!");
                               telemetry.update();

                               sleep(10000);

                               return false; // Returning true causes the action to run again, returning false causes it to cease
                           },



                           park
,
                                   (telemetryPacket) -> {
                                       telemetry.addLine("Action!");
                                       telemetry.update();

                                       shootNoWait();
                                       shooter.setPower(-0.70);
                                       sleep(500);
                                       shooter.setPower(0);
                                       shootNoWait();
                                       shooter.setPower(-0.70);
                                       sleep(1300);
                                       shooter.setPower(0);
                                       shootNoWait();

                                       //  shooter.setPower( 0);
                                       //  flinger.setPower(-1);
                                       // flinger2.setPower(1);
                                       return false; // Returning true causes the action to run again, returning false causes it to cease
                                   }
               ,parkInCorner






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

