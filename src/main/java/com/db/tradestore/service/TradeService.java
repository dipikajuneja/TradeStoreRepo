package com.db.tradestore.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.tradestore.dao.TradeRepository;
import com.db.tradestore.exception.LowerTradeVersionException;
import com.db.tradestore.model.Trade;
import com.db.tradestore.util.TradeUtil;

@Service
public class TradeService {
	

	@Autowired
	TradeRepository tradeRepository;
	
	public void saveTrade(Trade trade) {
		//validate trade version
		if(validTradeVersion(trade)) {
			//compare maturity date with today's date
			if(TradeUtil.isLowDate(trade.getMaturityDate()))
				tradeRepository.save(trade);
		}else {
			throw new LowerTradeVersionException("New Trade version is lower than existing trade");
		}
			
	}
	
	
	//check for version 
	public boolean validTradeVersion(Trade trade) {
		
		Optional<Trade> checkExistingTrade = tradeRepository.findById(trade.getTradeId());
		//check for version if trade exits
		if(checkExistingTrade.isPresent()) 
			 return TradeUtil.validateTradeVersion(trade.getVersion(), checkExistingTrade.get().getVersion());
		
		  return false;
		
	}
	
	//return all the trades
	public List<Trade> getAllTrades() {
		return tradeRepository.findAll();
	}


	public void updateTradeExpiryFlag() {
		tradeRepository.findAll().stream().forEach(trade -> {
			if(!TradeUtil.isLowDate(trade.getMaturityDate())) {
				trade.setExpired("Y");
				tradeRepository.save(trade);
			}
		});
	}
}
