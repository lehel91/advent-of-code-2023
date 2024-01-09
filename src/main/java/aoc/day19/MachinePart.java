package aoc.day19;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MachinePart {
  private int x;
  private int m;
  private int a;
  private int s;

  public MachinePart(String values) {
    String[] valuesArray = values.split(",");

    for (String value : valuesArray) {
      String[] valueParts = value.split("=");

      switch (valueParts[0]) {
        case "x":
          this.x = Integer.parseInt(valueParts[1]);
          break;
        case "m":
          this.m = Integer.parseInt(valueParts[1]);
          break;
        case "a":
          this.a = Integer.parseInt(valueParts[1]);
          break;
        case "s":
          this.s = Integer.parseInt(valueParts[1]);
          break;
        default:
          break;
      }
    }
  }

  public long getRatingsSum() {
    return (long) x + m + a + s;
  }
}
