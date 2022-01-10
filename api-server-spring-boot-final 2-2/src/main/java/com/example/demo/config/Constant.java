package com.example.demo.config;

import org.springframework.validation.Errors;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Constant {

    // validation error를 BaseResponse에 넣기 위하여 정제
    public static LinkedList<LinkedHashMap<String,String>> refineErrors(Errors errors){
        LinkedList errorList = new LinkedList<LinkedHashMap<String, String>>();
        errors.getFieldErrors().forEach(e-> {
            LinkedHashMap<String, String> error = new LinkedHashMap<>();
            error.put(e.getField(), e.getDefaultMessage());
            errorList.push(error);
        });
        return errorList;
    }
}

