package frc.robot;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.math.controller.PIDController;
//import edu.wpi.first.networktables.NetworkTableInstance;
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
//  NetworkTableInstance instance;

  public RobotContainer() {
    camera = new PhotonCamera("Photonvision");
    result = camera.getLatestResult();
    target = result.getBestTarget();
    yaw = target.getYaw();
    pid = new PIDController(0.1, 0, 0.1);

    tankDrive.setDefaultCommand(new DefaultDrive(() -> 0, () -> pid.calculate(target.getYaw(), 0), tankDrive));

    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
