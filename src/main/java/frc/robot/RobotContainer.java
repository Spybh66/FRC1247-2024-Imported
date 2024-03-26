// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.PS4Controller;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
//import frc.robot.subsystems.LauncherSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // private final ExampleCommand m_autoCommand = new
  // ExampleCommand(m_exampleSubsystem);

  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  //private final LauncherSubsystem m_launcher = new LauncherSubsystem();
  private final ArmSubsystem m_arm = new ArmSubsystem();
  private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
  private final ClimberSubsystem m_climber = new ClimberSubsystem();

  private PS4Controller m_driverController = new PS4Controller(Constants.OIConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_arm.setDefaultCommand(new RunCommand(() -> m_arm.runManual(m_driverController.getRightY()), m_arm));

    // set the intake to stop (0 power) when no other command is running
    m_intake.setDefaultCommand(new RunCommand(() -> m_intake.setPower(0.0), m_intake));

    // configure the launcher to stop when no other command is running
    //m_launcher.setDefaultCommand(new RunCommand(() -> m_launcher.stopLauncher(), m_launcher));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link PS4Controller}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // set up the drivetrain command that runs all the time
    m_drivetrain.setDefaultCommand(new RunCommand(
        () -> m_drivetrain.driveArcade(
            MathUtil.applyDeadband(-m_driverController.getLeftY(), Constants.OIConstants.kDriveDeadband),
            MathUtil.applyDeadband(m_driverController.getLeftX() * Constants.Drivetrain.kTurningScale,
                Constants.OIConstants.kDriveDeadband)),
        m_drivetrain));
/*     new JoystickButton(m_driverController, PS4Controller.Button.kL1.value)
        .onTrue(new InstantCommand(() -> m_arm.setTargetPosition(Constants.Arm.kScoringPosition)));
    new Trigger(
        () -> m_driverController.getL2Axis() > Constants.OIConstants.kTriggerButtonThreshold)
        .onTrue(new InstantCommand(() -> m_arm.setTargetPosition(Constants.Arm.kIntakePosition)));
    new JoystickButton(m_driverController, PS4Controller.Button.kPS.value)
        .onTrue(new InstantCommand(() -> m_arm.setTargetPosition(Constants.Arm.kHomePosition)));

     */
    // intake controls (run while button is held down, run retract command once when
    // the button is
    // released)
    new Trigger(
        () -> m_driverController.getR2Axis() > Constants.OIConstants.kTriggerButtonThreshold)
        .whileTrue(new RunCommand(() -> m_intake.setPower(Constants.Intake.kIntakePower), m_intake))
        .onFalse(m_intake.retract());

    new JoystickButton(m_driverController, PS4Controller.Button.kTriangle.value)
        .whileTrue(new RunCommand(() -> m_intake.setPower(-1.0)));

    // launcher controls (button to pre-spin the launcher and button to launch)
    //new JoystickButton(m_driverController, PS4Controller.Button.kR1.value)
    //        .whileTrue(new RunCommand(() -> m_launcher.runLauncher(), m_launcher));

    //new JoystickButton(m_driverController, PS4Controller.Button.kCross.value)
    //        .onTrue(m_intake.feedLauncher(m_launcher));

    new JoystickButton(m_driverController, PS4Controller.Button.kCircle.value)
            .whileTrue(new RunCommand(() -> m_climber.setPower(.5)))
            .onFalse(new RunCommand(() -> m_climber.setPower(0)));
    new JoystickButton(m_driverController, PS4Controller.Button.kSquare.value)
            .whileTrue(new RunCommand(() -> m_climber.setPower(-.5)))
            .onFalse(new RunCommand(() -> m_climber.setPower(0)));
    // set up arm manual and auto functions
}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
