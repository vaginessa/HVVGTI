package com.mlohr.hvvgti;

public enum FilterType {
    NO_FILTER("NO_FILTER"),
    HVV_LISTED("HVV_LISTED");

    String type;

    FilterType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
