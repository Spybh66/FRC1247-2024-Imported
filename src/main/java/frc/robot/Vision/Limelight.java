// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Vision;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.Util.RectanglePoseArea;

public class Limelight extends SubsystemBase {
    DrivetrainSubsystem drivetrain;
    Alliance alliance;
    private String ll = "limelight";
    private Boolean enable = false;
    private Boolean trust = false;
    private int fieldError = 0;
    private int distanceError = 0;
    private Pose2d botpose;
    private static final RectanglePoseArea field = new RectanglePoseArea(new Translation2d(0.0, 0.0), new Translation2d(16.54, 8.02));

    /** Creates a new Limelight. */
    public Limelight(DrivetrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
        SmartDashboard.putNumber("Field Error", fieldError);
        SmartDashboard.putNumber("Limelight Error", distanceError);
    }

    @Override
    public void periodic() {
        if (DriverStation.isEnabled()) {
            trust = false;
        } else {
            trust = true;
        }

/*         if (enable) {
            Double targetDistance = LimelightHelpers.getTargetPose3d_CameraSpace(ll).getTranslation().getDistance(new Translation3d());
            Double confidence = 1 - ((targetDistance - 1) / 6);
            LimelightHelpers.Results result = LimelightHelpers.getLatestResults(ll).targetingResults;
            if (result.valid) {
                if (alliance == Alliance.Blue) {
                    botpose = LimelightHelpers.getBotPose2d_wpiBlue(ll);
                } else if (alliance == Alliance.Red) {
                    botpose = LimelightHelpers.getBotPose2d_wpiRed(ll);
                }
                if (field.isPoseWithinArea(botpose)) {
                    if (drivetrain.getState().Pose.getTranslation().getDistance(botpose.getTranslation()) < 0.5 || trust
                            || result.targets_Fiducials.length > 1) {
                        drivetrain.addVisionMeasurement(botpose,
                                Timer.getFPGATimestamp() - (result.latency_capture / 1000.0) - (result.latency_pipeline / 1000.0),
                                VecBuilder.fill(confidence, confidence, .01));
                    } else {
                        distanceError++;
                        SmartDashboard.putNumber("Limelight Error", distanceError);
                    }
                } else {
                    fieldError++;
                    SmartDashboard.putNumber("Field Error", fieldError);
                }
            }
        } */
    }

    public void setAlliance(Alliance alliance) {
        this.alliance = alliance;
    }

    public void useLimelight(boolean enable) {
        this.enable = enable;
    }

    public void trustLL(boolean trust) {
        this.trust = trust;
    }
}
