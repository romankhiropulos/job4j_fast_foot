package ru.job4j.payment.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.job4j.payment.controller.PaymentController;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@RestControllerAdvice(assignableTypes = {
        PaymentController.class
})
public class RestExceptionHandler {

    private final ObjectMapper objectMapper;

    public RestExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(value = {
            NullPointerException.class
    })
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        Map<String, String> map = Map.of("message", "Some of fields empty", "details", e.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(map));
        log.error(e.getMessage());

        Stream<Integer> numStream = Stream.of(43, 65, 1, 98, 63);
        Integer largest = numStream.max(Integer::compare).get();
    }

    @ExceptionHandler(value = {
            IllegalArgumentException.class
    })
    public void exceptionHandler(Exception e,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(
                Map.of("message", e.getMessage(), "type", e.getClass()))
        );
        log.error(e.getLocalizedMessage());
    }
}
