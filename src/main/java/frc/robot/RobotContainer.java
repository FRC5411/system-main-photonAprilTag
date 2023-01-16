package frc.robot;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Commands.DefaultDrive;
import frc.robot.Subsystems.drive;

public class RobotContainer {
  PhotonCamera camera;
  PhotonTrackedTarget target;
  PhotonPipelineResult result;
  double yaw;
  drive tankDrive;
  PIDController pid;
  CommandXboxController controller;
  Trigger A;
//  NetworkTableInstance instance;

  public RobotContainer() {
    controller = new CommandXboxController(0);
    tankDrive = new drive();
    camera = new PhotonCamera("Microsoft_LifeCam_HD-3000");
    result = new PhotonPipelineResult();
    target = new PhotonTrackedTarget();
    A = controller.a();

    pid = new PIDController(0.03, 0, 0.025);

    tankDrive.setDefaultCommand(new DefaultDrive(() -> controller.getLeftY(),
     () -> controller.getRightX(), 
     tankDrive));

    configureBindings();
  }

  private void configureBindings() {
    A.onTrue(new DefaultDrive(() -> 0, () -> pid.calculate(april_tag(), yaw), tankDrive));
    A.onFalse(new DefaultDrive(() -> 0, () -> 0, tankDrive));
  }

  public double april_tag() {
    result = camera.getLatestResult();
    if(result.hasTargets() == true){
      SmartDashboard.putBoolean("Has Targets", result.hasTargets());
      target = result.getBestTarget();
      return target.getYaw();
    }
    return 0.0;
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
