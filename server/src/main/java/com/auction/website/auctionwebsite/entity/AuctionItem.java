package com.auction.website.auctionwebsite.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class AuctionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auction_item_id")
    private Long auctionItemId;

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    @Lob
    private String description;

    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private String imageUrl;

    //add not null
    @OneToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    //add not null

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(targetEntity = Bid.class, cascade = CascadeType.ALL)
    @OrderBy("creationDate DESC")
    @JoinColumn(name = "auction_item_id", referencedColumnName = "auction_item_id")
    private List<Bid> bids;

}
