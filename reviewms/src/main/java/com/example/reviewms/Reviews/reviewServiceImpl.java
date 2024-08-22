package com.example.reviewms.Reviews;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class reviewServiceImpl implements reviewService{
   private final reviewRepository reviewRepo;
   review r = new review();

    public reviewServiceImpl(reviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }
    @Override
    public List<review> getReviews(int id) {
        List<review> r = reviewRepo.findByCompanyId(id);
        return r;
    }


    @Override
    public boolean addReviews(int id, review r) {
        if(id!=0) {
            r.setCompanyId(id);
            reviewRepo.save(r);
            return true;
        }
        return  false;
    }

//    @Override
//    public review reviewById(int reviewId, int companyId) {
//        Companies company = companyService.getCompanyById(companyId);
//        return company.getReviewListById(reviewId);
//    }

    @Override
    public String updateReviewById(int reviewId, review updateReview) {
        review r = reviewRepo.findById(reviewId).orElse(null);
        if(r!=null) {
            r.setLikes(updateReview.getLikes());
            r.setComment(updateReview.getComment());
            r.setDislikes(updateReview.getDislikes());
            r.setLovedOrNot(updateReview.isLovedOrNot());
            reviewRepo.save(r);
            return "Review Updated Succesfully";
        }
        return "";
    }

    @Override
    public boolean deleteReviewById(int reviewId) {
        review r = reviewRepo.findById(reviewId).orElse(null);
        if(r!=null) {
            reviewRepo.delete(r);
            return  true;
        }
        return false;
    }
}
