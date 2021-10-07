package de.alexpawe.tracepointparser.things;

import java.util.List;

public class Task {

	private String taskID;
	private List<Tracepoint> tracepoints;
	private int iterationNr;
	
	
	public Task(String taskID, int iterationNr, List<Tracepoint> tracepoints) {
		this.taskID = taskID;
		this.iterationNr = iterationNr;
		this.tracepoints = tracepoints;
	}
	
	public int getNumberOf(String trcpName) {
		int counter = 0;
		for (Tracepoint tracepoint : tracepoints) {
			if (tracepoint.getName() == trcpName)
					counter++;
		}
		
		return counter;
	}
	
	public List<Tracepoint> getTracepoints() {
		return tracepoints;
	}
	
	public String getTaskID() {
		return taskID;
	}
}
