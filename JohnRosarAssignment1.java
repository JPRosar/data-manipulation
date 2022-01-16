/* John Rosar
 * COSC 2436
 * Assignment 1
 * 7-15-2021
 */

import java.io.*;
import java.util.*;

public class JohnRosarAssignment1 {

	/* I struggled with this simple part the most for some reason.
	 * isLetterOrDigit to delimit all non letters and digits
	 * could not get regex delimiting to function
	 * this worked beautifully and hope it is acceptable */

	private static boolean myDelimit(char delimit) {
		return !Character.isLetterOrDigit(delimit);
	}

	/* Recursive method to capitalize */

	public static String JohnRosarRecursion(String line) {

		/* Base Case */

		if (line.length() == 0)
			return line;

		/* As requested capital first character then substring */

		return Character.toUpperCase(line.charAt(0)) + JohnRosarRecursion(line.substring(1));
	}

	/* Create List method */

	public static List<String> JohnRosarCreateList() {
		List<String> JohnRosarList = new ArrayList<>();

		/* Reading our text file */

		try (BufferedReader scan = new BufferedReader(new FileReader("Assingment1Data.txt"))) {

			/* Building the strings */

			StringBuilder stringB = new StringBuilder();
			int fullWord;
			char element;

			/* Reading each element */

			while ((fullWord = scan.read()) != -1) {
				element = (char) fullWord;

				/* verifying elements are delimited */

				if (myDelimit(element)) {

					/* checking if string is empty */

					if (stringB.length() == 0)
						continue;

					/* adding to my list */

					JohnRosarList.add(stringB.toString());
					stringB = new StringBuilder();
				} else {

					/* not delimited add to string */

					stringB.append(element);
				}
			}
		} 

		/* catch the errors and throw message */

		catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		/* printing the inital list one element per line */

		System.out.println("LIST");
		System.out.println();
		for (String word : JohnRosarList)
			System.out.println(word);

		return JohnRosarList;
	}

	/* processing the list to capitalized */

	public static void JohnRosarProcessList(List<String> JohnRosarList) {

		/* iterating through the list and capitalizing using our recursize method */

		for (int i = 0; i < JohnRosarList.size(); i++) {
			JohnRosarList.set(i, JohnRosarRecursion(JohnRosarList.get(i)));
		}

		/* printing the capitalized list one element per line */
		
		System.out.println();
		System.out.println("-----------------------");
		System.out.println();
		System.out.println("CAPITALIZED LIST");
		System.out.println();
		for (String word : JohnRosarList)
			System.out.println(word);
	}

	/* creating my set */

	public static Set<String> JohnRosarCreateSet(List<String> JohnRosarList) {
		Set<String> JohnRosarSet = new HashSet<>();

		/* adding elements from list to the set */

		for (String word : JohnRosarList)
			JohnRosarSet.add(word);

		/* printing the set */
		
		System.out.println();
		System.out.println("-----------------------");
		System.out.println();
		System.out.println("SET");
		System.out.println();
		for (String word : JohnRosarSet)
			System.out.println(word);
		return JohnRosarSet;
	}

	/* creating linked hash map from my list
	 * counting the times each element appears */

	public static Map<String, Integer> JohnRosarCreateMap(List<String> JohnRosarList, Set<String> JohnRosarSet) {
		Map<String, Integer> JohnRosarMap = new LinkedHashMap<>();
		for (String word : JohnRosarSet) {

			/* initializing word count to zero */

			int wordCount = 0;
			for (String readWords : JohnRosarList)
				if (readWords.equals(word))
					wordCount++;

			/* putting items in the map */

			JohnRosarMap.put(word, wordCount);
		}

		/* creating a formatted map printing function */
		
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println();
		System.out.println("MAP           Times word appears\n");

		/* map.entry interface to access map elements */

		for (Map.Entry<String, Integer> entry : JohnRosarMap.entrySet())
			System.out.printf("%-20s%-20d\n", entry.getKey(), entry.getValue());
		return JohnRosarMap;
	}

	/* running the main and calling our methods in order requested */

	public static void main(String[] args) {

		/* Create list Method*/

		List<String> JohnRosarList = JohnRosarCreateList();

		/* process list method */

		JohnRosarProcessList(JohnRosarList);

		/* Create set method */

		Set<String> JohnRosarSet = JohnRosarCreateSet(JohnRosarList);

		/* Create map method */

		Map<String, Integer> JohnRosarMap = JohnRosarCreateMap(JohnRosarList, JohnRosarSet);


	}
}