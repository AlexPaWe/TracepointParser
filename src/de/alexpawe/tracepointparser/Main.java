package de.alexpawe.tracepointparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import de.alexpawe.tracepointparser.things.Parser;
import de.alexpawe.tracepointparser.things.Task;
import de.alexpawe.tracepointparser.things.Tracepoint;

public class Main {
	
	public static final String TRACES_LIST_FILE_NAME = "traces.dat_list.txt";
	
	private static List<Task> tasks;


	public static void main(String[] args) {
		
		// TODO: Import tracepoints from traces.dat file(s)
		File resultsDirectory = new File("/results");
		File[] resultsFolders = resultsDirectory.listFiles();
		for (File taskFolder : resultsFolders) {
			File[] taskFiles = taskFolder.listFiles();
			for (File taskFile : taskFiles) {
				if (taskFile.getName() == TRACES_LIST_FILE_NAME) {
					try {
						Scanner fileScanner = new Scanner(taskFile);
						List contents = new LinkedList();
						while(fileScanner.hasNextLine()) {
							contents.add(fileScanner.nextLine());
						}
						
						Parser parser = new Parser();
						List<Tracepoint> tracepoints = parser.parse(contents);
						
						int iterationNr = 1; // TODO: Nake this non static
						Task task = new Task(taskFolder.getName(), iterationNr, tracepoints);
						
						tasks = new LinkedList<Task>();
						tasks.add(task);
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		// TODO: Do computations to extract metrices
		
	}

}
