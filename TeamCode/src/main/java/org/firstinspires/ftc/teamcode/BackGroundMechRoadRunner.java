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
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class BackGroundMechRoadRunner implements Runnable {
    private boolean isRunning = true;

    private MecanumDrive mecanumDrive;
    private GamepadEx GP;
    private static ElapsedTime sleeper = new ElapsedTime();
        private GoBildaPinpointDriver pinpoint;

 //   private double adjustedHeading = 0;
    //private double headingDiff = 0;

   // private double finalHeading = 0;
  //  private double heading = 0;


    //All motors
    public BackGroundMechRoadRunner(GamepadEx gamepad1, MecanumDrive MD, GoBildaPinpointDriver odo) {
        GP = gamepad1;
          mecanumDrive = MD;
        pinpoint = odo;
    }


    @Override
    public void run() {

        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);


        while (isRunning) {



         //   if (GP2.getButton(GamepadKeys.Button.DPAD_DOWN))
           // {

             //   posServo1.setPosition(1);
               // posServo2.setPosition(0);
            //}
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


            if (GP.getButton(GamepadKeys.Button.A))
            {
               // finalHeading = 0;
              //  pinpoint.setHeading(0,AngleUnit.RADIANS);
                pinpoint.resetPosAndIMU();

            }





            double vertical = GP.getLeftY();
            double horizontal = -GP.getLeftX();
            double pivot = -GP.getRightX();

            if (GP.getButton(GamepadKeys.Button.LEFT_BUMPER))
            {
                pivot = pivot/4;
                horizontal = horizontal/4;
                vertical = vertical/4;
            }

            if(GP.getButton(GamepadKeys.Button.RIGHT_BUMPER)){


                Vector2d linearVelocity = new Vector2d(vertical, horizontal);
                PoseVelocity2d powers = new PoseVelocity2d(linearVelocity, pivot);
                mecanumDrive.setDrivePowers(powers);
            }
            else
            {
                if (sleeper.milliseconds() > 10)
                {
                    pinpoint.update();
                    sleeper.reset();
                }

                double heading = pinpoint.getHeading(AngleUnit.RADIANS);
                Rotation2d rotation = Rotation2d.exp(-heading);
                Vector2d fieldRelativeVector = rotation.times(new Vector2d(vertical, horizontal));
                PoseVelocity2d powers = new PoseVelocity2d(fieldRelativeVector, pivot);
                mecanumDrive.setDrivePowers(powers);
            }















        }

        // Stop running
    }

    public void stop() {
        isRunning = false;
    }
}