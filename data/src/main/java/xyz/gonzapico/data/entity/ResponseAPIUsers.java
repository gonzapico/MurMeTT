
package xyz.gonzapico.data.entity;

import java.util.List;

public class ResponseAPIUsers {

    private Status status;
    private List<Contact> contacts = null;
    private UserMain userMain;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public UserMain getUserMain() {
        return userMain;
    }

    public void setUserMain(UserMain userMain) {
        this.userMain = userMain;
    }

}
