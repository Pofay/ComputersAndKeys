package com.pofay.computersandkeys.services;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Service;

@Service
public class KeyVerifierService {

    public boolean verifyKeyForKeyType(String pubKey, String keyType) {
        byte[] decodedKey = Base64.decode(pubKey);
        String actualKey = new String(decodedKey);

        return actualKey.contains(keyType);
    }

}
