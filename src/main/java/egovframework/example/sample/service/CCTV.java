package egovframework.example.sample.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CCTV{
    private String cctvID;

    private String cctvName;

    private float powerCnmp;

    private String locId;

    public String getCctvID() {
        return cctvID;
    }

    public void setCctvID(String cctvID) {
        this.cctvID = cctvID;
    }

    public String getCctvName() {
        return cctvName;
    }

    public void setCctvName(String cctvName) {
        this.cctvName = cctvName;
    }

    public float getPowerCnmp() {
        return powerCnmp;
    }

    public void setPowerCnmp(float powerCnmp) {
        this.powerCnmp = powerCnmp;
    }

    public String getLocId() {
        return locId;
    }

    public void setLocId(String locId) {
        this.locId = locId;
    }
}
