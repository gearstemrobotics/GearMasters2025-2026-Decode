package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp()
public class TestMotors extends OpMode {

    private DcMotor FrontLeft, FrontRight;
   // private ColorSensor color;
    // private DistanceSensor distance;
    //private TouchSensor touchSen;
    private GamepadEx GP1;

    @Override
    public void init() {
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");

        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
      //  touchSen = hardwareMap.get(TouchSensor.class, "TouchSen");
      //  color = hardwareMap.get(ColorSensor.class, "Color");
       // distance = hardwareMap.get(DistanceSensor.class, "distance");
        GP1 = new GamepadEx(gamepad1);
    }

    @Override
    public void loop() {

       FrontRight.setPower(GP1.getRightY());
       FrontLeft.setPower(GP1.getLeftY());
       // Arm.setPower(power);
       // telemetry.addData("power1", power);
       // telemetry.addData("Touched", touchSen.getValue());
        //telemetry.addData("Red", color.red());
        //telemetry.addData("Green", color.green());
        //telemetry.addData("Blue", color.blue());
        //telemetry.addData("Distance", distance.getDistance(DistanceUnit.CM));
    }
}
