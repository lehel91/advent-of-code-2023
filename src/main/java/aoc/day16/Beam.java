package aoc.day16;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Beam {
  private static long counter = 0L;
  private long beamId;
  private Point position;
  private char direction;

  public Beam() {
    beamId = counter++;
    this.position = new Point(0, 0);
    this.direction = 'E';
  }

  public Beam(char direction) {
    beamId = counter++;
    this.position = new Point(0, 0);
    this.direction = direction;
  }

  public Beam(Point position, char direction) {
    this(direction);
    this.position = position;
  }

  public void move() {
    switch (direction) {
      case 'N' :
        position.y--;
        break;
      case 'W' :
        position.x = position.x-1;
        break;
      case 'S' :
        position.y++;
        break;
      case 'E' :
        position.x = position.x+1;
        break;
      default:
        break;
    }
  }
}
