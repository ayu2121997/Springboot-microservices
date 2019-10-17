package com.example.ratingsdataservice.Resource;
import com.example.ratingsdataservice.model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class Ratingsdata {
    @RequestMapping("/{movieId}")
    public Rating getRating (@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }
}



