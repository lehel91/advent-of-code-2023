package aoc.day16;

import aoc.common.AocUtilities;
import aoc.common.Day;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Day16 implements Day {
  private static final List<String> INPUT_LINES = AocUtilities.processInputFile("day16.txt");
  private final Tile[][] tiles;

  public Day16() {
    tiles = new Tile[INPUT_LINES.size()][INPUT_LINES.get(0).length()];
    for (int i = 0; i < INPUT_LINES.size(); i++) {
      for (int j = 0; j < INPUT_LINES.get(i).length(); j++) {
        tiles[i][j] = new Tile(INPUT_LINES.get(i).charAt(j));
      }
    }
  }

  @Override
  public void solvePartOne() {
    long start = System.currentTimeMillis();

    char dir = 'E';
    if (tiles[0][0].getType() == '\\' || tiles[0][0].getType() == '|') {
      dir = 'S';
    } else if (tiles[0][0].getType() == '/') {
      dir = 'N';
    }

    System.out.println("Part I: " + getEnergizedCount(dir, 0) + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  @Override
  public void solvePartTwo() {
    long start = System.currentTimeMillis();

    List<Long> energizedList = new ArrayList<>();
    char[] directions = new char[]{'N','W','S','E'};
    for (char dir : directions) {

      for (int i = 0; i < 110; i++) {
        energizedList.add(getEnergizedCount(dir, i));
      }
    }
    Optional<Long> maxOptional = energizedList.stream().max(Long::compareTo);
    long max = maxOptional.orElse(0L);

    System.out.println("Part II: " + max + " (" + (System.currentTimeMillis() - start) + "ms)");
  }

  private long getEnergizedCount(char dir, int i) {
    List<Beam> beams = new ArrayList<>();
    Beam startBeam = new Beam(dir);

    int x = 0;
    int y = 0;
    switch (dir) {
      case 'N' :
        x = i;
        y = 110;
        break;
      case 'S' :
        x = i;
        y = -1;
        break;
      case 'W' :
        x = 110;
        y = i;
        break;
      case 'E' :
        x = -1;
        y = i;
        break;
      default:
        break;
    }

    startBeam.setPosition(new Point(x, y));
    startBeam.move();

    beams.add(startBeam);
    tiles[startBeam.getPosition().y][startBeam.getPosition().x].setEnergized(true);

    List<Beam> beamsToRemove = new ArrayList<>();

    int changedSince = 0;
    while (!beams.isEmpty()) {
      Iterator<Beam> iterator = beams.iterator();

      List<Beam> newBeams = new ArrayList<>();
      while (iterator.hasNext()) {
        Beam beam = iterator.next();
        beam.move();

        try {
          Tile current = tiles[beam.getPosition().y][beam.getPosition().x];

          if (!current.isEnergized()) {
            changedSince = 0;
            current.setEnergized(true);
          }

          char beamDirection = beam.getDirection();
          char tileType = current.getType();
          if (tileType == '-') {
            switch (beamDirection) {
              case 'N' :
              case 'S' :
                beam.setDirection('E');
                if (!current.isSplitted()) {
                  newBeams.add(new Beam(new Point(beam.getPosition().x, beam.getPosition().y), 'W'));
                }
                current.setSplitted(true);
                break;
              case 'E' :
              case 'W' :
              default :
                break;
            }
          } else if (tileType == '|') {
            switch (beamDirection) {
              case 'W' :
              case 'E' :
                beam.setDirection('N');
                if (!current.isSplitted()) {
                  newBeams.add(new Beam(new Point(beam.getPosition().x, beam.getPosition().y), 'S'));
                }
                current.setSplitted(true);
                break;
              case 'N' :
              case 'S' :
              default :
                break;
            }
          } else if (tileType == '/') {
            switch (beamDirection) {
              case 'W' :
                beam.setDirection('S');
                break;
              case 'E' :
                beam.setDirection('N');
                break;
              case 'S' :
                beam.setDirection('W');
                break;
              case 'N' :
                beam.setDirection('E');
                break;
              default :
                break;
            }
          } else if (tileType == '\\') {
            switch (beamDirection) {
              case 'W' :
                beam.setDirection('N');
                break;
              case 'E' :
                beam.setDirection('S');
                break;
              case 'S' :
                beam.setDirection('E');
                break;
              case 'N' :
                beam.setDirection('W');
                break;
              default :
                break;
            }
          }
        } catch (ArrayIndexOutOfBoundsException e) {
          beamsToRemove.add(beam);
        }
      }

      beams.removeAll(beamsToRemove);
      beamsToRemove.clear();
      beams.addAll(newBeams);
      newBeams.clear();

      if (changedSince > tiles.length) {
        break;
      }

      changedSince++;
    }

    long energized = 0L;
    for (int j = 0; j < tiles.length; j++) {
      for (int k = 0; k < tiles[j].length; k++) {
        if (tiles[j][k].isEnergized()) {
          energized++;
        }
        tiles[j][k].setEnergized(false);
        tiles[j][k].setSplitted(false);
      }
    }

    return energized;
  }
}
