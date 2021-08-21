package com.db.tradestore.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.tradestore.model.Trade;
import com.db.tradestore.service.TradeService;

@RestController
public class TradeController {
	
	
	private static final Logger log = LoggerFactory.getLogger(TradeController.class);
	
	@Autowired
	TradeService tradeService;
	
	
	@PostMapping(path = "/trade",produces = {"application/json","application/xml"})
	public Trade validateAndStoreTrade(@RequestBody Trade trade) {
		
		  trade.setCreatedDate(LocalDate.now());
			try {
				tradeService.saveTrade(trade);
			}catch(Exception e) {
				log.debug("Unable to store the trade");
			}
		return trade;
	}
}
