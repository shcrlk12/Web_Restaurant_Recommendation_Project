package com.kjwon.foodchoice.json.errors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundKeywordException extends Exception{

    public NotFoundKeywordException(){
        super();
    }
    public NotFoundKeywordException(String message){
        super(message);
    }
}
