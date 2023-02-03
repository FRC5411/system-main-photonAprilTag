package frc.robot.Subsystems;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Test code to change between pipelines on limelight
public class Limelight extends SubsystemBase {
  private NetworkTable limelight;
  private boolean pipelineIndex;
  private double[] posevalues;
  private String team;

  public Limelight() {
    limelight = NetworkTableInstance.getDefault().getTable("limelight");
    pipelineIndex = false;
    team = "blue";
  }

  public void switchPipeline() {
    limelight.getEntry("pipeline").setNumber(!pipelineIndex ? 1 : 0);
  }

  public int getPipeLineIndex() {
    return pipelineIndex ? 1 : 0;
  }

  public boolean hastarget() {
    double bool = limelight.getEntry("tv").getDouble(0);
    if(bool == 0) {
      return false;
    }
    return true;
  }

  public double getyaw() {
    return limelight.getEntry("tx").getDouble(0);
  }

  public double getPitch() {
    return limelight.getEntry("ty").getDouble(0);
  }

  public double getArea() {
    return limelight.getEntry("ta").getDouble(0);
  }

  public Pose2d getPose() {
    if(team == "blue") {
      posevalues = limelight.getEntry("botpose_wpiblue").getDoubleArray(new double[6]);
    }
    if(team == "red") {
      posevalues = limelight.getEntry("botpose_wpired").getDoubleArray(new double[6]);
    }
    Translation2d translate = new Translation2d(posevalues[0], posevalues[1]);
    Rotation2d rotation = new Rotation2d(posevalues[3], posevalues[4]);
    return new Pose2d(translate, rotation);
  }
/*
  public double getTapeXDistance() {
    return LL.SLOPE * getArea() + LL.YINT;
  }

  public double getTapeYDistance() {
    return Math.tan(getyaw()) * getTapeXDistance();
  }

  public double alignTarget(double dist) {
    PIDController LLAlign = new PIDController(0, 0, 0);
    return LLAlign.calculate(dist, 0);
  }

  public double movetotarget(double dist) {
    PIDController move = new PIDController(0, 0, 0);
    return move.calculate(dist, 0);
  }*/

  @Override
  public void periodic() {}


}