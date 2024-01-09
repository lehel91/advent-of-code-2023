package aoc.common;

import aoc.day1.Day1;
import aoc.day10.Day10;
import aoc.day11.Day11;
import aoc.day12.Day12;
import aoc.day13.Day13;
import aoc.day14.Day14;
import aoc.day15.Day15;
import aoc.day16.Day16;
import aoc.day17.Day17;
import aoc.day18.Day18;
import aoc.day19.Day19;
import aoc.day2.Day2;
import aoc.day20.Day20;
import aoc.day21.Day21;
import aoc.day22.Day22;
import aoc.day23.Day23;
import aoc.day24.Day24;
import aoc.day25.Day25;
import aoc.day3.Day3;
import aoc.day4.Day4;
import aoc.day5.Day5;
import aoc.day6.Day6;
import aoc.day7.Day7;
import aoc.day8.Day8;
import aoc.day9.Day9;

public class Main {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		AocUtilities.drawTheChristmasTree("D01");
		Day day = new Day1();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D02");
		day = new Day2();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D03");
		day = new Day3();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D04");
		day = new Day4();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D05");
		day = new Day5();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D06");
		day = new Day6();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D07");
		day = new Day7();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D08");
		day = new Day8();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D09");
		day = new Day9();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D10");
		day = new Day10();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D11");
		day = new Day11();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D12");
		day = new Day12();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D13");
		day = new Day13();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D14");
		day = new Day14();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D15");
		day = new Day15();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D16");
		day = new Day16();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D17");
		day = new Day17();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D18");
		day = new Day18();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D19");
		day = new Day19();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D20");
		day = new Day20();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D21");
		day = new Day21();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D22");
		day = new Day22();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D23");
		day = new Day23();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D24");
		day = new Day24();
		day.solvePartOne();
		day.solvePartTwo();

		AocUtilities.drawTheChristmasTree("D25");
		day = new Day25();
		day.solvePartOne();
		day.solvePartTwo();

		System.out.println(AocConstants.ANSI_GREEN + "\nTOTAL TIME ELAPSED: " + (System.currentTimeMillis() - start) + "ms");
		System.out.println(AocConstants.ANSI_RED + "*** MERRY CHRISTMAS!!! ***" + AocConstants.ANSI_RESET);
	}
}