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
        errorText = body.optString("errorText");
        errorDevInfo = body.optString("errorDevInfo");
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
