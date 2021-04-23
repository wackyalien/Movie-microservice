package com.micro.ratingdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {
    
    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId){
        return new Rating(movieId, 4);
    }

    @GetMapping("users/{userid}")
    public UserRating getUserRating(@PathVariable String userid){

        List<Rating> ratings = Arrays.asList(
            new Rating("101", 4),
            new Rating("102",3)
        );

        UserRating userrating = new UserRating(ratings);
        return userrating;
    }
}
