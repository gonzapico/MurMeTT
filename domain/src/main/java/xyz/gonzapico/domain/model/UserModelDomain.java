package xyz.gonzapico.domain.model;

/**
 * Created by gfernandez on 25/02/17.
 */

public class UserModelDomain {

  private String idUser = "";
  private String alias = "";
  private String presentation = "";

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getIdUser() {
    return idUser;
  }

  public void setIdUser(String idUser) {
    this.idUser = idUser;
  }

  public String getPresentation() {
    return presentation;
  }

  public void setPresentation(String presentation) {
    this.presentation = presentation;
  }
}
