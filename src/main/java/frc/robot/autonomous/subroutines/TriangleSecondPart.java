package frc.robot.autonomous.subroutines;

import edu.wpi.first.math.trajectory.Trajectory;

import javax.management.InstanceNotFoundException;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystem.arm.Arm;
import frc.robot.subsystem.arm.ArmConstants;
import frc.robot.subsystem.arm.command.ArmSetAngleCommand;
import frc.robot.subsystem.intake.Intake;
import frc.robot.subsystem.intake.command.IntakeRunCommand;
import frc.robot.subsystem.loader.Loader;
import frc.robot.subsystem.shooter.Shooter;
import frc.robot.subsystem.shooter.command.AutomatedShootingCommand;
import frc.robot.subsystem.shooter.command.ShooterSpinDownCommand;
import frc.robot.subsystem.shooter.util.ShooterControl;
import frc.robot.subsystem.swerve.pathfollowingswerve.AdvancedSwerveControllerBuilder;
import frc.robot.subsystem.swerve.pathfollowingswerve.PathFollowingSwerve;
import frc.robot.subsystem.swerve.pathfollowingswerve.command.FollowDottedTrajectoryCommand;
import frc.robot.subsystem.vision.Vision;
import frc.robot.utility.ExtendedTrajectoryUtilities;
import frc.robot.utility.GenericAutonUtilities;
import frc.robot.subsystem.shooter.command.ManualShootingCommand;

/**
 * Second Part of the triangular autonomous route, to be run after FirstBall
 */

public class TriangleSecondPart extends SequentialCommandGroup {

    public TriangleSecondPart(PathFollowingSwerve swerve, Arm arm, Intake intake, Shooter shooter, Vision vision, Loader loader) {
        Trajectory path = ExtendedTrajectoryUtilities.getDeployedTrajectory("TriangleSecondPart");
        FollowDottedTrajectoryCommand swerveCommand = new FollowDottedTrajectoryCommand(swerve, path, ExtendedTrajectoryUtilities.createBasicController(1,1,3,4,1));
        swerveCommand.setRotation(true);
        addCommands(
                new ParallelCommandGroup(
                        swerveCommand,
                        new IntakeRunCommand(intake)
                ).withTimeout(2),
                new InstantCommand(() -> swerve.moveRobotCentric(0,0,0)),
                new ParallelCommandGroup(
                        new ManualShootingCommand(shooter, vision, loader, new ShooterControl(25000, 1000)),
                        new ArmSetAngleCommand(arm, ArmConstants.ARM_UP_ANGLE)
                ).withTimeout(2),
                new ShooterSpinDownCommand(shooter)
        );
    }
}