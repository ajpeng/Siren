package com.pengal.Siren.Request;

import com.sun.istack.NotNull;

public class RedirectCreationRequest {
    @NotNull
    private String alias;
    @NotNull
    private String url;

    public RedirectCreationRequest() {
    }

    public RedirectCreationRequest(final String alias, final String url) {
        this.alias = alias;
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "RedirectCreationRequest{" +
                "alias='" + alias + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
