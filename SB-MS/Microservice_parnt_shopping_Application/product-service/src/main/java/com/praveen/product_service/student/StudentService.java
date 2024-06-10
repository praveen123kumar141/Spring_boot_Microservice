package com.praveen.product_service.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.praveen.product_service.student.Exceptions.MyServiceException;
import com.praveen.product_service.student.Exceptions.ResourceNotFound;
import com.praveen.product_service.student.config.WebClientConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.type.SqlTypes.JSON;


@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final WebClientConfig webClientConfig;


    public List<StudentResponse> getAllStudentDetails() {
        log.info("Enter into Product-service "+"getAllStudentDetails()");
        List<StudentResponse> StudentResposes = webClientConfig.webClient().get()
                .uri("http://localhost:9092/api/student/getAllStudents")
                .retrieve()
                .bodyToFlux(StudentResponse.class).collectList()
                .block();
        return StudentResposes;
    }
    //https://howtodoinjava.com/spring-webflux/webclient-post-examples/
    //https://github.com/codefarm0/microservice-communication-springboot/blob/main/webclient/productclient/src/main/java/com/codefarm/productclient/service/ProductClientService.java
    public ResponseEntity<StudentResponse> createStudent(StudentResponse student) {
        log.info("Entter into the ProdectService"+"createStudent");
        ResponseEntity<StudentResponse> block1 = webClientConfig.webClient().post()
                .uri("http://localhost:9092/api/student/create")
                .body(BodyInserters.fromValue(student))
                .retrieve()
                .onRawStatus(
                        status -> status == 500, resonse -> {
                            return Mono.error(new MyServiceException("Bad Request"));
                        })
                .onRawStatus(
                        status -> status == 404, response -> {
                            return Mono.error(new ResourceNotFound("No Resource found"));
                        }
                ).onStatus(
                        HttpStatusCode::is4xxClientError, clientResponse -> {
                            return Mono.error(new ResourceNotFound("No Resource found"));
                        }
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError, clientResponse -> {
                            return Mono.error(new MyServiceException("Server error......"));
                        }
                )
                .toEntity(StudentResponse.class)
                .log()
                .doOnSubscribe(subscription -> log.info("subscribed .."))
                .doOnSuccess(succ -> log.info("suceess.. {} ", succ))
                .doOnError(erro -> log.error("errored out ", erro))
//                .retry(3)
                .retryWhen(Retry.fixedDelay(10, Duration.ofMillis(1000))
                        .doBeforeRetry(x -> log.info("retrying.. -{}", x.totalRetries()))
                        .doAfterRetry(x -> log.info("after retry .. -{}", x.totalRetriesInARow()))
                )
                .block();

        return  block1;
    }

    public StudentResponse getstudentById(String name) {
        ResponseEntity<StudentResponse> block1 = webClientConfig.webClient().get()
                .uri("http://localhost:9092/api/student/getAccountBy/praveen")
                .retrieve()
                .onRawStatus(
                        status -> status == 500, resonse -> {
                            return Mono.error(new MyServiceException("Bad Request"));
                        })
                .onRawStatus(
                        status -> status == 404, response -> {
                            return Mono.error(new ResourceNotFound("No Resource found"));
                        }
                ).onStatus(
                        HttpStatusCode::is4xxClientError, clientResponse -> {
                            return Mono.error(new ResourceNotFound("No Resource found"));
                        }
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError, clientResponse -> {
                            return Mono.error(new MyServiceException("Server error......"));
                        }
                )
                .toEntity(StudentResponse.class)
                .block();
        StudentResponse body = block1.getBody();
        return body;
    }

    public StudentResponse getstudentByIdReqParam(String name) {
        ResponseEntity<StudentResponse> block1 = webClientConfig.webClient().get()
                .uri("http://localhost:9092/api/student/getAccountByReqPerm",uriBuilder ->
                    uriBuilder.queryParam("name",name).build()
                )
                .retrieve()
                .onRawStatus(
                        status -> status == 500, resonse -> {
                            return Mono.error(new MyServiceException("Bad Request"));
                        })
                .onRawStatus(
                        status -> status == 404, response -> {
                            return Mono.error(new ResourceNotFound("No Resource found"));
                        }
                ).onStatus(
                        HttpStatusCode::is4xxClientError, clientResponse -> {
                            return Mono.error(new ResourceNotFound("No Resource found"));
                        }
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError, clientResponse -> {
                            return Mono.error(new MyServiceException("Server error......"));
                        }
                )
                .toEntity(StudentResponse.class)
                .block();
        StudentResponse body = block1.getBody();
        return body;
    }
}
