package com.tarasiuk.votehub.service.impl;

import com.tarasiuk.votehub.service.CitizenService;
import com.tarasiuk.votehub.service.KeyGenerationService;
import com.tarasiuk.votehub.util.KeyGenerationUtil;
import com.tarasiuk.votehub.util.data.RSAKey;
import com.tarasiuk.votehub.util.data.RSAKeyPair;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DefaultKeyGenerationService implements KeyGenerationService {

    @Value("${rsa.key.bitsize:16}")
    private Integer bitsize;
    private final CitizenService citizenService;

    @Transactional
    @Override
    public RSAKeyPair generateAndRegister(Integer passportId) {
        RSAKeyPair rsaKeyPair = KeyGenerationUtil.generateRSAKeyPair(bitsize);
        RSAKey publicKey = rsaKeyPair.publicKey();

        citizenService.savePublicKeyFor(publicKey.key(), publicKey.n(), passportId);

        return rsaKeyPair;
    }

}
