package com.example.demo.dto;

import lombok.Data;

@Data
public class ResultVO {

    private int resultCode;

    private String ErrorMsg;

    public ResultVO() {
    }

    public ResultVO(int resultCode, String ErrorMsg){
        this.resultCode = resultCode;
        this.ErrorMsg = ErrorMsg;
    }
}
