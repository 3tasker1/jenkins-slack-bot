package uk.co.aledlewis;

import com.codahale.metrics.MetricRegistry;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.common.http.SlackHttpClient;
import com.raskasa.metrics.okhttp.InstrumentedOkHttpClients;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import okhttp3.OkHttpClient;
import uk.co.aledlewis.resources.SampleSlackResource;

public class SlackTestApplication extends Application<SlackTestConfiguration> {

  @Override
  public void run(SlackTestConfiguration slackTestConfiguration,
                  Environment environment) {


    //instrument the okhttp client
    MetricRegistry registry = environment.metrics();
    OkHttpClient rawClient = new OkHttpClient();
    OkHttpClient client = InstrumentedOkHttpClients.create(registry,rawClient);

    Slack slack = Slack.getInstance(new SlackHttpClient(client));

    SampleSlackResource sampleSlackResource = new SampleSlackResource(slack, slackTestConfiguration.getSlackWebhookUrl());
    environment.jersey().register(sampleSlackResource);

  }

  @Override
  public String getName() {
    return "hello-world";
  }

  @Override
  public void initialize(Bootstrap<SlackTestConfiguration> bootstrap) {

  }


  public static void main(String[] args) throws Exception {
    new SlackTestApplication().run(args);
  }
}
