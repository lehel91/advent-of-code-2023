package aoc.day13;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day13 implements Day {
  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day13.txt");
  private final List<char[][]> maps = new ArrayList<>();

  public Day13() {
    List<String> mapLines = new ArrayList<>();
    for (int i = 0; i <= INPUT_LINES.size(); i++) {
      if (i == INPUT_LINES.size() || INPUT_LINES.get(i).trim().isEmpty()) {
        char[][] map = new char[mapLines.size()][mapLines.get(0).length()];
        for (int j = 0; j < mapLines.size(); j++) {
          map[j] = mapLines.get(j).toCharArray();
        }
        maps.add(map);
        mapLines = new ArrayList<>();
      } else {
        mapLines.add(INPUT_LINES.get(i));
      }
    }
  }

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    long sum = 0L;
    for (char[][] map : maps) {
      sum += getReflectionLine(map, false);
    }

    System.out.println("Part I: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    long sum = 0L;
    for (char[][] map : maps) {
      sum += getReflectionLine(map, true);
    }

    System.out.println("Part II: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private int getReflectionLine(char[][] map, boolean withSmudge) {
    // Check horizontal
    int line = 1;

    while (line < map.length) {
      boolean match = true;
      int smudge = 0;
      for (int i = 1; i <= line; i++) {
        if (line - i < 0 || line + i > map.length) {
          break;
        }

        for (int j = 0; j < map[0].length; j++) {
          if (map[line - i][j] != map[line + i - 1][j]) {
            match = false;
            smudge++;

            if (!withSmudge || smudge > 1) {
              break;
            }
          }
        }

        if (smudge > 1 && withSmudge || !withSmudge && !match) {
          break;
        }
      }

      if (!withSmudge && match || (smudge == 1 && withSmudge)) {
        return line * 100;
      }

      line++;
    }

    // Check vertical
    line = 1;

    while (line < map[0].length) {
      boolean match = true;
      int smudge = 0;
      for (int i = 1; i <= line; i++) {
        if (line - i < 0 || line + i > map[0].length) {
          break;
        }

        for (int j = 0; j < map.length; j++) {
          if (map[j][line - i] != map[j][line + i - 1]) {
            match = false;
            smudge++;

            if (!withSmudge || smudge > 1) {
              break;
            }
          }
        }

        if (smudge > 1 && withSmudge || !match && !withSmudge) {
          break;
        }
      }

      if (!withSmudge && match || (smudge == 1 && withSmudge)) {
        return line;
      }
      line++;
    }

    return -1;
  }
}
