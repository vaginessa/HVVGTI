package com.mlohr.hvvgti;

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
