package com.mlohr.hvvgti;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiInfo {

    private Date beginOfService;

    private Date endOfService;

    private String id;

    private String dataId;

    private Date buildDateTime;

    private String buildText;

    private final DateFormat dateParser = new SimpleDateFormat("dd.MM.yyyy");

    ApiInfo(BaseResponse response) throws ApiException, JSONException {
        response.assertOk();
        //beginOfService = dateParser.parse(response.getBody().getString("beginOfService"));
        id = response.getBody().getString("id");
    }

    public Date getBeginOfService() {
        return beginOfService;
    }

    public Date getEndOfService() {
        return endOfService;
    }

    public String getId() {
        return id;
    }

    public String getDataId() {
        return dataId;
    }

    public Date getBuildDateTime() {
        return buildDateTime;
    }

    public String getBuildText() {
        return buildText;
    }
}
