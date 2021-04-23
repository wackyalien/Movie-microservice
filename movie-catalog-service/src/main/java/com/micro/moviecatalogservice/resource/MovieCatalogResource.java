package com.micro.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingdata/users/"+ userId, UserRating.class);

        return ratings.getUserRating().stream().map(rating -> {
        Movie movie= restTemplate.getForObject("http://localhost:8082/Movie/"+ rating.getMovieId() , Movie.class);
            return new CatalogItem(movie.getName(), "Test", rating.getRating());})
                                .collect(Collectors.toList());

        // return Collections.singletonList(
        //     new CatalogItem("Transformer", "Test", 4)
        // );
    }
}
