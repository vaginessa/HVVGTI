package com.mlohr.hvvgti;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
        SecretKeySpec keySpec = new SecretKeySpec(authKey.getBytes(passwordEncoding), algorithm);
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(keySpec);
            byte[] signature = mac.doFinal(data.toString().getBytes());
            return Base64.encodeBase64String(signature);
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
}
