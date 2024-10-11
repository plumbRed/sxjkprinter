package com.example.demo.dto;

import lombok.Data;

@Data
public class ResultVO<T> {

    private int resultCode;

    private String errorMsg;

    private T data;

    public ResultVO() {
    }

    public ResultVO(int resultCode, String errorMsg, T data){
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }
}
