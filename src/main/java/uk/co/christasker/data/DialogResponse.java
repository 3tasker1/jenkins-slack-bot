package uk.co.christasker.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DialogResponse {

  String type;
  String callback_id;
  String response_url;
  Map<String, Object> submission;

  public DialogResponse(){};

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCallback_id() {
    return callback_id;
  }

  public void setCallback_id(String callback_id) {
    this.callback_id = callback_id;
  }

  public String getResponse_url() {
    return response_url;
  }

  public void setResponse_url(String response_url) {
    this.response_url = response_url;
  }

  public Map<String, Object> getSubmission() {
    return submission;
  }

  public void setSubmission(Map<String, Object> submission) {
    this.submission = submission;
  }


}
