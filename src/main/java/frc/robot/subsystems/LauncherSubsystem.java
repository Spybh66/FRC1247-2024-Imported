package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LauncherSubsystem extends SubsystemBase {

  private WPI_VictorSPX m_topMotor;
  private WPI_VictorSPX m_bottomMotor;

  private boolean m_launcherRunning;

  /** Creates a new LauncherSubsystem. */
  public LauncherSubsystem() {

    m_topMotor = new WPI_VictorSPX(Constants.Launcher.kTopCanId);
    m_topMotor.setInverted(false);
    m_topMotor.setNeutralMode(NeutralMode.Coast);

    m_bottomMotor = new WPI_VictorSPX(Constants.Launcher.kBottomCanId);
    m_bottomMotor.setInverted(false);
    m_bottomMotor.setNeutralMode(NeutralMode.Coast);
    // create two new SPARK MAXs and configure them
/*     m_topMotor =
        new CANSparkMax(Constants.Launcher.kTopCanId, CANSparkLowLevel.MotorType.kBrushless);
    m_topMotor.setInverted(false);
    m_topMotor.setSmartCurrentLimit(Constants.Launcher.kCurrentLimit);
    m_topMotor.setIdleMode(IdleMode.kBrake);

    m_topMotor.burnFlash();

    m_bottomMotor =
        new CANSparkMax(Constants.Launcher.kBottomCanId, CANSparkLowLevel.MotorType.kBrushless);
    m_bottomMotor.setInverted(false);
    m_bottomMotor.setSmartCurrentLimit(Constants.Launcher.kCurrentLimit);
    m_bottomMotor.setIdleMode(IdleMode.kBrake);

    m_bottomMotor.burnFlash(); */

    m_launcherRunning = false;
  }

  /**
   * Turns the launcher on. Can be run once and the launcher will stay running or run continuously
   * in a {@code RunCommand}.
   */
  public void runLauncher() {
    m_launcherRunning = true;
  }

  /**
   * Turns the launcher off. Can be run once and the launcher will stay running or run continuously
   * in a {@code RunCommand}.
   */
  public void stopLauncher() {
    m_launcherRunning = false;
  }

  @Override
  public void periodic() { // this method will be called once per scheduler run
    // set the launcher motor powers based on whether the launcher is on or not
    if (m_launcherRunning) {
      m_topMotor.set(Constants.Launcher.kTopPower);
      m_bottomMotor.set(Constants.Launcher.kBottomPower);
    } else {
      m_topMotor.set(0.0);
      m_bottomMotor.set(0.0);
    }
  }
}