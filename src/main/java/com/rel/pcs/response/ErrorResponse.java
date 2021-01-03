package com.rel.pcs.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class ErrorResponse {

  @JsonIgnore
  private HttpStatus status;

  private String message;

  private ErrorResponse(ErrorResponseBuilder builder) {
    this.status = builder.status;
    this.message = builder.message;
  }

  public HttpStatus getStatus() {

    return status;
  }

  public String getMessage() {

    return message;
  }

  @Override
  public String toString() {

    return "ErrorResponse [status=" + status + ", message=" + message + "]";
  }

  public static class ErrorResponseBuilder {

    private HttpStatus status;
    private String message;

    public ErrorResponseBuilder(HttpStatus status) {

      this.status = status;
    }

    public HttpStatus getStatus() {

      return status;
    }

    public ErrorResponseBuilder setStatus(HttpStatus status) {

      this.status = status;
      return this;
    }

    public String getMessage() {

      return message;
    }

    public ErrorResponseBuilder setMessage(String message) {

      this.message = message;
      return this;
    }

    public ErrorResponse build() {

      return new ErrorResponse(this);
    }

  }
}
