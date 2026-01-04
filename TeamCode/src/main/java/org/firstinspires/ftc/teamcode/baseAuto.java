package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class baseAuto extends LinearOpMode {

    public static class Locations
    {
        public enum Pose
        {
            endPose,
            startPose,
        }

        public Pose2d getPose(Pose pose, boolean isBlue) throws Exception {

            double x = 0, y = 0, heading = 0;

            if (pose == Pose.startPose)
            {
                x = endPoseRed.position.x;
                y = endPoseRed.position.y;
                heading = endPoseRed.heading.real;
            }
            else
            {
                throw new Exception("Invalid pose");
            }


            if (isBlue)
            {
                y *= -1;
                heading *= -1;
            }

            return new Pose2d(new Vector2d(x, y), Math.toRadians(heading));
        }

        public Pose2d endPoseRed = new Pose2d(new Vector2d(-47, -52), Math.toRadians(-490));
        public Pose2d endPoseBlue = new Pose2d(new Vector2d(-47, -52), Math.toRadians(-490));

        public Pose2d test = new Pose2d(10,-23,-90);
        public Pose2d firstBalls = new Pose2d(-11,-24,-1.57);
        public Pose2d secondBalls = new Pose2d(12,-24,-1.57);
    }





    protected DcMotor flinger;
    protected DcMotor flinger2;
    protected DcMotor shooter;
    protected static ElapsedTime stopWatch = new ElapsedTime();
    protected VoltageSensor voltageSensor;

    protected Pose2d beginPose = null;
    protected  MecanumDrive drive = null;
    protected double color;



   public baseAuto() {}


    protected void setBeginPoseAndInitDrive(double x, double y, double rad)
    {
        beginPose = new Pose2d(new Vector2d(x, y), Math.toRadians(rad));
        drive = new MecanumDrive(hardwareMap, new Pose2d(new Vector2d(x, y), Math.toRadians(rad)));
    }


    protected void isBlue(boolean blue)
    {
        if(blue){
             color = -1;
        }

        else
        {
            color = 1;
        }



    }

    protected void shoot()
    {

        double voltage = voltageSensor.getVoltage();
        double voltageScaler = 12/voltage;
        stopWatch.reset();
        while (stopWatch.seconds() < 0.4 )
        {

            flinger.setPower(-voltageScaler);
            flinger2.setPower(voltageScaler);

        }

        stopWatch.reset();
        while (stopWatch.seconds() < 0.2 )
        {
            flinger.setPower(voltageScaler);
            flinger2.setPower(-voltageScaler);

        }


    }

    protected boolean isBlue = false;

    protected abstract void RunInit();

    protected abstract void RunOpModeInnerLoop();

    @Override
    public void runOpMode() {

        Map();

        RunInit();
        while (!isStopRequested() && !opModeIsActive()) {

        }

        waitForStart();


        if (opModeIsActive()) {
           PrepMotor();
            RunOpModeInnerLoop();
        }
    }


    protected void Map() {
        flinger = hardwareMap.get(DcMotor.class, "flinger");
        flinger2 = hardwareMap.get(DcMotor.class, "flinger2");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");
    }

    protected void PrepMotor() {

        flinger.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flinger2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    protected TrajectoryActionBuilder getTrajectoryBuilder(Pose2d pose) {
        if (pose == null) return drive.actionBuilder(drive.localizer.getPose());
        return drive.actionBuilder(pose);
    }

    protected Pose2d getBeginPose() {
        return beginPose;
    }
}



