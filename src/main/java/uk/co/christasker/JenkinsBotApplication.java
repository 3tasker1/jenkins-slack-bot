package uk.co.christasker;

import com.codahale.metrics.MetricRegistry;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.common.http.SlackHttpClient;
import com.raskasa.metrics.okhttp.InstrumentedOkHttpClients;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import okhttp3.OkHttpClient;
import uk.co.christasker.resources.SlackCommandResource;
import uk.co.christasker.resources.SlackDialogSubmitted;
import uk.co.christasker.resources.SlackWebhookResource;

public class JenkinsBotApplication extends Application<JenkinsBotConfiguration> {

  @Override
  public void run(JenkinsBotConfiguration slackTestConfiguration,
                  Environment environment) {


    //instrument the okhttp client
    MetricRegistry registry = environment.metrics();
    OkHttpClient rawClient = new OkHttpClient();
    OkHttpClient client = InstrumentedOkHttpClients.create(registry,rawClient);

    Slack slack = Slack.getInstance(new SlackHttpClient(client));

    SlackWebhookResource slackWebhookResource = new SlackWebhookResource();
    SlackCommandResource slackCommandResource = new SlackCommandResource(slack, slackTestConfiguration.getSlackWebhookUrl());
    SlackDialogSubmitted slackDialogSubmitted = new SlackDialogSubmitted(client, slackTestConfiguration.getJenkinsUrl());
    environment.jersey().register(slackWebhookResource);
    environment.jersey().register(slackCommandResource);
    environment.jersey().register(slackDialogSubmitted);

  }

  @Override
  public String getName() {
    return "jenkins-bot";
  }

  @Override
  public void initialize(Bootstrap<JenkinsBotConfiguration> bootstrap) {

  }


  public static void main(String[] args) throws Exception {
    new JenkinsBotApplication().run(args);
  }
}
