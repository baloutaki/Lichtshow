package be.ehb.lichtshow.model;

import com.google.android.gms.maps.model.LatLng;

public class LightFestival {

    private LatLng coordinate;
    private String name, info;


    public LightFestival(LatLng coordinate, String name, String info) {
        this.coordinate = coordinate;
        this.name = name;
        this.info = info;
    }

    public LatLng getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LatLng coordinate) {
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
