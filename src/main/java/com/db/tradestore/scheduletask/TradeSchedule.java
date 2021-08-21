package com.db.tradestore.scheduletask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.db.tradestore.service.TradeService;

@Component
public class TradeSchedule {

	@Autowired
	TradeService tradeService;
	
	
	@Scheduled(cron = "${trade.expiry.schedule}")//currently setup 30 min
	public void runTradeSchedule() {
		tradeService.updateTradeExpiryFlag();
	}
}
