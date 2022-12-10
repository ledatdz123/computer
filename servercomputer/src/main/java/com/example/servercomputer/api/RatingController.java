package com.example.servercomputer.api;

import com.example.servercomputer.dto.RequestRatingDto;
import com.example.servercomputer.dto.ResponseListRating;
import com.example.servercomputer.dto.ResponseRatingDto;
import com.example.servercomputer.exception.ResourceNotFoundException;
import com.example.servercomputer.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @PostMapping("/{id}/rating")
    @Operation(summary = "Rating products",
            tags = {"User"})
    public ResponseRatingDto addRatingForProduct(@PathVariable("id") Long id,
                                                 @RequestBody RequestRatingDto requestRatingDto) {
        return ratingService.addRating(requestRatingDto, id);
    }

    @GetMapping("/{id}/rating")
    @Operation(summary = "Get list rating of product",
            tags = {"User"})
    public List<ResponseListRating> getListRating(@PathVariable Long id) throws ResourceNotFoundException {
        return ratingService.getRatingsOfProduct(id);
    }

}
