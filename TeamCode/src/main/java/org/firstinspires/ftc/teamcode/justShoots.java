package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class justShoots extends LinearOpMode {
    private DcMotor flinger;
    private DcMotor flinger2;

    private static ElapsedTime stopWatch = new ElapsedTime();


    @Override
    public void runOpMode() {

        flinger = hardwareMap.get(DcMotor.class, "flinger");
        flinger2 = hardwareMap.get(DcMotor.class, "flinger2");
        flinger.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flinger2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        waitForStart();
        if (opModeIsActive()){





            stopWatch.reset();
            while (stopWatch.seconds() < 1.5 )
            {
                flinger.setPower(-1);
                flinger2.setPower(1);

            }

            stopWatch.reset();
            while (stopWatch.seconds() < 1 )
            {
                flinger.setPower(1);
                flinger2.setPower(- 1);

            }
        }





    }

}
