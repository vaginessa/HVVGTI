package com.mlohr.hvvgti;

import junit.framework.TestCase;

import java.util.Date;

public class ApiInfoTest extends TestCase {

    public void testValid() throws ApiException {
        ApiInfo apiInfo = new ApiInfo(new BaseResponse("{\n" +
                "\"returnCode\":\"OK\",\n" +
                "\"beginOfService\":\"12.03.2013\",\n" +
                "\"endOfService\":\"15.12.2013\",\n" +
                "\"id\":\"00.00.00.00.00\",\n" +
                "\"dataId\":\"25.28.01\",\n" +
                "\"buildDate\":\"12.03.2013\",\n" +
                "\"buildTime\":\"10:27:58\",\n" +
                "\"buildText\":\"Regelfahrplan 2013\"\n" +
                "}"));
        assertEquals(new Date(1363042800000L), apiInfo.getBeginOfService());
        assertEquals(new Date(1387062000000L), apiInfo.getEndOfService());
        assertEquals("00.00.00.00.00", apiInfo.getId());
        assertEquals("25.28.01", apiInfo.getDataId());
        assertEquals(new Date(1363080478000L), apiInfo.getBuildDateTime());
        assertEquals("Regelfahrplan 2013", apiInfo.getBuildText());
    }
}
