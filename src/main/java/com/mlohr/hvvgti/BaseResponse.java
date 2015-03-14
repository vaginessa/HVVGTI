package com.mlohr.hvvgti;

import org.json.JSONException;
import org.json.JSONObject;

public class BaseResponse {

    private JSONObject body;

    private ReturnCode returnCode;

    private String errorText;

    private String errorDevInfo;

    public BaseResponse(String responseBody) throws JSONException {
        this(new JSONObject(responseBody));
    }

    public BaseResponse(JSONObject responseBody) throws JSONException {
        body = responseBody;
        returnCode = ReturnCode.valueOf(body.getString("returnCode"));
        errorText = body.getString("errorText");
        errorDevInfo = body.getString("errorDevInfo");
    }

    public void assertOk() throws ApiException {
        if (getReturnCode() != ReturnCode.OK)
            throw new ApiException("Error validating response content!");
    }

    public JSONObject getBody() {
        return body;
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }

    public String getErrorText() {
        return errorText;
    }

    public String getErrorDevInfo() {
        return errorDevInfo;
    }
}
