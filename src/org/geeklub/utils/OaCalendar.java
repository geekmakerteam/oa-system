package org.geeklub.utils;


public class OaCalendar {



	/**
	 * 将0-9转换为"01,02,03,04,05,06,07,08,09"
	 * @param c
	 * @return
	 */
	public static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

}
