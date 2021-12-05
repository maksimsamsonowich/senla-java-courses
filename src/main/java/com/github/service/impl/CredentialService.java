package com.github.service.impl;

import com.github.dto.CredentialDto;
import com.github.entity.Credential;
import com.github.entity.User;
import com.github.mapper.IMapper;
import com.github.repository.impl.CredentialRepository;
import com.github.repository.impl.RoleRepository;
import com.github.service.ICredentialService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@Transactional
@AllArgsConstructor
public class CredentialService implements ICredentialService {

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final CredentialRepository credentialRepository;

    private final IMapper<CredentialDto, Credential> credentialMapper;

    @Override
    public CredentialDto createCredential(CredentialDto credentialDto) {
        Credential currentCredential = credentialMapper.toEntity(credentialDto, Credential.class);

        currentCredential.setUser(new User());
        currentCredential.setPassword(passwordEncoder.encode(currentCredential.getPassword()));
        currentCredential.setRoles(new HashSet<>(){{ add(roleRepository.findByName("USER")); }});

        return credentialMapper.toDto(credentialRepository.create(currentCredential), CredentialDto.class);
    }

    @Override
    public CredentialDto readCredential(Integer id) {
        return credentialMapper.toDto(credentialRepository.read(id), CredentialDto.class);
    }

    @Override
    public CredentialDto updateCredential(Integer id, CredentialDto credentialDto) {
        credentialDto.setId(id);
        return credentialMapper.toDto(credentialRepository.update(credentialMapper.toEntity(credentialDto, Credential.class)), CredentialDto.class);
    }

    @Override
    public void deleteCredential(Integer id) {
        credentialRepository.delete(credentialRepository.read(id));
    }

    public CredentialDto findByEmail(String email) {
        return credentialMapper.toDto(credentialRepository.getCredentialByEmail(email), CredentialDto.class);
    }

}
