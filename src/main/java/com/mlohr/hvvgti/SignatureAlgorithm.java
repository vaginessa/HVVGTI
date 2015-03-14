package com.mlohr.hvvgti;

public enum SignatureAlgorithm {
    HMAC_SHA1("HmacSHA1");

    private String algorithmString;

    SignatureAlgorithm(String algorithmString) {
        this.algorithmString = algorithmString;
    }

    public String getAlgorithmString() {
        return algorithmString;
    }

    @Override
    public String toString() {
        return getAlgorithmString();
    }
}
