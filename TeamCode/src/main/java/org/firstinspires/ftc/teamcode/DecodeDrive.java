package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp
public class DecodeDrive extends LinearOpMode {


    //  private GamepadEx gamepad;
    private boolean Moving = false;
    private VoltageSensor voltageSensor;
    private double powerScaler = 1;
    
    private double powerForShooter = 0.5; 

    private Servo servo1;
    private Servo servo2;
    private DcMotor flinger;
    private DcMotor flinger2;
    private DcMotor shooter;
    private DcMotor kickStand;
    private static ElapsedTime stopWatch = new ElapsedTime();

    private GoBildaPinpointDriver pinpoint;


    @Override
    public void runOpMode() {

        DoWork3();
    }


    public void DoWork3() {
        BackGroundMechRoadRunner task = new BackGroundMechRoadRunner(new GamepadEx(gamepad1),
                new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0)),
          hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint"));


        Thread t1 = new Thread(task, "t1");
        flinger = hardwareMap.get(DcMotor.class, "flinger");
        flinger2 = hardwareMap.get(DcMotor.class, "flinger2");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");

        kickStand = hardwareMap.get(DcMotor.class, "kickStand");
        //TouchSensor touch = hardwareMap.get(TouchSensor.class, "touch");
        flinger.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flinger2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        if (opModeIsActive()) {
            t1.start();
            //t2.start();
            while (opModeIsActive()) {

                flinger.setPower(-gamepad2.left_stick_y);
                flinger2.setPower(gamepad2.left_stick_y);
                shooter.setPower(-gamepad2.right_stick_y);
                kickStand.setPower(gamepad2.right_trigger);
                kickStand.setPower(-gamepad2.left_trigger);

                double voltage = voltageSensor.getVoltage();
                double voltageScaler = 12.7 / voltage;

                //kickStand.setPower(gamepad2.right_stick_x);
                //shooter.setPower(-gamepad2.left_trigger);

                /*  double voltage = voltageSensor. getVoltage();
                telemetry.addData("voltage", voltage);
                telemetry.update();
                voltage = voltage/14;
                if (voltage >= 0.5)
            {
                powerScaler = powerScaler - (voltage - 0.5);
            }


                 */



                // three balls
                while (gamepad2.y) {
                   // stopWatch.reset();
                   /* while (stopWatch.seconds() < 0.5) {

                        flinger.setPower(-voltageScaler * 0.95);
                        flinger2.setPower(voltageScaler * 0.95);

                    }

                    */

                    //stopWatch.reset();
                    //while (stopWatch.seconds() < 0.5) {
                        flinger.setPower(-voltageScaler);
                        flinger2.setPower(voltageScaler);
                    stopWatch.reset();

                  //  }


                }

                while (stopWatch.seconds() > 0.1 && stopWatch.seconds() < 0.5)
                {
                    flinger.setPower(voltageScaler);
                    flinger2.setPower(-voltageScaler);

                }

                // two balls
                if (gamepad2.xWasPressed()) {
                    stopWatch.reset();
                    while (stopWatch.seconds() < 0.7) {

                        flinger.setPower(-voltageScaler );
                        flinger2.setPower(voltageScaler);

                    }

                    stopWatch.reset();
                    while (stopWatch.seconds() < 0.3) {
                        flinger.setPower(voltageScaler );
                        flinger2.setPower(-voltageScaler  );

                    }


                }

                // one ball
                if (gamepad2.aWasPressed())
                {
                    stopWatch.reset();
                    while (stopWatch.seconds() < 0.5 )
                    {

                        flinger.setPower(-voltageScaler * 0.83);
                        flinger2.setPower(voltageScaler * 0.83);

                    }

                    stopWatch.reset();
                    while (stopWatch.seconds() < 0.3 )
                    {
                        flinger.setPower(voltageScaler * 0.83);
                        flinger2.setPower(-voltageScaler * 0.83);

                    }


                }



                if (gamepad2.bWasPressed())
                {
                    stopWatch.reset();
                    while (stopWatch.seconds() < 0.8 )
                    {

                        flinger.setPower(-voltageScaler * powerForShooter);
                        flinger2.setPower(voltageScaler * powerForShooter);

                    }

                    stopWatch.reset();
                    while (stopWatch.seconds() < 0.4 )
                    {
                        flinger.setPower(voltageScaler * powerForShooter);
                        flinger2.setPower(-voltageScaler * powerForShooter);

                    }


                }
                
                
                if (gamepad2.dpadUpWasPressed())
                {
                    powerForShooter += 0.01;
                    servo1.setPosition(1);
                    servo2.setPosition(1);
                    
                }

                if (gamepad2.dpadDownWasPressed())
                {
                    powerForShooter -= 0.01;
                    servo1.setPosition(0);
                    servo2.setPosition(0);
                }
                /*
                if (gamepad2.right_trigger > 0) {
                    gripper2.setPower(gamepad2.right_trigger);
                    gripper.setPower(-gamepad2.right_trigger);
                }


                   else if (Red > 2000) {
                        gripper2.setPower(0);
                        gripper.setPower(0);

                    } else if (Blue > 2000) {
                        gripper2.setPower(1);
                        gripper.setPower(-1);

                    } else {
                        gripper2.setPower(0);
                        gripper.setPower(0);
                    }

                 */

                //task2.AddTelemetry(telemetry);
                telemetry.addData("voltage scaler", voltageScaler);
                telemetry.addData("voltage", voltage);
                telemetry.addData("shoot power", powerForShooter);
                telemetry.update();


                powerScaler = 1;
            }
        }

    }
}