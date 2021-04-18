package com.pofay.computersandkeys.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

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
