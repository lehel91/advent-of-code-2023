package aoc.day11;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day11 implements Day {
  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day11.txt");
  private List<LongPoint> galaxy;

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    galaxy = new ArrayList<>();

    expandUniverse(1);
    long sum = 0L;

    for (int i = 0; i < galaxy.size(); i++) {
      for (int j = i + 1; j < galaxy.size(); j++) {
        sum += AocUtilities.getManhattanDistance(galaxy.get(i).getX(), galaxy.get(i).getY(),
            galaxy.get(j).getX(), galaxy.get(j).getY());
      }
    }

    System.out.println("Part I: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    galaxy = new ArrayList<>();

    expandUniverse(1_000_000);
    long sum = 0L;

    for (int i = 0; i < galaxy.size(); i++) {
      for (int j = i + 1; j < galaxy.size(); j++) {
        sum += AocUtilities.getManhattanDistance(galaxy.get(i).getX(), galaxy.get(i).getY(),
            galaxy.get(j).getX(), galaxy.get(j).getY());
      }
    }

    System.out.println("Part II: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private void expandUniverse(long multiplier) {
    Set<Integer> occupiedRows = new HashSet<>();
    Set<Integer> occupiedColumns = new HashSet<>();

    for (int i = 0; i < INPUT_LINES.size(); i++) {
      for (int j = 0; j < INPUT_LINES.size(); j++) {
        if (INPUT_LINES.get(i).charAt(j) == '#') {
          occupiedRows.add(i);
          occupiedColumns.add(j);
          galaxy.add(new LongPoint(j, i));
        }
      }
    }

    for (LongPoint point : galaxy) {
      long xToGain = 0;
      long yToGain = 0;
      for (int x = 0; x < point.getX(); x++) {
        if (!occupiedColumns.contains(x)) {
          xToGain++;
        }
      }

      for (int y = 0; y < point.getY(); y++) {
        if (!occupiedRows.contains(y)) {
          yToGain++;
        }
      }
      point.setX(point.getX() + xToGain * (multiplier - 1));
      point.setY(point.getY() + yToGain * (multiplier - 1));
    }
  }
}
