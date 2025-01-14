/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.swerve;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.component.AngleComponent;
import frc.robot.component.AngularVelocityComponent;

public class KinematicWheelModule extends SubsystemBase {


    protected AngleComponent angleComponent;
    protected double angleRotsPerMotorRots;
    protected double driveRotsPerMotorRots;
    protected AngularVelocityComponent angularVelocityComponent;
    protected Translation2d translationFromSwerveCenter;
    protected double maxSurfaceSpeed;
    protected double wheelDiameter;
    public KinematicWheelModule(AngleComponent angleComponent, AngularVelocityComponent angularVelocityComponent, Translation2d translationFromSwerveCenter, double maxSurfaceSpeed, double wheelDiameter, double angleRotsPerMotorRots, double driveRotsPerMotorRots) {
        this.angleComponent = angleComponent;
        this.angularVelocityComponent = angularVelocityComponent;
        this.translationFromSwerveCenter = translationFromSwerveCenter;
        this.maxSurfaceSpeed = maxSurfaceSpeed;
        this.wheelDiameter = wheelDiameter;
        this.angleRotsPerMotorRots = angleRotsPerMotorRots;
        this.driveRotsPerMotorRots = driveRotsPerMotorRots;
    }
    public void drive(SwerveModuleState state){
        angleComponent.setAngle(state.angle.getRadians() / angleRotsPerMotorRots);
        angularVelocityComponent.setAngularVelocity(state.speedMetersPerSecond / (wheelDiameter * Math.PI) * 2 * Math.PI / driveRotsPerMotorRots);
    }
    public Translation2d getTranslationFromSwerveCenter(){
        return translationFromSwerveCenter;
    }

    public double getMaxSurfaceSpeed() {
        return maxSurfaceSpeed;
    }
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
