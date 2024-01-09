package aoc.day23;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.awt.Point;
import java.util.List;


public class Day23 implements Day {
  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day23.txt");

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    Point startingPoint = new Point(INPUT_LINES.get(0).indexOf('.'), 0);
    Point endingPoint = new Point(INPUT_LINES.get(INPUT_LINES.size() - 1).indexOf('.'), INPUT_LINES.size() - 1);

    int length = getLength(startingPoint, endingPoint, readMap(), readMap());

    System.out.println("Part I: " + length + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private int getLength(Point start, Point end, char[][] map, char[][] originalMap) {
    if (map[start.y][start.x] == 'O') {
      return 0;
    } else if (start.x == end.x && start.y == end.y) {
      int numberOfOs = 0;
      for (int y = 0; y < map.length; y++) {
        for (int x = 0; x < map[y].length; x++) {
          if (map[y][x] == 'O') {
            numberOfOs++;
          }
        }
      }
      return numberOfOs;
    } else {
      map[start.y][start.x] = 'O';
      int maxLength = 0;

      char current = originalMap[start.y][start.x];

      if (current == '>' && start.x + 1 < map[start.y].length && map[start.y][start.x + 1] != '#') {
        int nextLength = getLength(new Point(start.x + 1, start.y), new Point(end.x, end.y), map, originalMap);

        if (nextLength > maxLength) {
          maxLength = nextLength;
        }
      } else if (current == '<' && start.x - 1 >= 0 && map[start.y][start.x - 1] != '#') {
        int nextLength = getLength(new Point(start.x - 1, start.y), new Point(end.x, end.y), map, originalMap);

        if (nextLength > maxLength) {
          maxLength = nextLength;
        }
      } else if (current == '^' && start.y - 1 >= 0 && map[start.y - 1][start.x] != '#') {
        int nextLength = getLength(new Point(start.x, start.y - 1), new Point(end.x, end.y), map, originalMap);

        if (nextLength > maxLength) {
          maxLength = nextLength;
        }
      } else if (current == 'v' && start.y + 1 < map.length && map[start.y + 1][start.x] != '#') {
        int nextLength = getLength(new Point(start.x, start.y + 1), new Point(end.x, end.y), map, originalMap);

        if (nextLength > maxLength) {
          maxLength = nextLength;
        }
      } else {

        if (start.y - 1 >= 0 && map[start.y - 1][start.x] != '#') {
          int nextLength = getLength(new Point(start.x, start.y - 1), new Point(end.x, end.y), map, originalMap);

          if (nextLength > maxLength) {
            maxLength = nextLength;
          }
        }

        if (start.x + 1 < map[start.y].length && map[start.y][start.x + 1] != '#') {
          int nextLength = getLength(new Point(start.x + 1, start.y), new Point(end.x, end.y), map, originalMap);

          if (nextLength > maxLength) {
            maxLength = nextLength;
          }
        }

        if (start.y + 1 < map.length && map[start.y + 1][start.x] != '#') {
          int nextLength = getLength(new Point(start.x, start.y + 1), new Point(end.x, end.y), map, originalMap);

          if (nextLength > maxLength) {
            maxLength = nextLength;
          }
        }

        if (start.x - 1 >= 0 && map[start.y][start.x - 1] != '#') {
          int nextLength = getLength(new Point(start.x - 1, start.y), new Point(end.x, end.y), map, originalMap);

          if (nextLength > maxLength) {
            maxLength = nextLength;
          }
        }
      }
      map[start.y][start.x] = '.';
      return maxLength;
    }
  }

  private char[][] readMap() {
    char[][] map = new char[INPUT_LINES.size()][INPUT_LINES.get(0).length()];
    for (int y = 0; y < INPUT_LINES.size(); y++) {
      String line = INPUT_LINES.get(y);
      map[y] = line.toCharArray();
    }

    return map;
  }
}