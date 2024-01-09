package aoc.day9;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 implements Day {

  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day9.txt");
  private List<List<Long>> histories;

  public Day9() {
    parseHistories();
  }

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    long sum = 0L;

    for (List<Long> history : histories) {
      List<List<Long>> sequences = getSequences(history);

      for (int i = sequences.size() - 2; i >= 0; i--) {
        sequences.get(i).add(
            sequences.get(i).get(sequences.get(i).size() - 1) +
            sequences.get(i + 1).get(sequences.get(i + 1).size() - 1));
      }

      sum += sequences.get(0).get(sequences.get(0).size() - 1);
    }

    System.out.println("Part I: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    parseHistories();
    long sum = 0L;

    for (List<Long> history : histories) {
      List<List<Long>> sequences = getSequences(history);

      for (List<Long> sequence : sequences) {
        Collections.reverse(sequence);
      }

      for (int i = sequences.size() - 2; i >= 0; i--) {
        sequences.get(i).add(
            sequences.get(i).get(sequences.get(i).size() - 1) -
            sequences.get(i + 1).get(sequences.get(i + 1).size() - 1));
      }

      sum += sequences.get(0).get(sequences.get(0).size() - 1);
    }

    System.out.println("Part II: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private List<List<Long>> getSequences(List<Long> history) {
    List<List<Long>> sequences = new ArrayList<>();
    sequences.add(history);

    boolean allZeros = false;
    while(!allZeros) {
      List<Long> prevSequence = sequences.get(sequences.size() - 1);
      List<Long> newSequence = new ArrayList<>();

      for (int i = 0; i <= prevSequence.size() - 2; i++) {
        newSequence.add(prevSequence.get(i + 1) - prevSequence.get(i));
      }

      sequences.add(newSequence);
      allZeros = newSequence.stream().allMatch(value -> value == 0);
    }

    return sequences;
  }

  private void parseHistories() {
    this.histories = new ArrayList<>();
    INPUT_LINES.forEach(line ->
        histories.add(Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).boxed().collect(
            Collectors.toList())));
  }
}
