package com.mlohr.hvvgti;

import junit.framework.TestCase;

import org.apache.http.HttpStatus;

public class ApiClientTest extends TestCase {

    public void testUnauthorized() {
        ApiClient apiClient = new ApiClient("http://api-test.geofox.de/hvvgti/restapp", "foo", "bar");
        try {
            ApiInfo apiInfo = apiClient.init();
            fail("Should throw exception!");
        } catch (ApiException e) {
            assertNotNull(e.getHttpStatusCode());
            assertEquals(HttpStatus.SC_UNAUTHORIZED, e.getHttpStatusCode().intValue());
        }
    }
}
