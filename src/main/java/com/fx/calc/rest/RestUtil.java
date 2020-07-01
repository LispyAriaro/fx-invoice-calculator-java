package com.fx.calc.rest;

import com.fx.calc.exceptions.InvalidDataFormatException;
import com.fx.calc.models.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;

/**
 * @author efe ariaroo
 */
public class RestUtil {

    /**
     * Performs validation.
     *
     * @param fields the binding result holding the fields
     * @throws InvalidDataFormatException if one or more field fails the validation test
     */
    static void validate(BindingResult fields) throws Exception {
        if (fields.hasErrors()) {
            final FieldError fieldError = fields.getFieldError();
            if (fieldError != null) {
                throw new InvalidDataFormatException(fieldError.getDefaultMessage());
            }
        }
    }

    public static ResponseEntity<ResponseDto> response(HttpStatus httpStatus, ResponseDto.Status status, String message, Object data) {
        return response(httpStatus, status, message, data, null);
    }

    private static ResponseEntity<ResponseDto> response(HttpStatus httpStatus, ResponseDto.Status status, String message, Object data, String code) {
        return ResponseEntity.status(httpStatus).body(new ResponseDto(status, message, (Serializable) data, code));
    }
}
