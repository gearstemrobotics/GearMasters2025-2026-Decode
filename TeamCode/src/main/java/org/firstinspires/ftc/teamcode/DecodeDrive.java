package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;


@TeleOp
public class DecodeDrive extends LinearOpMode {



    private boolean Moving = false;

    @Override
    public void runOpMode() {

        DoWork3();
    }


    public void DoWork3() {
        BackGroundMechRoadRunner task = new BackGroundMechRoadRunner(new GamepadEx(gamepad1),
                new MecanumDrive(hardwareMap, new Pose2d(0,0,0)));

        Thread t1 = new Thread(task, "t1");

        //TouchSensor touch = hardwareMap.get(TouchSensor.class, "touch");

        waitForStart();
        if (opModeIsActive()) {
            t1.start();
            //t2.start();
            while (opModeIsActive()) {







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




            }
        }

    }
}