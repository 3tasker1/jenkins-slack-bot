package uk.co.christasker;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class JenkinsBotConfiguration extends Configuration {


  @NotEmpty
  private String slackWebhookUrl;

  @NotEmpty
  private String jenkinsUrl;

  @JsonProperty
  public String getJenkinsUrl() {
    return jenkinsUrl;
  }

  @JsonProperty
  public void setJenkinsUrl(String jenkinsUrl) {
    this.jenkinsUrl = jenkinsUrl;
  }

  @JsonProperty
  public String getSlackWebhookUrl() {
    return slackWebhookUrl;
  }

  @JsonProperty
  public void setSlackWebhookUrl(String slackWebhookUrl) {
    this.slackWebhookUrl = slackWebhookUrl;
  }
}
