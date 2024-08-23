package com.praveen.order_service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MyServiceException extends RuntimeException {

    private final String msg;
}
