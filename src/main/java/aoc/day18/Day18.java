package aoc.day18;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Day18 implements Day {

  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day18.txt");
  private List<Point> outline;
  long steps = 0L;

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    initializePoints(true);

    System.out.println("Part I: " + calculateArea() + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    initializePoints(false);

    System.out.println("Part II: " + calculateArea() + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private long calculateArea() {
    long area = 0L;

    for (int i = 0; i < outline.size() - 1; i++) {
      area += ((long) outline.get(i).x * outline.get(i + 1).y -
               (long) outline.get(i).y * outline.get(i + 1).x);
    }

    area += steps;
    return Math.abs(area) / 2 + 1;
  }

  private void initializePoints(boolean partOne) {
    outline = new ArrayList<>();
    steps = 0L;

    int currentX = 0;
    int currentY = 0;
    outline.add(new Point(currentX, currentY));

    for (int i = 0; i < INPUT_LINES.size(); i++) {
      String[] parts = INPUT_LINES.get(i).split(" ");
      if (partOne) {
        String dir = parts[0];

        int stepsNumber = Integer.parseInt(parts[1]);
        switch (dir) {
          case "L":
            currentX -= stepsNumber;
            break;
          case "R":
            currentX += stepsNumber;
            break;
          case "U":
            currentY -= stepsNumber;
            break;
          case "D":
            currentY += stepsNumber;
            break;
          default:
            break;
        }
        steps += stepsNumber;
      } else {
        char dir = parts[2].charAt(parts[2].length() - 2);

        int stepsNumber = Integer.parseInt(parts[2].substring(2, parts[2].length() - 2), 16);
        switch (dir) {
          case '0':
            currentX += stepsNumber;
            break;
          case '1':
            currentY += stepsNumber;
            break;
          case '2':
            currentX -= stepsNumber;
            break;
          case '3':
            currentY -= stepsNumber;
            break;
          default:
            break;
        }
        steps += stepsNumber;
      }

      outline.add(new Point(currentX, currentY));
    }
  }
}
