package aoc.day8;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 implements Day {
  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day8.txt");
  String directions;
  Map<String, String[]> nodes;

  public Day8() {
    directions = INPUT_LINES.get(0).trim();
    nodes = new HashMap<>();

    for (int i = 2; i < INPUT_LINES.size(); i++) {
      String[] nodeWithDirs = INPUT_LINES.get(i).split("=");
      String[] dirs = nodeWithDirs[1].trim().substring(1, nodeWithDirs[1].length() - 2).split(", ");
      nodes.put(nodeWithDirs[0].trim(), dirs);
    }
  }

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    int counter = 0;
    String currentNode = "AAA";

    while (!"ZZZ".equals(currentNode)) {
      char dir = directions.charAt(counter++ % directions.length());

      if (dir == 'L') {
        currentNode = nodes.get(currentNode)[0];
      } else {
        currentNode = nodes.get(currentNode)[1];
      }
    }

    System.out.println("Part I: " + counter + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    Map<String, Integer> startingPointsWithRequiredSteps = new HashMap<>();
    nodes.keySet().forEach(key -> {
      if (key.endsWith("A")) {
        startingPointsWithRequiredSteps.put(key, 0);
      }
    });

    startingPointsWithRequiredSteps.keySet().forEach(key -> {
      String currentNode = key;
      int counter = 0;

      while (!currentNode.endsWith("Z")) {
        char dir = directions.charAt(counter++ % directions.length());

        if (dir == 'L') {
          currentNode = nodes.get(currentNode)[0];
        } else {
          currentNode = nodes.get(currentNode)[1];
        }
      }

      startingPointsWithRequiredSteps.put(key, counter);
    });

    long[] steps = new long[startingPointsWithRequiredSteps.size()];
    List<Integer> stepsValues = new ArrayList<>(startingPointsWithRequiredSteps.values());
    for (int i = 0; i < stepsValues.size(); i++) {
      steps[i] = stepsValues.get(i);
    }
    long stepsRequired = lcm(steps);

    System.out.println("Part II: " + stepsRequired + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private static long gcd(long a, long b) {
    while (b > 0)
    {
      long temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  private static long lcm(long a, long b) {
    return a * (b / gcd(a, b));
  }

  private static long lcm(long[] input) {
    long result = input[0];
    for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
    return result;
  }
}
