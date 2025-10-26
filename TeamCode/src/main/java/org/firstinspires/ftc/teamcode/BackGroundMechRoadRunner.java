package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;

public class BackGroundMechRoadRunner implements Runnable {
    private boolean isRunning = true;

    private MecanumDrive mecanumDrive;
    private GamepadEx GP;

    //All motors
    public BackGroundMechRoadRunner(GamepadEx gamepad, MecanumDrive MD) {
        GP = gamepad;
        mecanumDrive = MD; //HIIII THIS SH
        // hi edit 2
    }


    @Override
    public void run() {

        while (isRunning) {
            // Do the work

            double pivot = -GP.getRightX();
            double horizontal = -GP.getLeftX();
            double vertical = GP.getLeftY();
/*
            if (GP.getButton(GamepadKeys.Button.LEFT_BUMPER))
            {
                FrontRight.setPower((-pivot + (vertical - horizontal)) * 0.5);
                BackRight.setPower((-pivot + vertical + horizontal) * 0.5);
                FrontLeft.setPower((pivot + vertical + horizontal) * 0.5);
                BackLeft.setPower((pivot + (vertical - horizontal)) * 0.5);
            }
            else if (GP.getButton(GamepadKeys.Button.RIGHT_BUMPER))
            {
                FrontRight.setPower((-pivot + (vertical - horizontal)) * 0.25);
                BackRight.setPower((-pivot + vertical + horizontal) * 0.25);
                FrontLeft.setPower((pivot + vertical + horizontal) * 0.25);
                BackLeft.setPower((pivot + (vertical - horizontal)) * 0.25);
            }
            else
            {
                FrontRight.setPower((-pivot + (vertical - horizontal)) * 1);
                BackRight.setPower((-pivot + vertical + horizontal) * 1);
                FrontLeft.setPower((pivot + vertical + horizontal) * 1);
                BackLeft.setPower((pivot + (vertical - horizontal)) * 1);
            }
*/

            Vector2d linearVelocity = new Vector2d(vertical, horizontal);
            PoseVelocity2d powers = new PoseVelocity2d(linearVelocity, pivot);
            mecanumDrive.setDrivePowers(powers);
        }

        // Stop running
    }

    public void stop() {
        isRunning = false;
    }
}