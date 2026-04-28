package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class BackGroundMechRoadRunner implements Runnable {
    private boolean isRunning = true;

    private MecanumDrive mecanumDrive;
    private GamepadEx GP;
    private GoBildaPinpointDriver pinpoint;

    private double adjustedHeading = 0;
    private double headingDiff = 0;

    private double finalHeading = 0;
    private double heading = 0;


    //All motors
    public BackGroundMechRoadRunner(GamepadEx gamepad, MecanumDrive MD, GoBildaPinpointDriver odo) {
        GP = gamepad;
        mecanumDrive = MD;
        pinpoint = odo;
    }


    @Override
    public void run() {
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);


        while (isRunning) {
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

            headingDiff = heading;
            pinpoint.update();
            heading = pinpoint.getHeading(AngleUnit.RADIANS);


            adjustedHeading = heading - headingDiff;

            finalHeading += adjustedHeading;


            if (GP.getButton(GamepadKeys.Button.A))
            {
                finalHeading = 0;

            }





            double vertical = -GP.getLeftY();
            double horizontal = GP.getLeftX();
            double pivot = -GP.getRightX();
            Rotation2d rotation = Rotation2d.exp(-finalHeading);

            Vector2d fieldRelativeVector = rotation.times(new Vector2d(vertical, horizontal));


            PoseVelocity2d powers = new PoseVelocity2d(fieldRelativeVector, pivot);
            mecanumDrive.setDrivePowers(powers);




/*
            Vector2d linearVelocity = new Vector2d(vertical, horizontal);
            PoseVelocity2d powers = new PoseVelocity2d(linearVelocity, pivot);
            mecanumDrive.setDrivePowers(powers);

 */
        }

        // Stop running
    }

    public void stop() {
        isRunning = false;
    }
}