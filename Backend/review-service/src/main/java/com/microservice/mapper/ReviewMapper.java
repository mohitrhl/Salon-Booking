package com.microservice.mapper;


import com.microservice.modal.Review;
import com.microservice.payload.dto.ReviewDTO;
import com.microservice.payload.dto.UserDTO;

public class ReviewMapper {

    public static ReviewDTO mapToDTO(Review review, UserDTO userDTO) {
        if (review == null) {
            return null;
        }

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setReviewText(review.getReviewText());
        reviewDTO.setRating(review.getRating());
//        reviewDTO.setSalonId(review.getSalonId());
        reviewDTO.setUser(userDTO);
        reviewDTO.setCreatedAt(review.getCreatedAt());

        return reviewDTO;
    }
}
