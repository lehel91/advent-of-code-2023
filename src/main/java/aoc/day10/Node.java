package aoc.day10;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
  private char direction;
  private int distance;
  private boolean isPartOfTheLoop;
  private boolean isInsideTheLoop;

  public Node(char direction) {
    this.direction = direction;
  }
}
