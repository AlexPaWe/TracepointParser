package de.alexpawe.tracepointparser.things;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Parser {
	
	public Parser() {}
	
	public List<Tracepoint> parse(List<String> fileContents) {
		List<Tracepoint> tracepoints = new LinkedList<Tracepoint>();
		
		Iterator<String> iterator = fileContents.iterator();
		while (iterator.hasNext()) {
			do {} while (!iterator.next().matches("[-]*"));
			iterator.next();
			
			String line = iterator.next();
			String[] splitLine = line.split(" ");
			Tracepoint tracepoint = new Tracepoint(splitLine[1], Integer.parseInt(splitLine[0]), splitLine[2]);
			
			tracepoints.add(tracepoint);
		}
		
		return tracepoints;
	}
}
