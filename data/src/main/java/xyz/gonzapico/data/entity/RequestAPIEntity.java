package xyz.gonzapico.data.entity;

/**
 * Created by gfernandez on 1/03/17.
 */

public class RequestAPIEntity {

  private String keyAccess;
  private String language;
  private String pushToken;
  private String contentType;
  private String idUser;

  public String getKeyAccess() {
    return keyAccess;
  }

  public void setKeyAccess(String keyAccess) {
    this.keyAccess = keyAccess;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getPushToken() {
    return pushToken;
  }

  public void setPushToken(String pushToken) {
    this.pushToken = pushToken;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getIdUser() {
    return idUser;
  }

  public void setIdUser(String idUser) {
    this.idUser = idUser;
  }
}
