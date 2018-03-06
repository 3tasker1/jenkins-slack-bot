package uk.co.aledlewis;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class SlackTestConfiguration extends Configuration {


  @NotEmpty
  private String slackWebhookUrl;


  @JsonProperty
  public String getSlackWebhookUrl() {
    return slackWebhookUrl;
  }

  @JsonProperty
  public void setSlackWebhookUrl(String slackWebhookUrl) {
    this.slackWebhookUrl = slackWebhookUrl;
  }
}
