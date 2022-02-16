package frc.robot.subsystem.arm;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public interface Arm extends Subsystem, Sendable {
    public void setAngle(double angle);
}
