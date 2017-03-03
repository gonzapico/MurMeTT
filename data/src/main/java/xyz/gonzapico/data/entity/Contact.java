
package xyz.gonzapico.data.entity;

import java.util.List;

public class Contact {

    private String idConversation;
    private String idUser;
    private Integer state;
    private Integer gender;
    private String alias;
    private String presentation;
    private Integer newContact;
    private Integer callsLost;
    private String date;
    private Integer rated;
    private List<Like> likes = null;
    private String country;
    private Integer offline;
    private Integer canSendAudioMessage;

    public String getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(String idConversation) {
        this.idConversation = idConversation;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public Integer getNewContact() {
        return newContact;
    }

    public void setNewContact(Integer newContact) {
        this.newContact = newContact;
    }

    public Integer getCallsLost() {
        return callsLost;
    }

    public void setCallsLost(Integer callsLost) {
        this.callsLost = callsLost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getRated() {
        return rated;
    }

    public void setRated(Integer rated) {
        this.rated = rated;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getOffline() {
        return offline;
    }

    public void setOffline(Integer offline) {
        this.offline = offline;
    }

    public Integer getCanSendAudioMessage() {
        return canSendAudioMessage;
    }

    public void setCanSendAudioMessage(Integer canSendAudioMessage) {
        this.canSendAudioMessage = canSendAudioMessage;
    }

}
