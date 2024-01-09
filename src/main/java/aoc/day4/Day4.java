package aoc.day4;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 implements Day {

  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day4.txt");
  private List<Integer> winningNumbers = new ArrayList<>();
  private List<Integer> actualNumbers = new ArrayList<>();

  @Override
  public void solvePartOne() {

    long start = System.currentTimeMillis();
    long sum = 0L;

    for (String line : INPUT_LINES) {
      String[] stringsArray = line.substring(line.indexOf(':') + 2).split("\\|");
      getCardNumbers(stringsArray);

      long score = 0L;
      for (Integer number : actualNumbers) {
        if (winningNumbers.contains(number)) {
          score = score != 0 ? score * 2 : 1;
        }
      }

      sum += score;
    }

    System.out.println("Part I: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();
    Map<Integer, Long> copies = new HashMap<>();
    for (int i = 1; i <= INPUT_LINES.size(); i++) {
      copies.put(i, 1L);
    }

    for (int i = 0; i < INPUT_LINES.size(); i++) {
      String[] stringsArray = INPUT_LINES.get(i).substring(INPUT_LINES.get(i).indexOf(':') + 2).split("\\|");
      getCardNumbers(stringsArray);

      int counter = i + 1;
      for (Integer number : actualNumbers) {
        if (winningNumbers.contains(number) && counter <= copies.size() - 1) {
          long copiesOfNext = copies.get(counter+1) + copies.get(i + 1);
          copies.put(counter+1,copiesOfNext);
          counter++;
        }
      }
    }

    long sum = copies.values().stream()
        .mapToLong(Long::longValue)
        .sum();

    System.out.println("Part II: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private void getCardNumbers(String[] stringsArray) {
    winningNumbers = new ArrayList<>();
    actualNumbers = new ArrayList<>();

    String[] winningStrings = stringsArray[0].trim().split(" ");
    String[] actualStrings = stringsArray[1].trim().split(" ");

    for (String numberStr : winningStrings) {
      if (!"".equals(numberStr)) {
        winningNumbers.add(Integer.parseInt(numberStr));
      }
    }

    for (String numberStr : actualStrings) {
      if (!"".equals(numberStr)) {
        actualNumbers.add(Integer.parseInt(numberStr));
      }
    }
  }
}
