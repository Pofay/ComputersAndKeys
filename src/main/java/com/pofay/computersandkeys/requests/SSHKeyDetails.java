package com.pofay.computersandkeys.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SSHKeyDetails {
    private String type;
    private String publicKey;
    private String comment;

    public SSHKeyDetails() {
    }
}
