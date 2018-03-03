This is what I've put together in advance of my slack integration hack and in the spirit of getting people started quickly I'm sharing it.

It's a dropwizard server that can send messages to the configured webhook

Libraries I've used/tutorials I've followed:

* Dropwizard basics http://www.dropwizard.io/1.2.2/docs/getting-started.html
* Jslack, slack bindings https://github.com/seratch/jslack
* metrics-okhttp to integrate okhttp (jslack's httpclient of choice) with DW's metrics

# Set up

1st register your own slack workspace (don't pollute the Fivium one :senpai:)

2nd create and activate an app for that workspace [it's easy](https://api.slack.com/) and activate a webhook (you'll need the url)

You need to set up a yaml file for configuration.
 
#### example.yaml
```yaml
slackWebhookUrl: https://hooks.slack.com/services/xxx/yyy/zzz
``` 

# Building 

It's a maven project so you can just go `mvn package`

# Running
Assuming you've got example.yaml in the project root, execute `java -jar target/slack-test-1.0-SNAPSHOT.jar server slack-starter.yaml`

Go to http://localhost:8080/slack?content=Hi%20Mom!

If everything's working you should get "Hi Mom" in your chosen 

# Next steps
To add integrations etc. you'll probably need to set up ngrok or similar to port through messages from slack to your machine (https://api.slack.com/tutorials/tunneling-with-ngrok)

# Support
[Here you go](https://giphy.com/search/support)
