package de.alexpawe.tracepointparser.things;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Task {

	private String taskID;
	private List<Tracepoint> tracepoints;
	private int iterationNr;
	
	private long maxSyscallTime = Long.MIN_VALUE;
	private long minSyscallTime = Long.MAX_VALUE;
	private double avgSyscallTime = 0;
	private long totalSyscallTime = 0;
	private int noSyscalls = 0;
	
	
	public Task(String taskID, int iterationNr, List<Tracepoint> tracepoints) {
		this.taskID = taskID;
		this.iterationNr = iterationNr;
		this.tracepoints = tracepoints;
	}
	
	public int getNumberOf(String trcpName) {
		int counter = 0;
		for (Tracepoint tracepoint : tracepoints) {
			if (tracepoint.getName().equals(trcpName)) {
					counter += 1;
			}
		}
		
		return counter;
	}
	
	private void computeSyscallTimes() {
		List<Tracepoint> startedSyscalls = new LinkedList<Tracepoint>();
		int countSyscalls = 0;
		Iterator<Tracepoint> iterator = tracepoints.iterator();
		while (iterator.hasNext()) {
			Tracepoint ctp = iterator.next();
			switch (ctp.getName()) {
			case "trace_syscall_start":
				startedSyscalls.add(ctp);
				break;
			case "trace_syscall_end":
				for (Tracepoint comparator : startedSyscalls) {
					if (comparator.getMessage().equals(ctp.getMessage())) {
						long elapsedTime = ctp.getTimeStamp() - comparator.getTimeStamp();
						if (elapsedTime > 0) {
							countSyscalls += 1;
							maxSyscallTime = (maxSyscallTime > elapsedTime)? maxSyscallTime : elapsedTime;
							minSyscallTime = (minSyscallTime < elapsedTime)? minSyscallTime : elapsedTime;
							totalSyscallTime += elapsedTime;
						}
					}
				}
				break;
			default: break;
			}
		}
		avgSyscallTime = (double)totalSyscallTime/(double)countSyscalls;
		noSyscalls = countSyscalls;
	}
	
	
	public long getMinSyscallTime() {
		if (minSyscallTime == Long.MAX_VALUE) {
			computeSyscallTimes();
		}
		return minSyscallTime;
	}
	
	public long getMaxSyscallTime() {
		if (maxSyscallTime == Long.MIN_VALUE) {
			computeSyscallTimes();
		}
		return maxSyscallTime;
	}
	
	public double getAvgSyscallTime() {
		if (avgSyscallTime == 0) {
			computeSyscallTimes();
		}
		return avgSyscallTime;
	}
	
	public long getTotalSyscallTime() {
		if (totalSyscallTime == 0) {
			computeSyscallTimes();
		}
		return totalSyscallTime;
	}
	
	public int getNoSyscalls() {
		if (noSyscalls == 0) {
			computeSyscallTimes();
		}
		return noSyscalls;
	}
	
	public List<Tracepoint> getTracepoints() {
		return tracepoints;
	}
	
	public String getTaskID() {
		return taskID;
	}
	
	public int getIterationNr() {
		return iterationNr;
	}
}
