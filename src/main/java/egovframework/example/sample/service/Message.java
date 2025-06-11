package egovframework.example.sample.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Message {
    String cctvId;
    String cctvLocname;
    String event;
    Message(String cctvID, String event){
        this.cctvId = cctvID;
        this.cctvLocname = event;
    };

    public String getCctvId() {
        return cctvId;
    }

    public void setCctvId(String cctvId) {
        this.cctvId = cctvId;
    }

    public String getCctvLocname() {
        return cctvLocname;
    }

    public void setCctvLocname(String cctvLocname) {
        this.cctvLocname = cctvLocname;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
