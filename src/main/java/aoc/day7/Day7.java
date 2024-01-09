package aoc.day7;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day7 implements Day {

  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day7.txt");

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    System.out.println("Part I: " + getTotalWinnings(false) + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    System.out.println("Part II: " + getTotalWinnings(true) + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private long getTotalWinnings(boolean joker) {
    long sum = 0L;
    List<Hand> hands = new ArrayList<>();

    for (String line : INPUT_LINES) {
      hands.add(new Hand(line, joker));
    }
    Collections.sort(hands);

    for (int i = 0; i < hands.size(); i++) {
      sum += (long) (i + 1) * hands.get(i).getBid();
    }
    return sum;
  }
}
