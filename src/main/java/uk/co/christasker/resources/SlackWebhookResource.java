package uk.co.christasker.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import uk.co.christasker.api.Result;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Optional;

@Path("/slack")
@Produces(MediaType.APPLICATION_JSON)
public class SampleSlackResource {

  private final Slack slack;
  private final String webhookUrl;

  public SampleSlackResource(Slack slack, String webhookUrl) {
    this.slack = slack;
    this.webhookUrl = webhookUrl;
  }

  @GET
  @Timed
  public Result sendMessage(@QueryParam("content") Optional<String> content) {

    Payload payload = Payload.builder()
      .text(content.orElse("Put something in the content query param (e.g. http://localhost:8080/slack?content=I've%20done%20it%20properly%20now)"))
      .build();

    try {
      slack.send(webhookUrl, payload);
      return new Result("200","ok");
    } catch (IOException e) {
      throw new WebApplicationException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
    }

  }
}
