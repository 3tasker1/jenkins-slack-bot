package uk.co.christasker.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/slack")
@Produces(MediaType.APPLICATION_JSON)
public class SlackWebhookResource {

  public SlackWebhookResource() {
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Timed
  public Response slackEndPoint(JsonNode bodyData) {

    if(bodyData.has("challenge")) {
        //Need to return the challenge as it must be a new connection from slack
        return Response.ok(bodyData, MediaType.APPLICATION_JSON).build();
      } else {
        return Response.noContent().build();
      }


  }
}
