package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.acmerobotics.roadrunner.QuinticSpline2d;
import com.acmerobotics.roadrunner.QuinticSpline1d;

public class MeepMeepTesting {



    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(90), 15)
                .build();

        Pose2d beginPose = new Pose2d(new Vector2d(60, -12), Math.toRadians(-180));
        Pose2d origin = new Pose2d(new Vector2d(0, 0), Math.toRadians(-90));
        Pose2d endPose = new Pose2d(new Vector2d(-45, -45), Math.toRadians(-135));

        myBot.runAction(myBot.getDrive().actionBuilder(beginPose) //new Pose2d(30, 0, 90))
               // .strafeTo(origin.position)
                .splineToSplineHeading(endPose, -134.5)
                //.splineToSplineHeading(origin, 0)
                //.splineToSplineHeading(endPose, 0)
                //.splineToSplineHeading(new Vector2d(60, 60))
                // .strafeTo(new Vector2d(40, 40))
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