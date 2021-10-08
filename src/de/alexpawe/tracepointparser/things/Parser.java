package de.alexpawe.tracepointparser.things;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Parser {
	
	public Parser() {}
	
	public List<Tracepoint> parse(List<String> fileContents) {
		List<Tracepoint> tracepoints = new LinkedList<Tracepoint>();
		
		Iterator<String> iterator = fileContents.iterator();
			String line = iterator.next();
			
			// Search for the separator line where the table of tracepoints with timestamps starts.
			while (!line.matches("[-]+[\s]+[-]+[\s]+[-]+")) {
				line = iterator.next();
			}
			
		while (iterator.hasNext()) {
			line = iterator.next();
			
			Tracepoint tracepoint = parseLine(line);
			
			tracepoints.add(tracepoint);
		}
		
		return tracepoints;
	}
	
	private Tracepoint parseLine(String line) {
		String[] splitLine = line.split("\s[\s]+");
		if (splitLine[0].matches("[0-9]+")) {
			int timeStamp = Integer.parseInt(splitLine[0]);
			String name = splitLine[1];
			String msg = (splitLine.length == 3)? splitLine[2] : null;
			
			//System.out.println(name + " at " + timeStamp + " with message: " + msg);
			
			return new Tracepoint(name, timeStamp, msg);
		} else {
			int timeStamp = Integer.parseInt(splitLine[1]);
			String name = splitLine[2];
			String msg = (splitLine.length == 4)? splitLine[3] : null;
			
			//System.out.println(name + " at " + timeStamp + " with message: " + msg);
			
			return new Tracepoint(name, timeStamp, msg);
		}
	}
}
