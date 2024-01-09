package aoc.day19;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Workflow {

  private static final String REGEX_RULE = "([xmas])([<>])(\\d+):([a-zA-Z]+)";
  private static final Pattern PATTERN = Pattern.compile(REGEX_RULE);

  private final String[] rulesArray;

  public Workflow(String rules) {
    this.rulesArray = rules.split(",");
  }

  public String processMachinePart(MachinePart part) {
    for (String rule : rulesArray) {
      Matcher matcher = PATTERN.matcher(rule);

      if (matcher.matches()) {
        String category = matcher.group(1);
        String relationOperator = matcher.group(2);
        String ruleNumber = matcher.group(3);
        String destination = matcher.group(4);

        switch (category) {
          case "x": {
            if ("<".equals(relationOperator) && part.getX() < Integer.parseInt(ruleNumber) ||
                ">".equals(relationOperator) && part.getX() > Integer.parseInt(ruleNumber)) {
              return destination;
            }
            break;
          }
          case "m": {
            if ("<".equals(relationOperator) && part.getM() < Integer.parseInt(ruleNumber) ||
                ">".equals(relationOperator) && part.getM() > Integer.parseInt(ruleNumber)) {
              return destination;
            }
            break;
          }
          case "a": {
            if ("<".equals(relationOperator) && part.getA() < Integer.parseInt(ruleNumber) ||
                ">".equals(relationOperator) && part.getA() > Integer.parseInt(ruleNumber)) {
              return destination;
            }
            break;
          }
          case "s": {
            if ("<".equals(relationOperator) && part.getS() < Integer.parseInt(ruleNumber) ||
                ">".equals(relationOperator) && part.getS() > Integer.parseInt(ruleNumber)) {
              return destination;
            }
            break;
          }
          default:
            break;
        }
      } else {
        return rule;
      }
    }
    return null;
  }
}
