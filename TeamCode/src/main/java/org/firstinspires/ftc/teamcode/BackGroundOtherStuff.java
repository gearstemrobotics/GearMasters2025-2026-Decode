package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class BackGroundOtherStuff implements Runnable {
    private boolean isRunning = true;

    private GamepadEx GP2;
    private Servo posServo1;
    private Servo posServo2;
    private CRServo intakeServo1;
    private CRServo intakeServo2;


    private DcMotor shooter;
    //   private double adjustedHeading = 0;
    //private double headingDiff = 0;

    // private double finalHeading = 0;
    //  private double heading = 0;


    //All motors
    public BackGroundOtherStuff(GamepadEx gamepad2,Servo backServo1, Servo backServo2, CRServo inServo1, CRServo inServo2, DcMotor nShooter) {

        GP2 = gamepad2;
        posServo1 = backServo1;
        posServo2 = backServo2;
        intakeServo1 = inServo1;
        intakeServo2 = inServo2;
        shooter = nShooter;

    }


    @Override
    public void run() {




        while (isRunning) {



            intakeServo1.setPower(GP2.getRightY());
            intakeServo2.setPower(-GP2.getRightY());

            shooter.setPower(-GP2.getRightY());

            if (GP2.getButton(GamepadKeys.Button.DPAD_UP))
            {

                    posServo1.setPosition(0);
                    posServo2.setPosition(1);


            }

               if (GP2.getButton(GamepadKeys.Button.DPAD_DOWN))
             {

               posServo1.setPosition(1);
             posServo2.setPosition(0);
            }
            // Do the work
            /*
            double pivot = -GP.getRightX();
            double horizontal = -GP.getLeftX();
            double vertical = GP.getLeftY();
            odo.update();
            double heading = odo.getHeading(AngleUnit.RADIANS);


            if (GP.getButton(GamepadKeys.Button.RIGHT_BUMPER))
            {
                pivot = pivot/2;
                horizontal = horizontal/2;
                vertical = vertical/2;
            }

            if (GP.getButton(GamepadKeys.Button.LEFT_BUMPER))
            {
                pivot = pivot/4;
                horizontal = horizontal/4;
                vertical = vertical/4;
            }


             */

            //   headingDiff = heading;



            // adjustedHeading = heading - headingDiff;

            //finalHeading += adjustedHeading;










        }

        // Stop running
    }

    public void stop() {
        isRunning = false;
    }
}