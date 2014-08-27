package util;

import org.joda.time.DateTime;

public class Log {

	public static void info(String log) {
		System.out.println(new DateTime().toString("yyyy-MM-dd HH:mm:ss") + " " + log);
	}

}
