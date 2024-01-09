package aoc.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AocUtilities {
	
	public static List<String> processInputFile(String fileName) {
		List<String> lines = new ArrayList<>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try (BufferedInputStream bis = new BufferedInputStream(classLoader.getResourceAsStream(fileName));
				BufferedReader reader = new BufferedReader(
				        new InputStreamReader(bis, StandardCharsets.UTF_8));) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		       lines.add(line);
		    }
		} catch (IOException e) {
			System.out.println("--> error during processing the file: " + e.getMessage());
		}
		return lines;
	}
	
	public static void printInputLines(List<String> lines) {
		if (Objects.isNull(lines) || lines.isEmpty()) {
			System.out.println("--> There are no input lines");
		} else {
			lines.forEach(System.out::println);
		}
	}
	
	public static void drawTheChristmasTree(String day) {
		System.out.println(AocConstants.ANSI_YELLOW + "\n    *   \n" + AocConstants.ANSI_GREEN + 
				"   ***  \n  *" + AocConstants.ANSI_PURPLE +"*" +AocConstants.ANSI_GREEN + "***  \n" + 
				AocConstants.ANSI_YELLOW + " *" + AocConstants.ANSI_GREEN + "****" + AocConstants.ANSI_WHITE + "*" + 
				AocConstants.ANSI_GREEN + "* \n***" + AocConstants.ANSI_BLUE + "*" + AocConstants.ANSI_GREEN + "***" + 
				AocConstants.ANSI_RED + "*" + AocConstants.ANSI_GREEN + "*\n   " + 
				AocConstants.ANSI_RED + day + AocConstants.ANSI_RESET + "  \n");
	}

	public static long getManhattanDistance(long aX, long aY, long bX, long bY) {
		return (long) Math.abs(aX - bX) + Math.abs(aY - bY);
	}
}
