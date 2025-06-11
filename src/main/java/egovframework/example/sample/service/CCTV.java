package egovframework.example.sample.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CCTV{
    private String id;

    private String name;

    private float cnmp;

    private String loc_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCnmp() {
        return cnmp;
    }

    public void setCnmp(float cnmp) {
        this.cnmp = cnmp;
    }

    public String getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(String loc_id) {
        this.loc_id = loc_id;
    }
}
