package com.mlohr.hvvgti;

/*
HVVGTI Java API
Copyright (C) 2015  Matthias Lohr <matthias@lohr.me>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License version 3 as
published by the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
