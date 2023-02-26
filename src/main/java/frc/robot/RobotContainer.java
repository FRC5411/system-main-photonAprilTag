package frc.robot;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Commands.AutoEngageCommand;
import frc.robot.Commands.DefaultDrive;
import frc.robot.Commands.anglealign;
import frc.robot.Commands.distalign;
import frc.robot.Subsystems.Limelight;
import frc.robot.Subsystems.drive;

public class RobotContainer {
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
    A = controller.a();
    B = controller.b();
    X = controller.x();

    side_pid = new PIDController(5, 0, 0);
    angle_pid = new PIDController(0.04, 0, 0.2);
//    angle_pid = new PIDController(0, 0, 0);
    move_pid = new PIDController(0.8, 0.1, 0);

    angle_pid.setTolerance(1);

    navX = new AHRS(SPI.Port.kMXP);

    navX.zeroYaw();

    tankDrive = new drive(navX);

    tankDrive.setDefaultCommand(new DefaultDrive(() -> controller.getLeftY(),
     () -> controller.getRightX(), 
     tankDrive));

    configureBindings();
  }

  private void configureBindings() {
    controller.a().onTrue(new anglealign(cam, tankDrive, angle_pid));
    controller.a().onFalse(new InstantCommand(tankDrive::stop, tankDrive));

    controller.b().onTrue(new DefaultDrive(() -> move_pid.calculate(cam.getPose().getX(), 2.6), () -> 0, tankDrive));
    controller.b().onFalse(new InstantCommand(tankDrive::stop, tankDrive));

    controller.y().onTrue(new AutoEngageCommand(tankDrive, navX));
    controller.y().onFalse(new InstantCommand(() -> tankDrive.stop(), tankDrive));
  
    tankDrive.setDefaultCommand(new DefaultDrive(() -> controller.getLeftY(),
    () -> controller.getRightX(), 
    tankDrive));
  }
}