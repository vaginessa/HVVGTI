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

public class ListStationsRequest extends BaseRequest {

    private String dataReleaseId = null;

    //private Set<ModificationType> modificationTypes;

    private CoordinateType coordinateType = CoordinateType.getDefault();

    private boolean filterEquivalent = true;

    public ListStationsRequest(String uriPart) {
        super(uriPart);
    }

    @Override
    public JSONObject getBody() {
        JSONObject body = super.getBody();
        if (dataReleaseId != null) body.put("dataReleaseID", dataReleaseId);
        // TODO add modificationTypes
        body.put("coordinateType", coordinateType.toString());
        body.put("filterEquivalent", filterEquivalent);
        return body;
    }
}
