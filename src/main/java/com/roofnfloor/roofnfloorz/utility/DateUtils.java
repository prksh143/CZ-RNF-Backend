package com.roofnfloor.roofnfloorz.utility;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Utility class for date format and handling
 * @author Raman
 *
 */
@Component
public class DateUtils {

	
	public LocalDateTime getCurrentDate() {
		return LocalDateTime.now();
	}
	public Long getTimeMilli() {
		Date date = new Date();
		return date.getTime();
	}
	
}
