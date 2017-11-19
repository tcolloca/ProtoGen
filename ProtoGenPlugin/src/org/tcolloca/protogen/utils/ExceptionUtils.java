package org.tcolloca.protogen.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

	public static String stackTraceToString(Throwable th) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		th.printStackTrace(pw);
		String str = sw.toString();
		pw.close();
		return str;
	}
}
