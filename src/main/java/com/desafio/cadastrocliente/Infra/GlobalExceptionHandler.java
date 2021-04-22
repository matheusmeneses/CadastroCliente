package com.desafio.cadastrocliente.Infra;

import com.desafio.cadastrocliente.excecoes.ViolacaoRegraException;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ViolacaoRegraException.class})
    public final ResponseEntity<ApiErro> handleException(Exception ex, WebRequest request){
        HttpHeaders headers = new HttpHeaders();

        if(ex instanceof ViolacaoRegraException){
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ViolacaoRegraException violacao = (ViolacaoRegraException) ex;
            return new ResponseEntity<>(new ApiErro(status.toString(), violacao.getMessage()), headers, status);
        }else{
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE,ex,WebRequest.SCOPE_REQUEST);
            return new ResponseEntity<>(null, headers, status);
        }


    }
}
