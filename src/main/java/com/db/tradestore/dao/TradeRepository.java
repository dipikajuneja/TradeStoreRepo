package com.db.tradestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.tradestore.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, String> {

}
