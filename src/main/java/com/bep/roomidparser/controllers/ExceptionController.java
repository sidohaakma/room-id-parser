package com.bep.roomidparser.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 *
 * <p>Handles the fileupload errors</p>
 *
 * @author sido
 *
 */
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    private static final String REDIRECT_KEY_ATTRIBUTE_MESSAGE = "message";

    @ExceptionHandler(MultipartException.class)
    public String handleFileUploadError(MultipartException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(REDIRECT_KEY_ATTRIBUTE_MESSAGE, e.getCause().getMessage());
        return "redirect:/parser/result";

    }

}
