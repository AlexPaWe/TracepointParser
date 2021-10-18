package de.alexpawe.tracepointparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import de.alexpawe.tracepointparser.things.Parser;
import de.alexpawe.tracepointparser.things.Task;
import de.alexpawe.tracepointparser.things.Tracepoint;

public class Main {
	
	private static List<Task> tasks;


	public static void main(String[] args) {
		
		// TODO: Import tracepoints from traces.txt file(s)
		File resultsDirectory = Paths.get("results").toFile();
		File[] resultsFolders = resultsDirectory.listFiles();
		for (File taskFolder : resultsFolders) {
			File[] taskFiles = taskFolder.listFiles();
			if (taskFiles != null) {
				for (File taskFile : taskFiles) {
					if (taskFile.getName().matches("traces[0-9]+.txt")) {
						
						// Get iteration number from filename
						String tmp = taskFile.getName().replace("traces", "");
						tmp = tmp.replace(".txt", "");
						int iterationNr = Integer.parseInt(tmp);
						
						try {
							Scanner fileScanner = new Scanner(taskFile);
							List contents = new LinkedList();
							while(fileScanner.hasNextLine()) {
								contents.add(fileScanner.nextLine());
							}
						
							Parser parser = new Parser();
							List<Tracepoint> tracepoints = parser.parse(contents);
						
							Task task = new Task(taskFolder.getName(), iterationNr, tracepoints);
						
							tasks = new LinkedList<Task>();
							tasks.add(task);
							
							System.out.println("Number of \"trace_yield\"s: " + task.getNumberOf("trace_yield"));
							System.out.println("Number of \"trace_thread_switch\"'s: " + task.getNumberOf("trace_thread_switch"));
							
							System.out.println("-----------------------------------------\n" +
											   "Number of total tracepoints: " + task.getTracepoints().size());
						
							//Create table and export as csv
							//Create header
							String tablestring = "Iteration,No_total_tps,No_yields,No_thread_switches,Min_syscall_time,Max_syscall_time,Avg_syscall_time,Total_syscall_time,Number_of_syscalls\n";
							//fill table
							for (Task cTask : tasks) {
								tablestring = tablestring + cTask.getIterationNr() +
													  "," + cTask.getTracepoints().size() +
													  "," + cTask.getNumberOf("trace_yield") +
													  "," + cTask.getNumberOf("trace_thread_switch") +
													  "," + cTask.getMinSyscallTime() +
													  "," + cTask.getMaxSyscallTime() +
													  "," + cTask.getAvgSyscallTime() + 
													  "," + cTask.getTotalSyscallTime() +
													  "," + cTask.getNoSyscalls() + "\n";
							}
							
							// Write tablestring to csv file
							File csvFile = new File(taskFolder.toString() + "/traces" + iterationNr + ".csv");
							try (PrintWriter writer = new PrintWriter(csvFile)) {
								writer.write(tablestring);
								writer.close();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
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
