package org.tcolloca.protogen.utils;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;

public class ProtoGenLog {

	private static final String PLUGIN_ID = "org.tcolloca.protogen";
	private static final String ERROR_MESSAGE = "Something went wrong.";
	private final ILog log;

	public ProtoGenLog(ILog log) {
		this.log = log;
	}
	
	public void info(String message) {
		log(Status.INFO, message);
	}
	
	public void warning(String message) {
		log(Status.WARNING, message);
	}
	
	public void error(Throwable cause) {
		log.log(new Status(Status.ERROR, PLUGIN_ID, ERROR_MESSAGE, cause));
	}
	
	private void log(int statusCode, String message) {
		log.log(new Status(statusCode, PLUGIN_ID, message));
	}
}
