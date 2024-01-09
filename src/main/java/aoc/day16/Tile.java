package aoc.day16;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Tile {
  char type;
  boolean energized;
  boolean splitted;

  public Tile(char type) {
    this.type = type;
  }
}
