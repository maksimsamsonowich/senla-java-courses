package com.github.service.impl;

import com.github.dto.ConfirmationCredentialDto;
import com.github.dto.CredentialDto;
import com.github.entity.Credential;
import com.github.entity.User;
import com.github.exception.security.CredentialNotFoundException;
import com.github.exception.user.WrongPasswordException;
import com.github.mapper.IMapper;
import com.github.repository.CredentialRepository;
import com.github.repository.impl.RoleRepository;
import com.github.service.ICredentialService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CredentialService implements ICredentialService {

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final CredentialRepository credentialRepository;

    private final IMapper<CredentialDto, Credential> credentialMapper;

    @Override
    public void createCredential(CredentialDto credentialDto) {
        Credential currentCredential = credentialMapper.toEntity(credentialDto, Credential.class);

        currentCredential.setUser(new User().setCredential(currentCredential))
                .setPassword(passwordEncoder.encode(currentCredential.getPassword()))
                .setRoles(new HashSet<>(){{ add(roleRepository.findByName("USER")); }});

        credentialRepository.save(currentCredential);

        log.info("Credential successfully created.");
    }

    @Override
    @Transactional(readOnly = true)
    public CredentialDto readCredential(Long credentialId) {
        Credential currentCredential = credentialRepository.findById(credentialId)
                .orElseThrow(() -> new CredentialNotFoundException("There is no such account."));

        log.info("Credential successfully founded.");

        return credentialMapper.toDto(currentCredential, CredentialDto.class);
    }

    @Override
    public void updateCredential(Long credentialId, ConfirmationCredentialDto credentialDto) {

        Credential currentCredential = credentialRepository.findById(credentialId)
                .orElseThrow(() -> new CredentialNotFoundException("There is no such account."));

        log.info("Credential successfully founded.");

        String receivedPassword = passwordEncoder.encode(credentialDto.getPasswordConfirmation());

        if (!receivedPassword.equals(currentCredential.getPassword()))
            throw new WrongPasswordException("Password mismatch :(");

        currentCredential.setEmail(credentialDto.getUsername())
                .setPassword(passwordEncoder.encode(credentialDto.getPassword()));

        credentialRepository.save(currentCredential);

        log.info("Credential successfully updated.");
    }

    @Override
    public void deleteCredential(Long credentialId, ConfirmationCredentialDto credentialDto) {

        Credential currentCredential = credentialRepository.findById(credentialId)
                .orElseThrow(() -> new CredentialNotFoundException("There is no such account."));

        log.info("Credential successfully founded.");

        String receivedPassword = passwordEncoder.encode(credentialDto.getPasswordConfirmation());

        if (!receivedPassword.equals(currentCredential.getPassword()))
            throw new WrongPasswordException("Password mismatch :(");

        credentialRepository.deleteById(credentialId);

        log.info("Credential successfully deleted.");
    }

    @Override
    public CredentialDto findCredentialByEmail(String email) {
        return credentialMapper.toDto(credentialRepository.getCredentialByEmail(email), CredentialDto.class);
    }

}
