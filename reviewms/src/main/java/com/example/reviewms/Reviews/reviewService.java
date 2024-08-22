package com.example.reviewms.Reviews;

import java.util.List;

public interface reviewService {
    List<review> getReviews(int id);
    boolean addReviews(int id, review r);
//    review reviewById(int reviewId, int companyId);
    String updateReviewById(int reviewId, review updateReview);
    boolean deleteReviewById(int reviewId);
}
