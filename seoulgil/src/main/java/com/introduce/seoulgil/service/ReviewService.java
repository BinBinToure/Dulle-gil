package com.introduce.seoulgil.service;


import com.introduce.seoulgil.dto.ReviewDTO;
import com.introduce.seoulgil.entity.Member;
import com.introduce.seoulgil.entity.Notice;
import com.introduce.seoulgil.entity.Review;

import java.util.List;

public interface ReviewService {

    //영화의 모든 영화리뷰를 가져온다.
    List<ReviewDTO> getListOfNotice(Long mno);

    //영화 리뷰를 추가
    Long register(ReviewDTO noticeReviewDTO);

    //특정한 영화리뷰 수정
    void modify(ReviewDTO noticeReviewDTO);

    //영화 리뷰 삭제
    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO noticeReviewDTO){

        Review noticeReview = Review.builder()
                .reviewnum(noticeReviewDTO.getReviewnum())
                .notice(Notice.builder().mno(noticeReviewDTO.getMno()).build())
                .member(Member.builder().mid(noticeReviewDTO.getMid()).build())
                .grade(noticeReviewDTO.getGrade())
                .text(noticeReviewDTO.getText())
                .build();

        return noticeReview;
    }

    default ReviewDTO entityToDto(Review noticeReview){

        ReviewDTO noticeReviewDTO = ReviewDTO.builder()
                .reviewnum(noticeReview.getReviewnum())
                .mno(noticeReview.getNotice().getMno())
                .mid(noticeReview.getMember().getMid())
                .nickname(noticeReview.getMember().getNickname())
                .email(noticeReview.getMember().getEmail())
                .grade(noticeReview.getGrade())
                .text(noticeReview.getText())
                .regDate(noticeReview.getRegDate())
                .modDate(noticeReview.getModDate())
                .build();

        return noticeReviewDTO;
    }
}
