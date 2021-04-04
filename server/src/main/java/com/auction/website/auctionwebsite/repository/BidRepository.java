package com.auction.website.auctionwebsite.repository;

import com.auction.website.auctionwebsite.entity.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends CrudRepository<Bid, Long> {
}
