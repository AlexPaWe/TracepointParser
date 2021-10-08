package de.alexpawe.tracepointparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
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
		File resultsDirectory = Paths.get("results").toFile();
		File[] resultsFolders = resultsDirectory.listFiles();
		for (File taskFolder : resultsFolders) {
			System.out.println(taskFolder.getName());
			File[] taskFiles = taskFolder.listFiles();
			if (taskFiles != null) {
				for (File taskFile : taskFiles) {
					System.out.println(taskFile.getName());
					if (taskFile.getName().equals(TRACES_LIST_FILE_NAME)) {
						System.out.println("Traces list file found.");
						try {
							Scanner fileScanner = new Scanner(taskFile);
							List contents = new LinkedList();
							while(fileScanner.hasNextLine()) {
								contents.add(fileScanner.nextLine());
							}
						
							Parser parser = new Parser();
							List<Tracepoint> tracepoints = parser.parse(contents);
						
							int iterationNr = 1; // TODO: Make this non static
							Task task = new Task(taskFolder.getName(), iterationNr, tracepoints);
						
							tasks = new LinkedList<Task>();
							tasks.add(task);
							
							System.out.println("Number of \"trace_yield\"s: " + task.getNumberOf("trace_yield"));
							System.out.println("Number of \"trace_thread_switch\"'s: " + task.getNumberOf("trace_thread_switch"));
							
							System.out.println("-----------------------------------------\n" +
											   "Number of total tracepoints: " + task.getTracepoints().size());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		// TODO: Do computations to extract metrices
		
	}

}
