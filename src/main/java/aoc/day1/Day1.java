package aoc.day1;

import java.util.List;

import aoc.common.AocUtilities;
import aoc.common.Day;

public class Day1 implements Day {

  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day1.txt");

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    long sum = 0;
    for (String line : INPUT_LINES) {
      sum += getNumberOfLine(line);
    }

    System.out.println("Part I: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    long sum = 0;
    int count = 0;
    for (String line : INPUT_LINES) {
      line = replaceStrings(line);
      sum += getNumberOfLine(line);
      count++;
    }

    System.out.println("Part II: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private int getNumberOfLine(String line) {
    char first = 'x';
    char second = 'x';

    for (int i = 0; i < line.length(); i++) {

      char firstChar = line.charAt(i);
      if (first == 'x' && Character.isDigit(firstChar)) {
        first = firstChar;
      }

      char lastChar = line.charAt(line.length() - 1 - i);
      if (second == 'x' && Character.isDigit(lastChar)) {
        second = lastChar;
      }

      if (Character.isDigit(first) && Character.isDigit(second)) {
        break;
      }
    }

    return Integer.parseInt(String.valueOf("" + first + second));
  }
  private String replaceStrings(String line) {
    String modifiedString = "";
    int counter = 0;
    while (counter < line.length()) {
      if (line.substring(counter).startsWith("one")) {
          modifiedString += 1;
      } else if (line.substring(counter).startsWith("two")) {
        modifiedString += 2;
      } else if (line.substring(counter).startsWith("three")) {
        modifiedString += 3;
      } else if (line.substring(counter).startsWith("four")) {
        modifiedString += 4;
      } else if (line.substring(counter).startsWith("five")) {
        modifiedString += 5;
      } else if (line.substring(counter).startsWith("six")) {
        modifiedString += 6;
      } else if (line.substring(counter).startsWith("seven")) {
        modifiedString += 7;
      } else if (line.substring(counter).startsWith("eight")) {
        modifiedString += 8;
      } else if (line.substring(counter).startsWith("nine")) {
        modifiedString += 9;
      } else {
        modifiedString += line.charAt(counter);
      }
      counter++;
    }

    return modifiedString;
  }
}