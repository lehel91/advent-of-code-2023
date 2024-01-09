package aoc.day19;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19 implements Day {

  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day19.txt");
  private final Map<String, Workflow> workflows;

  public Day19() {
    workflows = new HashMap<>();
    for (String line : INPUT_LINES) {
      if (line.isBlank()) {
        break;
      } else {
        String name = line.substring(0, line.indexOf('{'));
        String rules = line.substring(line.indexOf('{') + 1, line.length() - 1);
        Workflow workflow = new Workflow(rules);

        workflows.put(name, workflow);
      }
    }
  }

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    long sum = 0L;
    for (int i = workflows.size() + 1; i < INPUT_LINES.size(); i++) {
      String line = INPUT_LINES.get(i);
      String workflow = "in";
      MachinePart machinePart = new MachinePart(line.substring(1, line.length() - 1));

      while (!workflow.equals("A") && !workflow.equals("R")) {
        workflow = workflows.get(workflow).processMachinePart(machinePart);
      }

      if (workflow.equals("A")) {
        sum += machinePart.getRatingsSum();
      }
    }

    System.out.println("Part I: " + sum + " (" + (System.currentTimeMillis() - start) + "ms)");
  }
}
