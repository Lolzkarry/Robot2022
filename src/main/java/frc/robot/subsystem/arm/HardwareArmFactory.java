package frc.robot.subsystem.arm;

import frc.robot.component.AngleComponent;
import frc.robot.subsystem.arm.ArmImpl;
public class HardwareArmFactory {
    public static Arm createArm() {
        AngleComponent motor = null;
        return new ArmImpl(motor);
    }
}