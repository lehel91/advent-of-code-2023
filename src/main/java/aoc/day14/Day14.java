package aoc.day14;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 implements Day {
  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day14.txt");

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    char[][] rocks = getRocks();

    for (int i = 0; i < rocks.length; i++) {
      for (int j = 0; j < rocks[0].length; j++) {
        if (rocks[i][j] == 'O') {
          move(rocks, i, j, 'N');
        }
      }
    }

    long sum = 0L;

    for (int i = 0; i < rocks.length; i++) {
      for (int j = 0; j < rocks[0].length; j++) {
        if (rocks[i][j] == 'O') {
          sum += rocks.length - i;
        }
      }
    }

    System.out.println("Part I: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    char[][] rocks = getRocks();

    int round = 0;
    Map<String, Integer> representations = new HashMap<>();

    while (true) {
      doACompleteRound(rocks);

      String representation = getStringRepresentation(rocks);

      if (representations.containsKey(representation)) {
        int firstRep = representations.get(representation);
        int roundsRemaining = (1_000_000_000 - firstRep) % (round - firstRep) - 1;

        for (int remaining = 0; remaining < roundsRemaining; remaining++) {
          doACompleteRound(rocks);
        }
        break;
      } else {
        representations.put(getStringRepresentation(rocks), round);
      }
      round++;
    }

    long sum = countTotalLoad(rocks);

    System.out.println("Part II: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private char[][] getRocks() {
    char[][] rocks = new char[INPUT_LINES.size()][INPUT_LINES.get(0).length()];
    for (int i = 0; i < INPUT_LINES.size(); i++) {
      rocks[i] = INPUT_LINES.get(i).toCharArray();
    }

    return rocks;
  }

  private void doACompleteRound(char[][] rocks) {
    char[] directions = new char[] {'N', 'W', 'S', 'E'};
    for (char dir : directions) {
      if (dir == 'N' || dir == 'W') {
        for (int j = 0; j < rocks.length; j++) {
          for (int k = 0; k < rocks[0].length; k++) {
            if (rocks[j][k] == 'O') {
              move(rocks, j, k, dir);
            }
          }
        }
      } else {
        for (int j = rocks.length - 1; j >= 0; j--) {
          for (int k = rocks[0].length - 1; k >= 0; k--) {
            if (rocks[j][k] == 'O') {
              move(rocks, j, k, dir);
            }
          }
        }
      }
    }
  }

  private void move(char[][] matrix, int i, int j, char dir) {
    if ('N' == dir) {
      for (int k = i - 1; k >= 0; k--) {
        if (matrix[k][j] == '.') {
          matrix[k][j] = 'O';
          matrix[k + 1][j] = '.';
        } else {
          break;
        }
      }
    } else if ('S' == dir) {
      for (int k = i + 1; k < matrix.length; k++) {
        if (matrix[k][j] == '.') {
          matrix[k][j] = 'O';
          matrix[k - 1][j] = '.';
        } else {
          break;
        }
      }
    } else if ('W' == dir) {
      for (int k = j - 1; k >= 0; k--) {
        if (matrix[i][k] == '.') {
          matrix[i][k] = 'O';
          matrix[i][k + 1] = '.';
        } else {
          break;
        }
      }
    } else {
      for (int k = j + 1; k < matrix[0].length; k++) {
        if (matrix[i][k] == '.') {
          matrix[i][k] = 'O';
          matrix[i][k - 1] = '.';
        } else {
          break;
        }
      }
    }
  }

  private long countTotalLoad(char[][] rocks) {
    long sum = 0L;
    for (int x = 0; x < rocks.length; x++) {
      for (int y = 0; y < rocks[0].length; y++) {
        if (rocks[x][y] == 'O') {
          sum += rocks.length - x;
        }
      }
    }

    return sum;
  }

  private String getStringRepresentation(char[][] rocks) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < rocks.length; i++) {
      for (int j = 0; j < rocks[0].length; j++) {
        sb.append(rocks[i][j]);
      }
    }

    return sb.toString();
  }
}
