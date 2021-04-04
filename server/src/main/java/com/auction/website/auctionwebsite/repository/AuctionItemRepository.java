package com.auction.website.auctionwebsite.repository;

import com.auction.website.auctionwebsite.entity.AuctionItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface AuctionItemRepository extends CrudRepository<AuctionItem, Long> {

    Page<AuctionItem> findAll(Pageable pageable);

    Page<AuctionItem> findByNameContaining(String name, Pageable pageable);

    Page<AuctionItem> findByCategory_CategoryName(String category, Pageable pageable);
}

