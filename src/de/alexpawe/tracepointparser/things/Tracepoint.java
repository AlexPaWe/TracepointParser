package de.alexpawe.tracepointparser.things;

public class Tracepoint {

	private String name;
	private int timestamp;
	private String msg;
	
	
	public Tracepoint(String name, int timestamp, String msg) {
		this.name = name;
		this.timestamp = timestamp;
		this.msg = msg;
	}
	
	public String getName() {
		return name;
	}
	
	public int getTimeStamp() {
		return timestamp;
	}
	
	public String getMessage() {
		return msg;
	}
}
