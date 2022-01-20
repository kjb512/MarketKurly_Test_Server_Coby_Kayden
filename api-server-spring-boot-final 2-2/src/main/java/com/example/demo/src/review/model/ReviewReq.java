package com.example.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;

@Getter
@Setter
public class ReviewReq {
    private MultipartFile image;
    private ReviewDto reviewDto;
}
