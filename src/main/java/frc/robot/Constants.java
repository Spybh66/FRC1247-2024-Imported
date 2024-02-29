// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.lib.PIDGains;
import java.lang.Math;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final double kDriveDeadband = 0.05;
        public static final double kTriggerButtonThreshold = 0.5;
      }
    
    public static final class Drivetrain {
        public static final int kFrontLeftCanId = 1;
        public static final int kFrontRightCanId = 4;
        public static final int kRearLeftCanId = 2;
        public static final int kRearRightCanId = 3;

        public static final boolean kFrontLeftInverted = false;
        public static final boolean kFrontRightInverted = true;
        public static final boolean kRearLeftInverted = false;
        public static final boolean kRearRightInverted = true;

        public static final int kCurrentLimit = 55;

        public static final double kTurningScale = 0.5;
    }

    public static final class Arm {
        public static final int kArm1CanId = 8;
        public static final int kArm2CanId = 9;
        public static final double kArmPowerScale = 1;



        public static final boolean kArmInverted = true;
        public static final int kCurrentLimit = 40;
    
        public static final double kSoftLimitReverse = -1.15;
        public static final double kSoftLimitForward = 0.0;
    
        public static final double kArmGearRatio = (1.0 / 25.0) * (28.0 / 50.0) * (16.0 / 64.0);
        public static final double kPositionFactor =
            kArmGearRatio
                * 2.0
                * Math.PI; // multiply SM value by this number and get arm position in radians
        public static final double kVelocityFactor = kArmGearRatio * 2.0 * Math.PI / 60.0;
        public static final double kArmFreeSpeed = 5676.0 * kVelocityFactor;
        public static final double kArmZeroCosineOffset =
            1.342; // radians to add to converted arm position to get real-world arm position (starts at
        // ~76.9deg angle)
        public static final ArmFeedforward kArmFeedforward =
            new ArmFeedforward(0.0, 3.0, 12.0 / kArmFreeSpeed, 0.0);
        public static final PIDGains kArmPositionGains = new PIDGains(2.5, 0.0, 0.0);
        public static final TrapezoidProfile.Constraints kArmMotionConstraint =
            new TrapezoidProfile.Constraints(1.0, 2.0);
    
        public static final double kHomePosition = 0.0;
        public static final double kScoringPosition = 0.0;
        public static final double kIntakePosition = -1.17;
      }

    public static final class Intake {
        public static final int kCanId = 7;
        public static final boolean kMotorInverted = true;
        public static final int kCurrentLimit = 80;
    
        public static final PIDGains kPositionGains = new PIDGains(1.0, 0.0, 0.0);
        public static final double kPositionTolerance = 0.5;
    
        public static final double kIntakePower = 0.4;
    
        public static final double kRetractDistance = -3.5;
    
        public static final double kShotFeedTime = 1.0;
      }

      public static final class Launcher {
        public static final int kTopCanId = 10;
        public static final int kBottomCanId = 11;
    
        public static final int kCurrentLimit = 80;
    
        public static final double kTopPower = 0.7;
        public static final double kBottomPower = 0.8;
      }

      public static final class Climber {
        public static final int kLeftClimber = 5;
        public static final int kRightClimber = 6;
      }

      public static final class IMU {
        public static final byte ACC_ADDRESS = 0x40;
        public static final byte ACC_RESET = 0x10;
        public static final byte ACC_PWR = 0x0D;
        public static final byte ACC_BW = 0x20;
        public static final byte ACC_RANGE = 0x35;
        public static final byte ACC_DATA = 0x02;
        public static final byte ACC_A_TO_READ = 6;
    
        public static final byte GYRO_ADDRESS = 0x68;
        public static final byte GYRO_DLPF_FS = 0x16;
        public static final byte GYRO_INT_CFG = 0x17;
        public static final byte GYRO_PWR_MGM = 0x3E;
        public static final byte GYRO_DATA = 0x1B;
        public static final byte GYRO_G_TO_READ = 8;
      }
}
