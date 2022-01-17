package com.github.service.impl;

import com.github.dto.ArtistDto;
import com.github.entity.Artist;
import com.github.entity.Role;
import com.github.entity.User;
import com.github.exception.artist.NoSuchArtistException;
import com.github.mapper.IMapper;
import com.github.repository.ArtistRepository;
import com.github.repository.CredentialRepository;
import com.github.repository.impl.RoleRepository;
import com.github.repository.impl.UserRepository;
import com.github.service.IArtistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class ArtistService implements IArtistService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final ArtistRepository artistRepository;

    private final IMapper<ArtistDto, Artist> artistMapper;

    private final CredentialRepository credentialRepository;

    @Override
    public void createArtist(Long userId, ArtistDto artistDto) {

        Role artistRole = roleRepository.findByName("ARTIST");

        User currentUser = userRepository.readById(userId);

        Artist currentArtistEntity = artistMapper.toEntity(artistDto, Artist.class);

        if (currentUser.getCredential().getRoles().stream()
                .noneMatch(role -> role.getRole().equals(artistRole.getRole()))) {

            currentUser.getCredential().getRoles().add(artistRole);
            //credentialRepository.save(currentUser.getCredential());
        }
        currentArtistEntity.setCardOwner(currentUser);

        artistRepository.save(currentArtistEntity);

    }

    @Override
    @Transactional(readOnly = true)
    public ArtistDto getArtist(Long artistId) {

        Artist currentArtist = artistRepository.findById(artistId)
                .orElseThrow(() -> new NoSuchArtistException("There is no such artist"));

        return artistMapper.toDto(currentArtist, ArtistDto.class);
    }

    @Override
    public ArtistDto updateArtist(Long artistId, ArtistDto artistDto) {

        artistDto.setId(artistId);

        Artist currentArtist = artistMapper.toEntity(artistDto, Artist.class);

        return artistMapper.toDto(artistRepository.save(currentArtist), ArtistDto.class);
    }

    @Override
    public void deleteArtist(Long artistId) {
        artistRepository.deleteById(artistId);
    }

    @Override
    @Transactional(readOnly = true)
    public ArtistDto getArtistByEventId(Long eventId) {
        Artist currentArtist = artistRepository.getArtistByEventsId(eventId);

        if (Objects.isNull(currentArtist))
            throw new NoSuchArtistException("There is no such artist");
        else
            return artistMapper.toDto(currentArtist, ArtistDto.class);
    }

}
