package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.SparkRelativeEncoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.PIDGains;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {

  private CANSparkMax m_leftClimbMotor;
  private CANSparkMax m_rightClimbMotor;
  //private RelativeEncoder m_encoder;
  //private SparkPIDController m_controller;

  private boolean m_positionMode;
  private double m_targetPosition;
  private double m_power;

  /** Creates a new IntakeSubsystem. */
  public ClimberSubsystem() {
    // create a new SPARK MAX and configure it
    m_leftClimbMotor = new CANSparkMax(Constants.Climber.kLeftClimber, MotorType.kBrushless);
    m_leftClimbMotor.setInverted(false);
    m_leftClimbMotor.setIdleMode(IdleMode.kBrake);

    m_rightClimbMotor = new CANSparkMax(Constants.Climber.kRightClimber, MotorType.kBrushless);
    m_rightClimbMotor.setInverted(false);
    m_rightClimbMotor.setIdleMode(IdleMode.kBrake);

    //m_encoder = m_motor.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);

    //m_controller = m_motor.getPIDController();
    //PIDGains.setSparkMaxGains(m_controller, Constants.Intake.kPositionGains);

    m_leftClimbMotor.burnFlash();
    m_rightClimbMotor.burnFlash();

    //m_positionMode = false;
    //m_targetPosition = m_encoder.getPosition();
    m_power = 0.0;
  }

  /**
   * Set the power to spin the motor at. This only applies outside of position mode.
   *
   * @param _power The power to apply to the motor (from -1.0 to 1.0).
   */
  public void setPower(double _power) {
    m_positionMode = false;
    //m_targetPosition = m_encoder.getPosition();
    m_power = _power;
  }

  @Override
  public void periodic() { // This method will be called once per scheduler run
    m_leftClimbMotor.set(m_power);
    m_rightClimbMotor.set(m_power);
    SmartDashboard.putNumber("CL", m_leftClimbMotor.getOutputCurrent());
    SmartDashboard.putNumber("CR", m_rightClimbMotor.getOutputCurrent());
    SmartDashboard.putNumber("CRV", m_rightClimbMotor.getBusVoltage());

  }
}