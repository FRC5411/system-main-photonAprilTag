package frc.robot;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
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

  public RobotContainer() {
    cam = new Limelight();
    controller = new CommandXboxController(0);
    tankDrive = new drive();
/*    camera = new PhotonCamera("Microsoft_LifeCam_HD-3000");
    result = new PhotonPipelineResult();
    target = new PhotonTrackedTarget();*/
    A = controller.a();
    B = controller.b();
    X = controller.x();

    side_pid = new PIDController(5, 0, 0);
    angle_pid = new PIDController(0.03, 0, 1);
    move_pid = new PIDController(1, 0, 0);

    tankDrive.setDefaultCommand(new DefaultDrive(() -> controller.getLeftY(),
     () -> controller.getRightX(), 
     tankDrive));

    configureBindings();
  }

  private void configureBindings() {
    A.onTrue(new DefaultDrive(() -> 0, () -> -angle_pid.calculate(cam.getyaw(), 0), tankDrive));
    A.onFalse(new InstantCommand(() -> tankDrive.stop(), tankDrive));

    B.onTrue(new DefaultDrive(() -> move_pid.calculate(cam.getPose().getX(), 0.05), () -> 0, tankDrive));
    B.onFalse(new InstantCommand(() -> tankDrive.stop(), tankDrive));
  
//    X.onTrue(onXPressed());
    X.onTrue(new DefaultDrive(() -> -side_pid.calculate(cam.getPose().getY(), 0), () -> 0, tankDrive));
    X.onFalse(new InstantCommand(() -> tankDrive.stop(), tankDrive));
  
  tankDrive.setDefaultCommand(new DefaultDrive(() -> controller.getLeftY(),
    () -> controller.getRightX(), 
    tankDrive));
  }
/*
  public double target_yaw() {
    result = camera.getLatestResult();
    if(result.hasTargets() == true){
      SmartDashboard.putBoolean("Has Targets", result.hasTargets());
      target = result.getBestTarget();
      SmartDashboard.putNumber("Yaw", target.getYaw());
      return target.getYaw();
    }
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  }

  public double target_x() {
    result = camera.getLatestResult();
    if(result.hasTargets() == true){
      SmartDashboard.putBoolean("Has Targets", result.hasTargets());
      target = result.getBestTarget();
      double x = target.getBestCameraToTarget().getY();
      return x;
    }
    return 0.0;
  }

  public double target_y() {
    result = camera.getLatestResult();
    if(result.hasTargets() == true){
      SmartDashboard.putBoolean("Has Targets", result.hasTargets());
      target = result.getBestTarget();
      double y = target.getBestCameraToTarget().getY();
      SmartDashboard.putNumber("Y", y);
      return y;
    }
    return 0.0;
  }

  public Command onXPressed()
  {
    // Called once when the X button is pressed.

    side_pid.reset();
     SmartDashboard.putString("Value","onXPressed called " + 
    " 10 " + side_pid.calculate(1000.0) + 
    "  8 " + side_pid.calculate(8.0) + 
    "  6 " + side_pid.calculate(6.0) + 
    "  4 " + side_pid.calculate(4.0) + 
    "  2 " + side_pid.calculate(2.0) + 
    "  0 " + side_pid.calculate(8.0) +
    "\n");
       SmartDashboard.putString("X", "ON XPRESSEdON XPRESSEdON XPRESSEdON XPRESSEdON XPRESSEdON XPRESSEdON XPRESSEdON XPRESSEd");
    

    side_pid.reset();
    //DoubleSupplier speed = () -> calcSpeedForSideAlignment();//calculate(side_pid, target_yaw(), 0, "PID");
    //DoubleSupplier rotation = () -> 0.0;

    //System.out.print(speed);


    //return new DefaultDrive(speed, rotation, tankDrive);
    return new DefaultDrive(() -> calcSpeedForSideAlignment(), () -> 0.0, tankDrive);
  }

  public double deadzone(double input) {
    if(Math.abs(input) > 0.25) {
      input = Math.signum(input) * 0.25;
    }
    return input;
  }

  public double calcSpeedForSideAlignment ()
  {
    double measure = target_y();
    double speed = controlled_calculate(side_pid, measure, 0, "Name");

    SmartDashboard.putNumber("speed", speed);

    return -speed;
    
  }

  public double controlled_calculate(PIDController PID, double measure, double setpoint, String name) {
    SmartDashboard.putNumber(name, PID.calculate(measure, 0));
    double value = PID.calculate(measure, 0);
    if(value > 0.25) {
      value = 0.25;
    }
    return value;
  }
*/
}
