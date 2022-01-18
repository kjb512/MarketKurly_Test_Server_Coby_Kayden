package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;

import static com.example.demo.config.BaseResponseStatus.SUCCESS;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message","errorMessage", "result"})
public class BaseResponse<T> {
//    @JsonProperty("isSuccess")
//    private final Boolean isSuccess;
//    private final String message;
//    private final int code;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private T result;

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final int code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T message;


    // validation 실패
    public BaseResponse(LinkedList errorList){
        this.isSuccess = false;
        this.code = 2090;
        this.message = (T) errorList;
    }

    // 요청에 성공한 경우
    public BaseResponse(T result) {
        this.isSuccess = SUCCESS.isSuccess();
        this.message = (T) SUCCESS.getMessage();
        this.code = SUCCESS.getCode();
        this.result = result;
    }

    // 요청에 실패한 경우
    public BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess();
        this.message = (T) status.getMessage();
        this.code = status.getCode();
    }


}

