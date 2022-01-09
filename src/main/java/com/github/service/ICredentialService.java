package com.github.service;

import com.github.dto.ConfirmationCredentialDto;
import com.github.dto.CredentialDto;

public interface ICredentialService {

    void createCredential(CredentialDto credentialDto);

    CredentialDto readCredential(Long id);

    void updateCredential(Long credentialId, ConfirmationCredentialDto credentialDto);

    void deleteCredential(Long id, ConfirmationCredentialDto credentialDto);

    CredentialDto findCredentialByEmail(String email);
}
