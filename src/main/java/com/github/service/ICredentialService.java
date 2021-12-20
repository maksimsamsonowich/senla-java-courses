package com.github.service;

import com.github.dto.CredentialDto;

public interface ICredentialService {

    void createCredential(CredentialDto credentialDto);

    CredentialDto readCredential(Long id);

    CredentialDto updateCredential(Long id, CredentialDto credentialDto);

    void deleteCredential(Long id);

    CredentialDto findCredentialByEmail(String email);
}
