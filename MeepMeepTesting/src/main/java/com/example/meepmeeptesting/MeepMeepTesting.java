package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.ColorScheme;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.acmerobotics.roadrunner.QuinticSpline2d;
import com.acmerobotics.roadrunner.QuinticSpline1d;

public class MeepMeepTesting {


    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(900);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(90), 15)

                .build();

        float isBlue = 1;

       // Pose2d origin = new Pose2d(new Vector2d(0, 0), Math.toRadians(-90));
        Pose2d endPose = new Pose2d(new Vector2d(-47, -52), Math.toRadians(-490));
        //  Pose2d beginPose = new Pose2d(new Vector2d(-52, -45), Math.toRadians(-135));
        //Pose2d endPose = new Pose2d(new Vector2d(10, 23), Math.toRadians(-80));
        Pose2d beginPose =   new Pose2d(new Vector2d(-47, 51), Math.toRadians(490));
        Pose2d test = new Pose2d(10,-23,-90);
        Pose2d firstBalls = new Pose2d(-11,24,1.57);
        Pose2d secondBalls = new Pose2d(12,-24,-1.57);

        myBot.runAction(myBot.getDrive().actionBuilder(beginPose) //new Pose2d(30, 0, 90))

                // .strafeTo(origin.position)
                      //  .turnTo(200)
                      //  .strafeTo(46,47,9)
                .strafeTo(new Vector2d(-46, 47))
                .splineToSplineHeading(firstBalls,-100.1)
                .strafeTo(new Vector2d(-9, 35))
                        .waitSeconds(0.1)
                .strafeTo(new Vector2d(-9, 40))
                .waitSeconds(0.1)
                .strafeTo(new Vector2d(-9, 45))

                .strafeTo(new Vector2d(-9, 24))
                .turnTo(0.8)
                .splineToSplineHeading(beginPose,-110)
                .strafeTo(new Vector2d(-50, 15))
                .turnTo(-4.7)



/*
                .strafeTo(new Vector2d(-45, -52))
                .splineToSplineHeading(secondBalls,9)
                .strafeTo(new Vector2d(12, -40))
                .splineToSplineHeading(beginPose,110)


 */

               //                 .lineToXSplineHeading(endPose,-100)

                //.splineToSplineHeading(endPose, -134.5)
                //.splineToSplineHeading(origin, 0)
                //.splineToSplineHeading(endPose, 0)
                //.splineToSplineHeading(new Vector2d(60, 60))
                //.strafeTo(new Vector2d(-50, 15))
                //.turnTo(-3.5)
                //.lineToXSplineHeading(40, Math.toRadians(90))
                .build());

        // QuinticSpline2d spline = new QuinticSpline2d(
        // new QuinticSpline2d(0,0),//.Waypoint(0, 0, 0, 0),
        //  new QuinticSpline2d(0,0),//.Waypoint(30, 15, -30, 10)
        //  );
        // QuinticSpline2d spline = new QuinticSpline2d(
        //     new QuinticSpline1d.Waypoint(0, 0, 20, 20),
        //       new QuinticSpline1d(30, 15),
        //);

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_BLACK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}