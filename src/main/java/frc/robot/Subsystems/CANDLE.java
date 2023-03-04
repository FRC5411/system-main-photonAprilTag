// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.led.CANdle;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANDLE extends SubsystemBase {
  private CANdle LED;

  public CANDLE() {
    LED = new CANdle(10);
  }

  public void count() {
    LED.setLEDs(240, 240, 240, 240, 0, 400);
  }

  public void stop() {
    LED.setLEDs(0, 0, 0, 0, 0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
