package com.fx.calc.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;

/**
 * This represents the structure of the json payload that will be sent back in the HTTP response
 *
 * @author efe ariaroo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean status;
    private String message;
    private Serializable data;

    List<ResponseError> errors;

    public ResponseDto() {
    }

    public ResponseDto(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDto(Boolean status, String message, Serializable data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseDto(Boolean status, String message, Serializable data, List<ResponseError> errors) {
        this(status, message);
        this.errors = errors;
    }

    public Boolean getStatus() {
        return status;
    }

    public ResponseDto setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public Serializable getData() {
        return data;
    }

    public ResponseDto setData(Serializable data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseDto{" + "status=" + status + ", message=" + message + ", data=" + data + '}';
    }

    public static class ResponseError {
        public String fieldName;
        public String fieldError;

        public ResponseError(String fieldName, String fieldError) {
            this.fieldName = fieldName;
            this.fieldError = fieldError;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldError() {
            return fieldError;
        }

        public void setFieldError(String fieldError) {
            this.fieldError = fieldError;
        }
    }
}
