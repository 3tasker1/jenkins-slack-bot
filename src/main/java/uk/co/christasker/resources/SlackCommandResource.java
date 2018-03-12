package uk.co.christasker.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.dialog.DialogOpenRequest;
import com.github.seratch.jslack.api.methods.response.dialog.DialogOpenResponse;
import com.github.seratch.jslack.api.model.Option;
import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogElement;
import com.github.seratch.jslack.api.model.dialog.DialogTextElement;
import uk.co.christasker.Builds.Build;
import uk.co.christasker.Builds.BuildService;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Path("/command")
public class SlackCommandResource {

  private final Slack slack;
  private final String webhookUrl;

  public SlackCommandResource(Slack slack, String webhookUrl) {
    this.slack = slack;
    this.webhookUrl = webhookUrl;
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Timed
  public Response slackCommand(@FormParam("text") String commandText, @FormParam("trigger_id") String triggerID) {

    BuildService buildService = new BuildService();
    Optional<Build> build = buildService.getBuild(commandText);
    if(!build.isPresent()) {
      return Response.noContent().build();
    }
    DialogOpenRequest dialogOpenRequest = DialogOpenRequest.builder()
      .token("xoxb-325690688962-v4xQ4UlyvcyaR7fIJE8aqNCG")
      .triggerId(triggerID)
      .dialog(build.get().getDialog())
      .build();

    try {
      DialogOpenResponse dialogOpenResponse = slack.methods().dialogOpen(dialogOpenRequest);
      System.out.println(dialogOpenResponse.getError());
    }
    catch (IOException | SlackApiException e) {
      e.printStackTrace();
    }
    return Response.ok().build();

  }
}
