package com.mlohr.hvvgti;

import org.json.JSONObject;

public class Station {

    private String id;

    private String name;

    private String city;

    private String combinedName;

    private LonLat position;

    public Station(JSONObject json) {
        id = json.getString("id");
        name = json.getString("name");
        city = json.getString("city");
        combinedName = json.optString("combinedName");
        JSONObject pos = json.getJSONObject("coordinate");
        position = new LonLat(pos.getDouble("x"), pos.getDouble("y"));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCombinedName() {
        return combinedName;
    }

    public LonLat getPosition() {
        return position;
    }
}
