package uk.co.aledlewis.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

  private String resultCode;
  private String message;

  public Result(String resultCode, String message) {
    this.resultCode = resultCode;
    this.message = message;
  }

  @JsonProperty
  public String getMessage() {
    return message;
  }

  @JsonProperty
  public String getResultCode() {
    return resultCode;
  }
}
