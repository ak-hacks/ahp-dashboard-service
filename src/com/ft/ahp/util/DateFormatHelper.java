package com.ft.ahp.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author anurag.kapur
 */
public class DateFormatHelper {

	public static String getISOFormattedDate(Date date) {
		
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(date);
	}
}
