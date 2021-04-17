package com.pofay.computersandkeys.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class AddSSHKeyRequest {

    private SSHKeyDetails sshkey;

    public AddSSHKeyRequest() {
    }

}
