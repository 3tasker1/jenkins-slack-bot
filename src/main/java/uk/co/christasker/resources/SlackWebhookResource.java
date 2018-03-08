package uk.co.christasker.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/slack")
@Produces(MediaType.APPLICATION_JSON)
public class SlackWebhookResource {

  private final Slack slack;
  private final String webhookUrl;

  public SlackWebhookResource(Slack slack, String webhookUrl) {
    this.slack = slack;
    this.webhookUrl = webhookUrl;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Timed
  public Response slackEndPoint(String bodyData) {

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      JsonNode bodyJSON = objectMapper.readTree(bodyData);
      if(bodyJSON.has("challenge")) {
        //Need to return the challenge as it must be a new connection from slack
        return Response.ok(bodyJSON, MediaType.APPLICATION_JSON).build();
      } else {
        //No Challenge so need to handle it as an event
        slack.send(
          webhookUrl
          , Payload.builder().text("Watch this space!").build()
        );
        return Response.ok().build();
      }
    } catch (IOException e) {
      return Response.serverError().build();
    }


  }
}
