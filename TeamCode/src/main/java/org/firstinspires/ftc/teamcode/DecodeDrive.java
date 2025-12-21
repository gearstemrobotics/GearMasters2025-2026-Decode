package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp
public class DecodeDrive extends LinearOpMode {


    //  private GamepadEx gamepad;
    private boolean Moving = false;
    private VoltageSensor voltageSensor;
    private double powerScaler = 1;

    private DcMotor flinger;
    private DcMotor flinger2;
    private DcMotor shooter;
    private DcMotor kickStand;
    private static ElapsedTime stopWatch = new ElapsedTime();

    @Override
    public void runOpMode() {

        DoWork3();
    }


    public void DoWork3() {
        BackGroundMechRoadRunner task = new BackGroundMechRoadRunner(new GamepadEx(gamepad1),
                new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0)));

        Thread t1 = new Thread(task, "t1");
        flinger = hardwareMap.get(DcMotor.class, "flinger");
        flinger2 = hardwareMap.get(DcMotor.class, "flinger2");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
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

                //kickStand.setPower(gamepad2.right_stick_x);
                //shooter.setPower(-gamepad2.left_trigger);

                /*  double voltage = voltageSensor.getVoltage();
                telemetry.addData("voltage", voltage);
                telemetry.update();
                voltage = voltage/14;
                if (voltage >= 0.5)
            {
                powerScaler = powerScaler - (voltage - 0.5);
            }


                 */

if (gamepad2.dpadRightWasPressed())
{
    stopWatch.reset();
    while (stopWatch.seconds() < 0.5 )
    {
        flinger.setPower(-1);
        flinger2.setPower(1);

    }

    stopWatch.reset();
    while (stopWatch.seconds() < 0.3 )
    {
        flinger.setPower(1);
        flinger2.setPower(-1);

    }


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
                //  telemetry.addData("touch=", touch.isPressed());
                //telemetry.update();


                powerScaler = 1;
            }
        }

    }
}