package com.mlohr.hvvgti;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiInfo {

    private Date beginOfService;

    private Date endOfService;

    private String id;

    private String dataId;

    private Date buildDateTime;

    private String buildText;

    private final SimpleDateFormat dateParser = new SimpleDateFormat("dd.MM.yyyy");

    private final SimpleDateFormat dateTimeParser = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    ApiInfo(BaseResponse response) throws ApiException, JSONException {
        // test if result is OK
        response.assertOk();

        // read "beginOfService"
        try {
            beginOfService = dateParser.parse(response.getBody().getString("beginOfService"));
        } catch (ParseException e) {
            throw new ApiException("Error parsing InitResponse field beginOfService", e);
        }
        // read "endOfService"
        try {
            endOfService = dateParser.parse(response.getBody().getString("endOfService"));
        } catch (ParseException e) {
            throw new ApiException("Error parsing InitResponse field endOfService", e);
        }
        // read "id"
        id = response.getBody().getString("id");
        // read "dataId"
        dataId = response.getBody().getString("dataId");
        // read build "buildDate"/"buildTime"
        try {
            String dateTimeString = response.getBody().getString("buildDate") + " " + response.getBody().getString("buildTime");
            buildDateTime = dateTimeParser.parse(dateTimeString);
        } catch (ParseException e) {
            throw new ApiException("Error parsing InitResponse field buildDate/buildTime", e);
        }
        // read "buildText"
        buildText = response.getBody().getString("buildText");
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
