package aoc.day11;

import lombok.Getter;

@Getter
public class LongPoint {
  private long x;
  private long y;

  public LongPoint(long x, long y) {
    this.x = x;
    this.y = y;
  }

  public void setX(long x) {
    this.x = x;
  }

  public void setY(long y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  // Add any additional methods or functionality as needed
}