package aoc.day15;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

public class Day15 implements Day {
  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day15.txt");

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    String[] steps = INPUT_LINES.get(0).split(",");
    long sum = Arrays.stream(steps).flatMapToLong(step -> LongStream.of(getHash(step))).sum();

    System.out.println("Part I: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    Map<Integer, Map<String, Integer>> boxes = new HashMap<>();
    String[] steps = INPUT_LINES.get(0).split(",");
    for (String step : steps) {
      if (step.contains("=")) {
        String[] parts = step.split("=");
        int index = getHash(parts[0]);
        if (boxes.containsKey(index)) {
          boxes.get(index).put(parts[0], Integer.parseInt(parts[1]));
        } else {
          boxes.put(index, new LinkedHashMap<>());
          boxes.get(index).put(parts[0], Integer.parseInt(parts[1]));
        }
      } else if (step.contains("-")) {
        String seq = step.substring(0, step.length() - 1);
        int index = getHash(seq);
        if (boxes.containsKey(index)) {
          boxes.get(index).remove(seq);
        }
      }
    }

    long sum = 0L;
    for (Map.Entry<Integer, Map<String, Integer>> entry : boxes.entrySet()) {
      int boxNumber = entry.getKey();
      Map<String, Integer> value = entry.getValue();

      int counter = 0;
      for (Map.Entry<String, Integer> entryTwo : value.entrySet()) {
        sum += (boxNumber + 1) * ((long) ++counter * entryTwo.getValue());
      }
    }

    System.out.println("Part II: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private int getHash(String input) {
    int current = 0;

    for (char c : input.toCharArray()) {
      current += c;
      current *= 17;
      current %= 256;
    }

    return current;
  }
}
