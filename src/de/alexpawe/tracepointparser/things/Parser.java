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
			while (!iterator.next().equals("---------  ---------------------  ---------------------------------------------"));
			System.out.println("Separator line found");
			System.out.println(iterator.next());
			
			String line = iterator.next();
			String[] splitLine = line.split(" ");
			System.out.println(splitLine);
			//Tracepoint tracepoint = new Tracepoint(splitLine[1], Integer.parseInt(splitLine[0]), splitLine[2]);
			
			//tracepoints.add(tracepoint);
		}
		
		return tracepoints;
	}
}
