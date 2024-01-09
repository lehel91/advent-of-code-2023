package aoc.day17;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@AllArgsConstructor
@ToString
public class Node implements Comparable<Node> {
  int row;
  int col;
  int cost;
  int consecutiveMoves;
  int direction;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Node node = (Node) o;
    return row == node.row && col == node.col && direction == node.direction &&
           consecutiveMoves == node.consecutiveMoves;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col, direction);
  }

  @Override
  public int compareTo(Node other) {
    return Integer.compare(this.cost, other.cost);
  }
}