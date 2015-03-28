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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class BaseRequest {

    private String uriPart;

    private Locale locale;

    private FilterType filterType;

    public BaseRequest(String uriPart, FilterType filterType) {
        this(uriPart, filterType, Locale.getDefault());
    }

    public BaseRequest(String uriPart, FilterType filterType, Locale locale) {
        this.uriPart = uriPart;
        this.filterType = filterType;
        this.locale = locale;
    }

    public String getUriPart() {
        return uriPart;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public JSONObject getBody() {
        try {
            JSONObject body = new JSONObject();
            body.put("version", ApiClient.API_VERSION);
            body.put("language", getLocale().getLanguage());
            body.put("filterType", getFilterType().toString());
            return body;
        } catch (JSONException e) {
            return null;
        }
    }
}
