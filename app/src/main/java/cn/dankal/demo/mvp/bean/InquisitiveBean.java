package cn.dankal.demo.mvp.bean;

import java.io.Serializable;

public class InquisitiveBean implements Serializable {

  private Meta meta;
  private Response response;

  public Meta getMeta() {
    return meta;
  }

  public Response getResponse() {
    return response;
  }
}