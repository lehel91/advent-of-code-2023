package aoc.day3;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 implements Day {

  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day3.txt");
  private final char[][] engineSchematic;
  private final Map<Point, List<Integer>> asteriskNeighbours;

  public Day3() {
    engineSchematic = new char[INPUT_LINES.size()][INPUT_LINES.get(0).length()];
    asteriskNeighbours = new HashMap<>();
    for (int i = 0; i < INPUT_LINES.size(); i++) {
      for (int j = 0; j < INPUT_LINES.get(i).length(); j++) {
        engineSchematic[i][j] = INPUT_LINES.get(i).charAt(j);

        if (INPUT_LINES.get(i).charAt(j) == '*') {
          asteriskNeighbours.put(new Point(j , i), new ArrayList<>());
        }
      }
    }
  }

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    long sum = 0L;

    for (int i = 0; i < engineSchematic.length; i++) {
      StringBuilder currentNumber = new StringBuilder();
      int leftBound = 0;
      int rightBound = 0;
      for (int j = 0; j < engineSchematic[i].length; j++) {
        if (Character.isDigit(engineSchematic[i][j])) {
          if (currentNumber.toString().isEmpty()) {
            leftBound = j;
          }
          currentNumber.append(engineSchematic[i][j]);
          if (j == engineSchematic[0].length - 1 && Character.isDigit(engineSchematic[i][j])) {
            rightBound = j;
          }
        } else {
          rightBound = j - 1;
        }

        if (!currentNumber.isEmpty() && (!Character.isDigit(engineSchematic[i][j]) || j == engineSchematic[0].length - 1)) {

          boolean isPartNumber = isPartNumber(Integer.parseInt(currentNumber.toString()), i, leftBound, rightBound, false);
          isPartNumber(Integer.parseInt(currentNumber.toString()), i, leftBound, rightBound, true);

          if (isPartNumber) {
            sum += Integer.parseInt(currentNumber.toString());
          }

          currentNumber = new StringBuilder();
        }
      }
    }

    System.out.println("Part I: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    long sum = 0;
    for (List<Integer> neighbours : asteriskNeighbours.values()) {
      if (neighbours.size() == 2) {
        sum += (long) neighbours.get(0) * neighbours.get(1);
      }
    }

    System.out.println("Part II: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private boolean isPartNumber(int number, int rowNumber, int leftBound, int rightBound, boolean asteriskCheck) {
    boolean isPartNumber = false;
    for (int i = leftBound; i <= rightBound; i++) {
      isPartNumber = checkNeighbours(number, rowNumber, i, asteriskCheck);
      if (isPartNumber) {
        break;
      }
    }

    return isPartNumber;
  }

  private boolean checkNeighbours(int number, int row, int column, boolean asteriskCheck) {
    for (int i = row - 1; i <= row + 1; i++) {
      for (int j = column - 1; j <= column + 1; j++) {
        try {
          if (!Character.isDigit(engineSchematic[i][j]) &&
              (engineSchematic[i][j] != '.' || asteriskCheck && engineSchematic[i][j] == '*')) {

            if (asteriskCheck && engineSchematic[i][j] == '*') {
              List<Integer> numbers = asteriskNeighbours.get(new Point(j, i));
              numbers.add(number);
              asteriskNeighbours.put(new Point(j, i), numbers);
            }
            return true;
          }
        } catch (ArrayIndexOutOfBoundsException e) {
          // Ignore
        }
      }
    }

    return false;
  }
}
