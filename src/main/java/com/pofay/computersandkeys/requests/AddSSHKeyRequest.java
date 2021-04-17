package com.pofay.computersandkeys.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class AddSSHKeyRequest {

    @JsonProperty("ssh-key")
    private SshKey sshkey;

    public AddSSHKeyRequest() {
    }

    @Data
    @EqualsAndHashCode
    public static class SshKey {
        private String type;
        @JsonProperty("public")
        private String publicKey;
        private String comment;

        public SshKey() {
        }

    }
}
