package com.pofay.computersandkeys.RequestBuilders;

import org.json.JSONObject;

public class AuthorizedKeyRequestBuilder {
    private String publicKey;
    private String comment;
    private String type;

    public static AuthorizedKeyRequestBuilder create() {
        return new AuthorizedKeyRequestBuilder();
    }

    private AuthorizedKeyRequestBuilder() {
        this.publicKey = "";
        this.comment = "";
        this.type = "";
    }

    public AuthorizedKeyRequestBuilder withPublicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public AuthorizedKeyRequestBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public AuthorizedKeyRequestBuilder withKeyType(String type) {
        this.type = type;
        return this;
    }

    public JSONObject build() {
        JSONObject subPayload = new JSONObject();
        subPayload.put("type", type);
        subPayload.put("public", publicKey);
        subPayload.put("comment", comment);
        JSONObject payload = new JSONObject();
        payload.put("ssh-key", subPayload);
        return payload;
    }

}
