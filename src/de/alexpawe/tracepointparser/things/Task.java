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
	
	public List<Tracepoint> getTracepoints() {
		return tracepoints;
	}
	
	public String getTaskID() {
		return taskID;
	}
}
