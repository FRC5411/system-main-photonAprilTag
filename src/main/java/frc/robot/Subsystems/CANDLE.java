package frc.robot.Subsystems;
import com.ctre.phoenix.led.CANdle;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANDLE extends SubsystemBase {
  private CANdle CANLED;
  private AddressableLED LEDS;
  private AddressableLEDBuffer mbuffer;

  public CANDLE() {
    CANLED = new CANdle(12);
    LEDS = new AddressableLED(0);
    mbuffer = set(255, 255, 255);

    LEDS.setData(mbuffer);
  }

  public void count() {
    CANLED.setLEDs(240, 240, 240, 240, 0, 400);
  }

  public void stop() {
    CANLED.setLEDs(0, 0, 0, 0, 0, 0);
  }

  public AddressableLEDBuffer set(int r, int g, int b) {
    AddressableLEDBuffer buffer = new AddressableLEDBuffer(400);
    int i = 0;
    while(buffer.getLength() < i) {
      buffer.setRGB(i, r, g, b);
      i += 1;
    }
    return buffer;
  }

  @Override
  public void periodic() {
    set(255, 255, 255);
  }
}
