package com.gonzapico.murmett.showUsers;

/**
 * Created by gfernandez on 26/02/17.
 */

public class UserModel {

  private String id = "";
  private String alias = "";
  private String urlPresentation = "";
  private String pathLastAudio = "";

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getUrlPresentation() {
    return urlPresentation;
  }

  public void setUrlPresentation(String urlPresentation) {
    this.urlPresentation = urlPresentation;
  }

  public String getPathLastAudio() {
    return pathLastAudio;
  }

  public void setPathLastAudio(String pathLastAudio) {
    this.pathLastAudio = pathLastAudio;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
