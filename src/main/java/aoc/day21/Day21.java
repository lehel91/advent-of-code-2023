package aoc.day21;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Day21 implements Day {


  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day21.txt");

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    Set<Point> positions = new HashSet<>();
    positions.add(getStartingPoint());

    for (int i = 0; i < 64; i++) {
      Iterator<Point> iterator = positions.iterator();

      Set<Point> toAdd = new HashSet<>();
      Set<Point> toRemove = new HashSet<>();

      while (iterator.hasNext()) {
        Point point = iterator.next();
        Set<Point> neighborCandidates = getPossibleNeighbors(point);

        for (Point pNeighbor : neighborCandidates) {
          try {
            if (INPUT_LINES.get(pNeighbor.y).charAt(pNeighbor.x) == '.' || INPUT_LINES.get(pNeighbor.y).charAt(pNeighbor.x) == 'S') {
              toAdd.add(pNeighbor);
            }
          } catch (IndexOutOfBoundsException e) {
            // Ignore
          }
        }
        toRemove.add(point);
      }

      positions.addAll(toAdd);
      positions.removeAll(toRemove);
    }

    System.out.println("Part I: " + positions.size() + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private Set<Point> getPossibleNeighbors(Point point) {
    Set<Point> neighbors = new HashSet<>();
    neighbors.add(new Point(point.x - 1, point.y));
    neighbors.add(new Point(point.x + 1, point.y));
    neighbors.add(new Point(point.x, point.y - 1));
    neighbors.add(new Point(point.x, point.y + 1));

    return neighbors;
  }

  private Point getStartingPoint() {
    for (int i = 0; i < INPUT_LINES.size(); i++) {
      for (int j  = 0; j < INPUT_LINES.get(i).length(); j++) {
        if (INPUT_LINES.get(i).charAt(j) == 'S') {
          return new Point(j, i);
        }
      }
    }

    return null;
  }

}
