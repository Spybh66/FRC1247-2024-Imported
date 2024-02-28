package frc.robot;

import frc.robot.I2CHandler;

public class IMU {
    private I2CHandler acc;
    private I2CHandler gyro;
    public IMU () {
        acc = new I2CHandler(Constants.IMU.ACC_ADDRESS, Constants.IMU.ACC_A_TO_READ);
        gyro = new I2CHandler(Constants.IMU.GYRO_ADDRESS, Constants.IMU.GYRO_G_TO_READ);
    }

    public void retrieveData() {
        byte[] accData = acc.retrieveData(Constants.IMU.ACC_DATA);
        byte[] gyroData = gyro.retrieveData(Constants.IMU.GYRO_DATA);
    }
    
}


