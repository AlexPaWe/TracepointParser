package de.alexpawe.tracepointparser.things;

public class Tracepoint {

	private String name;
	private long timestamp;
	private String msg;
	
	
	public Tracepoint(String name, long timestamp, String msg) {
		this.name = name;
		this.timestamp = timestamp;
		this.msg = msg;
	}
	
	public String getName() {
		return name;
	}
	
	public long getTimeStamp() {
		return timestamp;
	}
	
	public String getMessage() {
		return msg;
	}
}
