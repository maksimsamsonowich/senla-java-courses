package com.github.service;

import com.github.dto.CredentialDto;

public interface ICredentialService {

    CredentialDto createCredential(CredentialDto credentialDto);

    CredentialDto readCredential(Integer id);

    CredentialDto updateCredential(Integer id, CredentialDto credentialDto);

    void deleteCredential(Integer id);

}
