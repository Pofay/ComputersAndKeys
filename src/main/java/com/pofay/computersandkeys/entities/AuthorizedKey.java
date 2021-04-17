package com.pofay.computersandkeys.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ssh_keys")
public class AuthorizedKey {

    @Id
    @Getter
    private String publicKey;
    @Getter
    private String comment;
    @Getter
    private String type;

    public AuthorizedKey() {
    }

    public AuthorizedKey(String publicKey, String comment, String type) {
        this.publicKey = publicKey;
        this.comment = comment;
        this.type = type;
    }
}
