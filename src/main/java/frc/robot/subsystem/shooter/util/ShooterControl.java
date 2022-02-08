package frc.robot.subsystem.shooter.util;

public class ShooterControl {
    public double shooterSpeed = 20000;
    public double speedThreshold = 1.00;
    public ShooterControl(double speed, double speedThreshold){
        shooterSpeed = speed;
        this.speedThreshold = speedThreshold;
    }
}