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
			
		while (iterator.hasNext()) {
			line = iterator.next();
			
			Tracepoint tracepoint = parseLine(line);
			
			if (tracepoint != null) {
				tracepoints.add(tracepoint);
			}
		}
		return tracepoints;
	}
	
	private Tracepoint parseLine(String line) {						// Example tracepoint line: 0000000000000000 trace_vfs_dup3: 0 1 0x0
		if (line.matches("[a-z][a-z_0-9]*") || line.equals("")) {	// Other lines are either "name [0-9]+" or empty
			return null;
		}
		String[] fsplitLine = line.split(": ");
		String[] ssplitLine = fsplitLine[0].split(" ");
		int timeStamp = Integer.parseInt(ssplitLine[0]);
		String name   = ssplitLine[1];
		String msg    = fsplitLine[1];
		
		return new Tracepoint(name, timeStamp, msg);
	}
}
