package frc.robot.Subsystems;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  private NetworkTable limelight;
  private boolean pipelineIndex;
  private double[] posevalues;
  private String team;

  public Limelight() {
    limelight = NetworkTableInstance.getDefault().getTable("limelight");
    pipelineIndex = false;
    team = "red";
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

  public double getYaw() {
    SmartDashboard.putNumber("Yaw", limelight.getEntry("tx").getDouble(0));
    return limelight.getEntry("tx").getDouble(0);
  }

  public double getPitch() {
    SmartDashboard.putNumber("Pitch", limelight.getEntry("ty").getDouble(0));
    return limelight.getEntry("ty").getDouble(0);
  }

  public double getArea() {
    SmartDashboard.putNumber("Area", limelight.getEntry("ta").getDouble(0));
    return limelight.getEntry("ta").getDouble(0);
  }

  public Pose2d getPose() {
    if(team == "blue") {
      SmartDashboard.putNumberArray("Pose", limelight.getEntry("botpose_wpiblue").getDoubleArray(new double[6]));
      posevalues = limelight.getEntry("botpose_wpiblue").getDoubleArray(new double[6]);
    }
    if(team == "red") {
      SmartDashboard.putNumberArray("Yaw", limelight.getEntry("botpose_wpired").getDoubleArray(new double[6]));
      posevalues = limelight.getEntry("botpose_wpired").getDoubleArray(new double[6]);
    }
    Translation2d translate = new Translation2d(posevalues[0], posevalues[1]);
    Rotation2d rotation = new Rotation2d(Math.toRadians(posevalues[3]));
    return new Pose2d(translate, rotation);
  }

  public Pose2d getTarget2d() {
    posevalues = limelight.getEntry("targetpose_cameraspace").getDoubleArray(new double[6]);
    SmartDashboard.putNumberArray("Distance Pose", posevalues);
    Translation2d translate = new Translation2d(posevalues[3], posevalues[4]);
    Rotation2d rotation = new Rotation2d(Math.toRadians(posevalues[3]));
    return new Pose2d(translate, rotation);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Yaw", getYaw());
    SmartDashboard.putNumber("Pitch", getPitch());
    SmartDashboard.putNumber("X", getPose().getX());
    SmartDashboard.putNumber("Y", getPose().getY());
    SmartDashboard.putNumber("PoseRot", getPose().getRotation().getDegrees());
    SmartDashboard.putNumber("Dist X", getTarget2d().getX());
    SmartDashboard.putNumber("Dist Y", getTarget2d().getY());
  }


}
