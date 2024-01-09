package aoc.day7;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
public class Hand implements Comparable<Hand> {
  private int bid;
  private int strength;
  private List<Integer> cards;

  public Hand(String line, boolean joker) {
    setCards(line, joker);
    setBid(line);
    setStrength(joker);
  }

  private void setCards(String line, boolean joker) {
    cards = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      switch (line.charAt(i)) {
        case 'A':
          cards.add(14);
          break;
        case 'K':
          cards.add(13);
          break;
        case 'Q':
          cards.add(12);
          break;
        case 'J':
          if (joker) {
            cards.add(1);
          } else {
            cards.add(11);
          }
          break;
        case 'T':
          cards.add(10);
          break;
        default:
          cards.add(Integer.parseInt(String.valueOf(line.charAt(i))));
          break;
      }
    }
  }

  private void setBid(String line) {
    bid = Integer.parseInt(line.substring(6));
  }

  private void setStrength(boolean joker) {
    int[] occurrences = new int[14];
    for (Integer card : cards) {
      occurrences[card - 1]++;
    }

    List<Integer> filteredList;

    if (joker) {
      int numberOfJokers = occurrences[0];

      filteredList = Arrays.stream(Arrays.copyOfRange(occurrences, 1, 14))
          .filter(num -> num > 0)
          .boxed()
          .sorted((a, b) -> b - a)
          .collect(Collectors.toList());
      if (filteredList.isEmpty()) {
        filteredList.add(0);
      }
      filteredList.set(0, filteredList.get(0) + numberOfJokers);
    } else {
      filteredList = Arrays.stream(occurrences)
          .filter(num -> num > 0)
          .boxed()
          .sorted((a, b) -> b - a)
          .collect(Collectors.toList());
    }


    if (filteredList.get(0) == 5) {
      strength = 7;
    } else if (filteredList.get(0) == 4) {
      strength = 6;
    } else if (filteredList.get(0) == 3 && filteredList.get(1) == 2) {
      strength = 5;
    } else if (filteredList.get(0) == 3 && filteredList.get(1) == 1) {
      strength = 4;
    } else if (filteredList.get(0) == 2 && filteredList.get(1) == 2) {
      strength = 3;
    } else if (filteredList.get(0) == 2) {
      strength = 2;
    } else {
      strength = 1;
    }
  }

  @Override
  public int compareTo(Hand o) {
    int value = this.strength - o.getStrength();

    if (value == 0) {
      for (int i = 0; i < this.cards.size(); i++) {
        value = this.cards.get(i) - o.getCards().get(i);

        if (value != 0) {
          break;
        }
      }
    }

    return value;
  }
}
