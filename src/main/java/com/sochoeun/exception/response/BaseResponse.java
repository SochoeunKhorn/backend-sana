package com.sochoeun.exception.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseResponse {
    private HttpStatus status;
    private String message;
    private Object object;

    public void getSuccess(Object object){
        this.status = HttpStatus.OK;
        this.message = "API Get Success";
        this.object = object;
    }
}
