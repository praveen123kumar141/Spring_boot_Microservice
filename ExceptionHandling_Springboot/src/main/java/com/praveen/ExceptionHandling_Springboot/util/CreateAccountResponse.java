package com.praveen.ExceptionHandling_Springboot.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.praveen.ExceptionHandling_Springboot.Entity.CreateAccount;


import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateAccountResponse {
    @JsonProperty("responseMessage")
    private String responseMessage;
    @JsonProperty("responseCode")
    private int responseCode;
    @JsonProperty("timeStamp")
    private Date timeStamp;
    @JsonProperty("resposeBody")
    private CreateAccount resposeBody;

    public CreateAccountResponse(String responseMessage, int responseCode, Date timeStamp, CreateAccount resposeBody) {
        this.responseMessage = responseMessage;
        this.responseCode = responseCode;
        this.timeStamp = timeStamp;
        this.resposeBody = resposeBody;
    }
}
