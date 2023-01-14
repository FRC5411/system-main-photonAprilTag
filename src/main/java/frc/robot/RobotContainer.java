// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Commands.DefaultDrive;
import frc.robot.Subsystems.drive;

public class RobotContainer {
  PhotonCamera camera;
  PhotonTrackedTarget target;
  PhotonPipelineResult result;
  double yaw;
  drive tankDrive;
  PIDController pid;
  NetworkTableInstance instance;
  NetworkTableEntry table;

  public RobotContainer() {
    table = new NetworkTableEntry(instance, 0);
    camera = new PhotonCamera(instance, "Photonvision");
    result = camera.getLatestResult();
    target = result.getBestTarget();
    yaw = target.getYaw();
    pid = new PIDController(0, 0, 0);

    tankDrive.setDefaultCommand(new DefaultDrive(() -> 0, () -> pid.calculate(target.getYaw(), 0), tankDrive));


    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
