package uk.co.christasker.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Message {

  private String channel;
  private String user;
  private String text;
  public final String type = "message";
  private int id;


  public Message() {
  }

  public static Message makeMessage(String channel, String text) {
    Message message = new Message();
    message.setChannel(channel);
    message.setText(text);
    message.setId((int)(Math.round(Math.random() * 1000)));
    return message;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getId() {
    return id;
  }


  public void setId(int id) {
    this.id = id;
  }

  public String getChannel() {
    return channel;
  }

  public String getUser() {
    return user;
  }

  public String getText() {
    return text;
  }




}
