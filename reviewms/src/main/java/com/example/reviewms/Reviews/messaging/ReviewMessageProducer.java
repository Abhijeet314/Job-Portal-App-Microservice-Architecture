package com.example.reviewms.Reviews.messaging;

import com.example.reviewms.Reviews.dtoReview;
import com.example.reviewms.Reviews.review;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {
    private  final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public  void sendMessage(review r) {
        dtoReview reviewMessage = new dtoReview();
        reviewMessage.setComment(r.getComment());
        reviewMessage.setDislikes(r.getDislikes());
        reviewMessage.setId(r.getId());
        reviewMessage.setLikes(r.getLikes());
        reviewMessage.setCompanyId(r.getCompanyId());
        reviewMessage.setLovedOrNot(r.isLovedOrNot());

        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
    }
}
