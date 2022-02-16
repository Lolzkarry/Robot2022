package frc.robot.subsystem.arm.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.arm.Arm;

public class ArmUpCommand extends CommandBase {
    private Arm arm;
    private double angle;

    public ArmUpCommand(Arm arm, double angle) {
        this.arm = arm;
        this.angle = angle;
        addRequirements(arm);
    }
    public void initalize() {
        arm.setAngle(angle);
    }
}
