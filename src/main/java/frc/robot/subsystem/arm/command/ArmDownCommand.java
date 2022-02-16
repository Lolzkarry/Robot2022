package frc.robot.subsystem.arm.command;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.arm.Arm;

public class ArmDownCommand extends CommandBase {
    private Arm arm;
    private double armAngle;
    public ArmDownCommand(Arm arm, double armAngle) {
        this.arm = arm;
        this.armAngle = armAngle;
        addRequirements(arm);
    }
    @Override
    public void initialize() {
        arm.setAngle(armAngle);
    }

}
