package com.auction.website.auctionwebsite.entity;


import com.auction.website.auctionwebsite.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;


    private String token;

    @Min(value = 0)
    @Max(value = 5)
    private Integer rating = 0;

    @Column(name = "role")
    private UserRole role = UserRole.USER;

    @NotNull
    private String profileImageUrl;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<AuctionItem> auctionItems = new ArrayList<>();

    //rename to userBids
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<Bid> bidList = new ArrayList<>();
}
