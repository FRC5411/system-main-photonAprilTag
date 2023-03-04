// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.led.CANdle;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANDLE extends SubsystemBase {
  private CANdle LED;
  private PWMSparkMax LEDS;

  public CANDLE() {
    LED = new CANdle(12);
    LEDS = new PWMSparkMax(0);
    }

  public void count() {
    LED.setLEDs(240, 240, 240, 240, 0, 400);
  }

  public void stop() {
    LED.setLEDs(0, 0, 0, 0, 0, 0);
  }

  @Override
  public void periodic() {
    LEDS.set(0.5);
  }
}
