package com.example.servercomputer.service;

import com.example.servercomputer.dto.RequestRatingDto;
import com.example.servercomputer.dto.ResponseListRating;
import com.example.servercomputer.dto.ResponseRatingDto;
import com.example.servercomputer.exception.ResourceNotFoundException;

import java.util.List;

public interface RatingService {
    ResponseRatingDto addRating(RequestRatingDto requestRatingDto, Long productId);

    List<ResponseListRating> getRatingsOfProduct(Long productId) throws ResourceNotFoundException;
}
