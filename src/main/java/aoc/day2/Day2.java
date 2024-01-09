package aoc.day2;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 implements Day {
  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day2.txt");
  Map<Integer,List<Map<String, Integer>>> games = new HashMap<>();

  private static final String GREEN = "green";
  private static final String RED = "red";
  private static final String BLUE = "blue";

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();
    long sum = 0;
    Map<String, Integer> possibleMap = new HashMap<>();
    possibleMap.put(RED, 12);
    possibleMap.put(GREEN, 13);
    possibleMap.put(BLUE, 14);


    for (String line : INPUT_LINES) {
      line = line.substring(line.indexOf(" "));
      int id = Integer.parseInt(line.substring(0, line.indexOf(":")).trim());
      String[] rounds = line.substring(line.indexOf(":") + 1).split(";");

      List<Map<String, Integer>> game = new ArrayList<>();
      Arrays.asList(rounds).forEach(round -> {
        String[] colours = round.split(",");

        Map<String, Integer> coloursMap = new HashMap<>();
        Arrays.asList(colours).forEach(colour -> {
          String[] colourAndNum = colour.trim().split(" ");
          coloursMap.put(colourAndNum[1], Integer.parseInt(colourAndNum[0].trim()));
        });
        game.add(coloursMap);

      });
      games.put(id, game);
    }

    for (Map.Entry<Integer, List<Map<String, Integer>>> entry : games.entrySet()) {
      int id = entry.getKey();
      List<Map<String, Integer>> game = entry.getValue();

      boolean isPossible = true;

      OUTER: for (Map<String, Integer> gameItem : game) {
        for (String key : gameItem.keySet()) {
          if (gameItem.get(key) > possibleMap.get(key)) {
            isPossible = false;
            break OUTER;
          }
        }
      }

      if (isPossible) {
        sum += id;
      }
    }

    System.out.println("Part I: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    long sum = 0;

    for (List<Map<String, Integer>> gameList : games.values()) {
      int maxGreen = 0;
      int maxRed = 0;
      int maxBlue = 0;
      for (Map<String, Integer> game : gameList) {
        if (game.get(GREEN) != null && game.get(GREEN) > maxGreen) {
          maxGreen = game.get(GREEN);
        }

        if (game.get(RED) != null && game.get(RED) > maxRed) {
          maxRed = game.get(RED);
        }

        if (game.get(BLUE) != null && game.get(BLUE) > maxBlue) {
          maxBlue = game.get(BLUE);
        }
      }

      sum += (long) maxGreen * maxBlue * maxRed;
    }

    System.out.println("Part II: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }
}
