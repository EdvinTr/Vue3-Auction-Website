package com.auction.website.auctionwebsite.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLoginRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String profileImageUrl;
    private String token;
}
