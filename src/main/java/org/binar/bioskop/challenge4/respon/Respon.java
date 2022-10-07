package org.binar.bioskop.challenge4.respon;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Respon {
    private String responCode;
    private String message;
    private Object data;
}

//http://localhost:8000/swagger-ui/index.html#/

