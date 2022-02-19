package com.ProgressSoft.DataWarehouse;

public enum Endpoint {

    CREATE_DEAL_ENDPOINT("/deal/v1/api/addDeal");

    private String uri;

    Endpoint(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
