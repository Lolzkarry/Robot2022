/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.swerve.pathfollowingswerve;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.component.GyroComponent;
import frc.robot.subsystem.swerve.KinematicSwerve;

/**
 * Add your docs here.
 */
public class OdometricSwerve extends KinematicSwerve implements PathFollowingSwerve  {

    SwerveDriveOdometry odometry;
    OdometricWheelModule[] odometricWheelModules;
    private Translation2d lastTranslation;
    private double lastTime;
    public OdometricSwerve(GyroComponent gyro, OdometricWheelModule... wheelModules) {
        super(gyro, wheelModules);
        odometry = new SwerveDriveOdometry(kinematics, new Rotation2d(gyro.getAngle()));
        odometricWheelModules = wheelModules;
    }
    private void updateOdometry(){
        lastTime = Timer.getFPGATimestamp();
        lastTranslation = getCurrentPose().getTranslation();
        odometry.update(new Rotation2d(gyro.getAngle()), getSwerveModuleStates());
    }
    private SwerveModuleState[] getSwerveModuleStates(){
        var states = new SwerveModuleState[wheelModules.length];
        for(int i = 0;i<states.length;i++){
            states[i] = odometricWheelModules[i].getState();
        }
        return states;
    }
    public Pose2d getCurrentPose(){
        return odometry.getPoseMeters();
    }
    public Translation2d getCurrentVelocity(){
        return getCurrentPose().getTranslation().minus(lastTranslation).div(Timer.getFPGATimestamp() - lastTime);
    }
    public void resetPose(){
        resetRobotAngle();
        odometry.resetPosition(new Pose2d(getCurrentPose().getTranslation(),new Rotation2d()), new Rotation2d(gyro.getAngle()));
    }
    public void resetPose(Translation2d translation){
        resetRobotAngle();
        odometry.resetPosition(new Pose2d(translation, new Rotation2d()), new Rotation2d(gyro.getAngle()));
    }
    public void resetPose(Pose2d pose){
        resetRobotAngle(pose.getRotation().getRadians());
        odometry.resetPosition(pose, new Rotation2d(gyro.getAngle()));
    }
    public void periodic() {
        updateOdometry();
    }
    public void enableWheelWrap(boolean enable){
        for(var module : odometricWheelModules){
            module.wheelWrapEnabled = enable;
        }
    }
    public void enableWheelInversion(boolean enable){
        for(var module : odometricWheelModules){
            module.wheelInversionEnabled = enable;
        }
    }
    public void coast(){
        for(var module : odometricWheelModules){
            module.coast();
        }
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
    }
}

