package com.ft.ahp.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.ft.ahp.util.DateFormatHelper;

public class DateFormatHelperTest {

	@Test
	public void testGetISOFormattedDate() {
		Date date = new Date();
		date.setDate(date)
		DateFormatHelper.getISOFormattedDate(date);
	}

}
