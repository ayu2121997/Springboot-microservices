package com.example.moviecatalogservice.resource;

import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.UserRating;
import com.example.moviecatalogservice.models.catalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

    @RestController
    @RequestMapping("/catalog")


    public class CatalogResource {

        @Autowired
        private RestTemplate restTemplate;


        @RequestMapping("/{userId}")
        public List<catalogItem> getCatalog(@PathVariable("userId") String userId) {

            UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);

            return userRating.getRatings().stream()
                    .map(rating -> {
                        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                        return new catalogItem(movie.getName(), movie.getDescription(), rating.getRating());
                    })
                    .collect(Collectors.toList());


        }
    }

