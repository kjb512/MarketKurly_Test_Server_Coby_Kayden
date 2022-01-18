package com.example.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;

@Getter
@Setter
@AllArgsConstructor
public class ReviewReq {
    private MultipartFile multipart;
    private ReviewDto reviewDto;
}
