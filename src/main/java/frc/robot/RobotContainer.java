package frc.robot;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Commands.AutoEngageCommand;
import frc.robot.Commands.DefaultDrive;
import frc.robot.Subsystems.Limelight;
import frc.robot.Subsystems.drive;

public class RobotContainer {
/*  PhotonCamera camera;
  PhotonTrackedTarget target;
  PhotonPipelineResult result;*/
  Limelight cam;
  double yaw;
  drive tankDrive;
  PIDController angle_pid;
  PIDController move_pid;
  PIDController side_pid;
  CommandXboxController controller;
  Trigger A;
  Trigger B;
  Trigger X;
  AHRS navX;

  public RobotContainer() {
    cam = new Limelight();
    controller = new CommandXboxController(0);
/*    camera = new PhotonCamera("Microsoft_LifeCam_HD-3000");
    result = new PhotonPipelineResult();
    target = new PhotonTrackedTarget();*/
    A = controller.a();
    B = controller.b();
    X = controller.x();

    side_pid = new PIDController(5, 0, 0);
    angle_pid = new PIDController(0.045, 0, 0.25);
    move_pid = new PIDController(0.8, 0.1, 0);

    angle_pid.setTolerance(5);

    navX = new AHRS(SPI.Port.kMXP);

    tankDrive = new drive(navX);

    tankDrive.setDefaultCommand(new DefaultDrive(() -> controller.getLeftY(),
     () -> controller.getRightX(), 
     tankDrive));

    configureBindings();
  }

  private void configureBindings() {
    A.onTrue(new DefaultDrive(() -> 0, () -> -angle_pid.calculate(cam.getyaw(), 0), tankDrive));
    A.onFalse(new InstantCommand(() -> tankDrive.stop(), tankDrive));

    B.onTrue(new DefaultDrive(() -> -move_pid.calculate(cam.getPose().getX(), 14), () -> 0, tankDrive));
    B.onFalse(new InstantCommand(() -> tankDrive.stop(), tankDrive));
  
//    X.onTrue(onXPressed());
    X.onTrue(new DefaultDrive(() -> -side_pid.calculate(cam.getPose().getY(), 0), () -> 0, tankDrive));
    X.onFalse(new InstantCommand(() -> tankDrive.stop(), tankDrive));

    controller.y().onTrue(new AutoEngageCommand(tankDrive, navX));
    controller.y().onFalse(new InstantCommand(() -> tankDrive.stop(), tankDrive));
  
    tankDrive.setDefaultCommand(new DefaultDrive(() -> controller.getLeftY(),
    () -> controller.getRightX(), 
    tankDrive));
  }
}