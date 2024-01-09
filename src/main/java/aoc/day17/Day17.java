package aoc.day17;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Day17 implements Day {
  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day17.txt");
  private final int[][] grid = new int[INPUT_LINES.size()][INPUT_LINES.get(0).length()];
  private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public Day17() {
    for (int i = 0; i < INPUT_LINES.size(); i++) {
      for (int j = 0; j < INPUT_LINES.get(i).length(); j++) {
        grid[i][j] = Integer.parseInt(String.valueOf(INPUT_LINES.get(i).charAt(j)));
      }
    }
  }

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    long dist = findLeastHeatLoss(grid, true);

    System.out.println("Part I: " + dist + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    long dist = findLeastHeatLoss(grid, false);

    System.out.println("Part II: " + dist + " (" + (System.currentTimeMillis() - start) + "ms) WTF after refactor. Should be 977");
  }

  public int findLeastHeatLoss(int[][] grid, boolean part1) {
    int rows = grid.length;
    int cols = grid[0].length;
    Set<Node> visited = new HashSet<>();

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(0, 1, grid[0][1], 1, 1));
    pq.offer(new Node(1, 0, grid[1][0], 1, 0));

    while (!pq.isEmpty()) {
      Node current = pq.poll();
      if (visited.contains(current)) {
        continue;
      }

      visited.add(current);

      for (int i = -1; i <= 1; i++) {
        int newDirection = (current.direction + i + 4) % 4;

        int newRow = current.row + DIRECTIONS[newDirection][1];
        int newCol = current.col + DIRECTIONS[newDirection][0];

        if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {

          int newConsecutiveMoves = part1 ?
              (current.direction == newDirection) ? current.consecutiveMoves + 1 : 0
              : current.consecutiveMoves + 1 > 9 ? 0 : current.consecutiveMoves + 1;


          if ((part1 && (newConsecutiveMoves < 3 || i != 0))
          || (!part1 && newConsecutiveMoves >= 4 && i != 0)
          || (!part1 && newConsecutiveMoves < 10 && i == 0)) {
            int newCost = current.cost + grid[newRow][newCol];

            Node newNode = new Node(newRow, newCol, newCost, newConsecutiveMoves, newDirection);

            pq.offer(newNode);

            if (newRow == rows - 1 && newCol == cols - 1) {
              return newCost;
            }
          }
        }
      }
    }

    return -1;
  }
}