package egovframework.example.sample.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CCTVMessage {
    String cctvId;
    String cctvLocname;
    String event;
    CCTVMessage(String cctvId, String event){
        this.cctvId = cctvId;
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
