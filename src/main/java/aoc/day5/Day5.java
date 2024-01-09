package aoc.day5;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 implements Day {
  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day5.txt");
  private final List<Long> seeds = new ArrayList<>();
  private final List<List<long[]>> mappings = new ArrayList<>();

  public Day5() {
    List<long[]> mapping = new ArrayList<>();

    for (String line : INPUT_LINES) {
      if (line.trim().isEmpty()) {
        if (!mapping.isEmpty()) {
          mappings.add(mapping);
          mapping = new ArrayList<>();
        }
        continue;
      }

      if (line.startsWith("seeds: ")) {
        seeds.addAll(Arrays.stream(line.substring(line.indexOf(":") + 1).trim().split(" ")).map(Long::parseLong).collect(
            Collectors.toList()));
      } else if (Character.isDigit(line.charAt(0))) {
        mapping.add(Arrays.stream(line.trim().split(" ")).mapToLong(Long::parseLong).toArray());
      }
    }

    mappings.add(mapping);
  }

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    long smallestDistance = Long.MAX_VALUE;
    for (Long seed : seeds) {
      long sourceNumber = seed;

      for (List<long[]> mapping : mappings) {
        for (long[] mappingLine : mapping) {
          if (sourceNumber >= mappingLine[1] && sourceNumber <= mappingLine[1] + mappingLine[2]) {
            sourceNumber = mappingLine[0] + sourceNumber - mappingLine[1];
            break;
          }
        }
      }

      if (sourceNumber < smallestDistance) {
        smallestDistance = sourceNumber;
      }
    }

    System.out.println("Part I: " + smallestDistance + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    long counter = 0L;
    OUTER: while (true) {
      long sourceNumber = counter;
      for (int i = mappings.size() - 1; i >= 0; i--) {
        for (long[] mappingLine : mappings.get(i)) {
          if (sourceNumber >= mappingLine[0] && sourceNumber <= mappingLine[0] + mappingLine[2]) {
            sourceNumber = mappingLine[1] + sourceNumber - mappingLine[0];
            break;
          }
        }
      }

      for (int i = 0; i < seeds.size(); i+=2) {
        if (sourceNumber >= seeds.get(i) && sourceNumber <= seeds.get(i) + seeds.get(i + 1)) {
          break OUTER;
        }
      }

      counter++;
    }

    System.out.println("Part II: " + --counter + " (" + (System.currentTimeMillis() - start) + "ms)");
  }
}
