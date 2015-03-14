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

    }
}
