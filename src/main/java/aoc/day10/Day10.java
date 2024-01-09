package aoc.day10;

import aoc.common.AocConstants;
import aoc.common.AocUtilities;
import aoc.common.Day;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Day10 implements Day {
  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day10.txt");
  private final Map<Point, Node> nodes;
  private Point startingPoint;
  private final Set<Character> possibleUp = Set.of('F', '|', '7');
  private final Set<Character> possibleDown = Set.of('L', '|', 'J');
  private final Set<Character> possibleLeft = Set.of('L', 'F', '-');
  private final Set<Character> possibleRight = Set.of('J', '7', '-');

  public Day10() {
    nodes = new HashMap<>();
    for (int i = 0; i < INPUT_LINES.size(); i++) {
      for (int j = 0; j < INPUT_LINES.get(i).length(); j++) {
        if (INPUT_LINES.get(i).charAt(j) == 'S') {
          startingPoint = new Point(j, i);
          Node startingNode = new Node('S');
          startingNode.setPartOfTheLoop(true);
          nodes.put(startingPoint, startingNode);
          startingNode.setDirection('|');
        } else {
          nodes.put(new Point(j, i), new Node(INPUT_LINES.get(i).charAt(j)));
        }
      }
    }
  }

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    Point firstCurrent = null;
    Point secondCurrent = null;
    Set<Point> possibleNeighbours = getPossibleNeighbours(startingPoint);
    for (Point neighbour : possibleNeighbours) {
      if (Objects.isNull(firstCurrent)) {
        firstCurrent = neighbour;
      } else {
        secondCurrent = neighbour;
      }
    }

    long moves = 1;
    Point firstFrom = startingPoint;
    Point secondFrom = startingPoint;
    while (Objects.nonNull(firstCurrent) && !firstCurrent.equals(secondCurrent)) {
      Point firstTo = move(firstFrom, firstCurrent, nodes.get(firstCurrent));
      firstFrom = firstCurrent;
      firstCurrent = firstTo;

      nodes.get(firstFrom).setPartOfTheLoop(true);
      nodes.get(firstCurrent).setPartOfTheLoop(true);

      Point secondTo = move(secondFrom, secondCurrent, nodes.get(secondCurrent));
      secondFrom = secondCurrent;
      secondCurrent = secondTo;

      nodes.get(secondFrom).setPartOfTheLoop(true);
      nodes.get(secondCurrent).setPartOfTheLoop(true);

      moves++;
    }

    System.out.println("Part I: " + moves + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    for (int i = 0; i < INPUT_LINES.size(); i++) {
      boolean isInside = false;
      char prevBorder = '0';
      for (int j = 0; j < INPUT_LINES.get(i).length(); j++) {
        if (nodes.get(new Point(j,i)).isPartOfTheLoop() && nodes.get(new Point(j,i)).getDirection() == 'L' ||
            nodes.get(new Point(j,i)).getDirection() == 'F') {
          prevBorder = nodes.get(new Point(j,i)).getDirection();
        }
        try {
          if (nodes.get(new Point(j,i)).isPartOfTheLoop() && (nodes.get(new Point(j,i)).getDirection() == '|' ||
                                                              nodes.get(new Point(j,i)).getDirection() == 'J' && prevBorder == 'F' ||
                                                              nodes.get(new Point(j,i)).getDirection() == '7' && prevBorder == 'L')) {
            isInside = !isInside;
          }
        } catch (NullPointerException e) {
          // Ignore
        }

        if (isInside && !nodes.get(new Point(j,i)).isPartOfTheLoop()) {
          nodes.get(new Point(j,i)).setInsideTheLoop(true);
        }
      }
    }

    System.out.println("Part II: " + nodes.values().stream().filter(Node::isInsideTheLoop).count() + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private Set<Point> getNeighbours(Point point, int maxX, int maxY) {
    Set<Point> neighbours = new HashSet<>();

    if (point.x < maxX) {
      neighbours.add(new Point(point.x + 1, point.y));
    }

    if (point.y < maxY) {
      neighbours.add(new Point(point.x, point.y + 1));
    }

    if (point.x != 0) {
      neighbours.add(new Point(point.x - 1, point.y));
    }

    if (point.y != 0) {
      neighbours.add(new Point(point.x, point.y - 1));
    }

    return neighbours;
  }

  private Point move(Point from, Point current, Node node) {
    char direction = node.getDirection();
    Point end = null;
    switch (direction) {
      case 'F':
        end = from.y > current.y ? new Point(current.x + 1, current.y) : new Point(current.x, current.y + 1);
        break;
      case 'J':
        end = from.x < current.x ? new Point(current.x, current.y - 1) : new Point(current.x - 1, current.y);
        break;
      case 'L':
        end = from.y < current.y ? new Point(current.x + 1, current.y) : new Point(current.x, current.y - 1);
        break;
      case '7':
        end = from.y > current.y ? new Point(current.x - 1, current.y) : new Point(current.x, current.y + 1);
        break;
      case '-':
        end = from.x > current.x ? new Point(current.x - 1, current.y) : new Point(current.x + 1, current.y);
        break;
      case '|':
        end = from.y > current.y ? new Point(current.x, current.y - 1) : new Point(current.x, current.y + 1);
        break;
      default:
        break;
    }

    return end;
  }

  private Set<Point> getPossibleNeighbours(Point point) {
    Set<Point> neighbours = getNeighbours(point, INPUT_LINES.get(0).length() - 1, INPUT_LINES.size() - 1);
    Set<Point> possibleNeighbours = new HashSet<>();

    for (Point neighbour : neighbours) {
      boolean isPossibleDir = neighbour.x < startingPoint.x &&
                              possibleLeft.contains(nodes.get(neighbour).getDirection()) ||
                              neighbour.x > startingPoint.x &&
                              possibleRight.contains(nodes.get(neighbour).getDirection()) ||
                              neighbour.y < startingPoint.y &&
                              possibleUp.contains(nodes.get(neighbour).getDirection()) ||
                              neighbour.y > startingPoint.y &&
                              possibleDown.contains(nodes.get(neighbour).getDirection());

      if (isPossibleDir) {
        possibleNeighbours.add(neighbour);
      }

      if (possibleNeighbours.size() == 2) {
        break;
      }
    }

    return possibleNeighbours;
  }
}
