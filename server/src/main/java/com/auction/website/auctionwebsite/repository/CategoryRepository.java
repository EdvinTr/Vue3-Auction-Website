package com.auction.website.auctionwebsite.repository;

import com.auction.website.auctionwebsite.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
