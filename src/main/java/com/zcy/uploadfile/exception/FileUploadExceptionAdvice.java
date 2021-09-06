package com.zcy.uploadfile.exception;

import com.zcy.uploadfile.valueobject.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author zhongchengye
 * @create 2021-09-03 17:16
 */
@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

   @ExceptionHandler
    public ResponseEntity<Message> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e){
        return ResponseEntity.badRequest().body(new Message("Upload file too large."));
    }
}
