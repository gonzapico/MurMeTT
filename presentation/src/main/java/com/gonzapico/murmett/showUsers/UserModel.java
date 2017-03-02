package com.gonzapico.murmett.showUsers;

/**
 * Created by gfernandez on 26/02/17.
 */

public class UserModel {

  private String name = "";
  private String urlPresentation = "";
  private String pathLastAudio = "";

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
}
