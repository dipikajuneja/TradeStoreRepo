package com.db.tradestore.util;

import java.time.LocalDate;

public class TradeUtil {
	
	/**
	 * check if the date is less than today date
	 * @param date
	 * @return
	 */
	public static boolean isLowDate(LocalDate date) {
		return date.isBefore(LocalDate.now()) ? false : true;
		
	}
	
	/**
	 * check for version(new version is less than old version)
	 * @param newVersion
	 * @param oldVersion
	 * @return
	 */

	public static boolean validateTradeVersion(int newVersion,int oldVersion) {
		return newVersion > oldVersion ? true : false;
	}
}
