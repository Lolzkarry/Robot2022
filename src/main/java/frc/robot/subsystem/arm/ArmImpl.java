package frc.robot.subsystem.arm;
import frc.robot.component.AngleComponent;
import frc.robot.subsystem.arm.Arm;
import edu.wpi.first.util.sendable.SendableBuilder;
public class ArmImpl implements Arm{
    private AngleComponent angleComponent;
    private double targetAngle;
    public ArmImpl(AngleComponent motor) {
        angleComponent = motor;
    }

    public void setAngle(double angle) {
        targetAngle = angle;
        angleComponent.setAngle(angle);
    }
    public void initSendable(SendableBuilder builder) {
        builder.addBooleanProperty("Active", () -> !(targetAngle == 0), null); //fix this later noob
        builder.addDoubleProperty("Target Angle", () -> targetAngle, null);
        builder.addDoubleProperty("Current Angle", angleComponent::getAngle, null);
    }
}
