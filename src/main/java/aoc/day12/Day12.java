package aoc.day12;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 implements Day {

  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day12.txt");
  private List<char[]> combinations = new ArrayList<>();

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();
    long sum = 0L;

    for (String line : INPUT_LINES) {
      sum += countPossibleArrangements(line);
    }

    System.out.println("Part I: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private void generateCombinations(char[] currentCombination, int currentIndex) {
    if (currentIndex == currentCombination.length) {
      combinations.add(Arrays.copyOf(currentCombination, currentCombination.length));
      return;
    }

    for (int i = 0; i <= 1; i++) {
      currentCombination[currentIndex] = i == 0 ? '.' : '#';
      generateCombinations(currentCombination, currentIndex + 1);
    }
  }

  private String createRegex(int[] constraints) {
    StringBuilder regex = new StringBuilder("^\\.*");

    for (int i = 0; i < constraints.length; i++) {
      regex.append("#".repeat(Math.max(0, constraints[i])));
      if (i != constraints.length - 1) {
        regex.append("(\\.+)+");
      } else {
        regex.append("\\.*$");
      }
    }

    return regex.toString();
  }

  private static int[] convertStringArrayToIntArray(String[] stringArray) {
    int[] intArray = new int[stringArray.length];

    for (int i = 0; i < stringArray.length; i++) {
      intArray[i] = Integer.parseInt(stringArray[i]);
    }

    return intArray;
  }

  private long countPossibleArrangements(String line) {
    long sum = 0L;

    combinations = new ArrayList<>();
    String[] parts = line.split(" ");

    List<Integer> questionMarkIndexes = new ArrayList<>();

    String springs = parts[0];
    for (int i = 0; i < springs.length(); i++) {
      if (springs.charAt(i) == '?') {
        questionMarkIndexes.add(i);
      }
    }
    char[] combination = new char[questionMarkIndexes.size()];
    generateCombinations(combination, 0);

    String regex = createRegex(convertStringArrayToIntArray(parts[1].split(",")));
    for (char[] comb : combinations) {
      for (char c : comb) {
        springs = springs.replaceFirst("\\?", String.valueOf(c));
      }

      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(springs);

      if (matcher.find()) {
        sum++;
      }

      springs = parts[0];
    }

    return sum;
  }
}
