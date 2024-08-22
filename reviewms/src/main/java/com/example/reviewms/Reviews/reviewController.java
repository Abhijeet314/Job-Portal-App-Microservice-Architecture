package com.example.reviewms.Reviews;

import com.example.reviewms.Reviews.messaging.ReviewMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/reviews")
public class reviewController {
    reviewService reviewService;
    ReviewMessageProducer reviewMessageProducer;

    public reviewController(reviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @GetMapping
    public ResponseEntity<List<review>> getReviews(@RequestParam int companyId) {
        return new ResponseEntity<>(reviewService.getReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReviews(@RequestParam int companyId, @RequestBody review r) {
        boolean isReviewed = reviewService.addReviews(companyId, r);
        if(isReviewed) {
            reviewMessageProducer.sendMessage(r);
            return new ResponseEntity<>("Review added", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/reviews/{reviewId}")
//    public ResponseEntity<review> getReviewById(@PathVariable int reviewId, @PathVariable int companyId) {
//        review r = reviewService.reviewById(reviewId, companyId);
//        return new ResponseEntity<>(r, HttpStatus.OK);
//    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable int reviewId,  @RequestBody review updateReview) {
        String response = reviewService.updateReviewById(reviewId, updateReview);
        if(!Objects.equals(response, "")) {
            return new ResponseEntity<>("Review updated succesfully", HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable int reviewId) {
        boolean isDeleted = reviewService.deleteReviewById(reviewId);
        if(isDeleted) {
            return new ResponseEntity<>("Review deleted succesfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam int companyId){
       List<review> reviews =  reviewService.getReviews(companyId);
       return reviews.stream().mapToDouble(review::getLikes).average().orElse(0.0);
    }

}
