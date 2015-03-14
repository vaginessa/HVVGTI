package com.mlohr.hvvgti;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ApiClient {

    public static final int API_VERSION = 22;

    private HttpClient httpClient = new DefaultHttpClient();

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HMAC_SHA1;

    private String baseUri;

    private String authUser;

    private String authKey;

    /* constructors */

    public ApiClient(String baseUri, String authUser, String authKey) {
        this.baseUri = baseUri;
        this.authUser = authUser;
        this.authKey = authKey;
    }

    /* getters & setters */

    public String getBaseUri() {
        return baseUri;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    /* internal methods */

    private BaseResponse executeApiRequest(BaseRequest apiRequest) throws ApiException {
        HttpPost httpRequest = new HttpPost(getBaseUri() + apiRequest.getUriPart());
        httpRequest.setHeader(new BasicHeader("Content-Type", "application/json"));
        httpRequest.setHeader(new BasicHeader("Accept", "application/json"));
        // TODO configure user agent
        httpRequest.setHeader(new BasicHeader("geofox-auth-type", getSignatureAlgorithm().getAlgorithmString()));
        httpRequest.setHeader(new BasicHeader("geofox-auth-user", authUser));
        httpRequest.setHeader(new BasicHeader("geofox-auth-signature", generateSignature(apiRequest.getBody())));
        httpRequest.setEntity(new ByteArrayEntity(apiRequest.getBody().toString().getBytes()));
        try {
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            BasicResponseHandler responseHandler = new BasicResponseHandler();
            String responseBody = responseHandler.handleResponse(httpResponse);
            return new BaseResponse(responseBody);
        } catch (HttpResponseException e) {
            throw new ApiException("Error sending HTTP request", e.getStatusCode(), e);
        } catch (ClientProtocolException e) {
            throw new ApiException("Error sending HTTP request", e);
        } catch (JSONException e) {
            throw new ApiException("Error parsing JSON response", e);
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    private String generateSignature(JSONObject data) {
        Charset passwordEncoding = Charset.forName("UTF-8");
        String algorithm = getSignatureAlgorithm().getAlgorithmString();
        byte[] key = authKey.getBytes(passwordEncoding);
        SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(keySpec);
            byte[] signature = mac.doFinal(data.toString().getBytes());
            return new String(Base64.encodeBase64(signature));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    /* public API methods */

    public ApiInfo init() throws ApiException {
        BaseRequest apiRequest = new BaseRequest("/init", FilterType.NO_FILTER);
        return new ApiInfo(executeApiRequest(apiRequest));
    }

    public List<Station> listStations() throws ApiException {
        BaseRequest apiRequest = new BaseRequest("/listStations", FilterType.NO_FILTER);
        BaseResponse response = executeApiRequest(apiRequest);
        response.assertOk();
        LinkedList<Station> stationsResult = new LinkedList<>();
        JSONArray stationArray = response.getBody().getJSONArray("stations");
        int stationCount = stationArray.length();
        for (int i = 0; i < stationCount; i++) {
            JSONObject stationObject = stationArray.getJSONObject(i);
            if (stationObject.has("exists") && stationObject.getBoolean("exists") == false)
                continue;
            Station station = new Station(stationObject);
            stationsResult.add(station);
        }
        return stationsResult;
    }
}
