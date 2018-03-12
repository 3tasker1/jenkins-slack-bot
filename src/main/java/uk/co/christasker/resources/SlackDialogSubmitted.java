package uk.co.christasker.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.OkHttpClient;
import uk.co.christasker.data.DialogResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@Path("/dialog-submitted")
@Produces(MediaType.APPLICATION_JSON)
public class SlackDialogSubmitted {

  private OkHttpClient httpClient;
  private String jenkinsUrl;

  public SlackDialogSubmitted(OkHttpClient httpClient, String jenkinsUrl) {
    this.httpClient = httpClient;
    this.jenkinsUrl = jenkinsUrl;
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Timed
  public Response dialogSubmitted(@FormParam("payload") String payload) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      DialogResponse dialogResponse = objectMapper.readValue(payload, DialogResponse.class);
      String url = jenkinsUrl + "/" + dialogResponse.getCallback_id() + "/buildWithParameters?token=fivium12!";

      HttpResponse<JsonNode> crumbResponse = Unirest
        .get(jenkinsUrl + "/crumbIssuer/api/json")
        .basicAuth("###JENKSINS_USERNAME###", "###JENKSINS_PASSWORD###")
        .asJson();

      String crumb = crumbResponse.getBody().getObject().getString("crumb");

      HttpResponse<String> jsonNodeHttpResponse = Unirest
        .post(url)
        .header("Jenkins-Crumb", crumb)
        .basicAuth("###JENKSINS_USERNAME###", "###JENKSINS_PASSWORD###")
        .fields((Map<String, Object>)dialogResponse.getSubmission())
        .asString();

      System.out.println("Responded!");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (UnirestException e) {
      e.printStackTrace();
      return Response.serverError().build();
    }
    return Response.ok().build();

  }
}
