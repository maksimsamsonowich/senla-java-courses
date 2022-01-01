package com.github.service.impl;

import com.github.exception.artist.NoSuchArtistException;
import com.github.metamodel.Roles;
import com.github.repository.ArtistRepository;
import com.github.repository.UserRepository;
import com.github.repository.impl.EventRepository;
import com.github.repository.impl.TicketRepository;
import com.github.service.IItemsSecurityExpressions;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ItemsSecurityExpressions implements IItemsSecurityExpressions {

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final TicketRepository ticketRepository;

    private final ArtistRepository artistRepository;

    @Override
    public Boolean isUserOwnedTicket(Long ticketId, Authentication authentication) {

        if (isAdmin(authentication))
            return true;

        String ownerEmail = ticketRepository.readById(ticketId).getOwner()
                .getCredential().getEmail();

        final String currentUserEmail = authentication.getName();

        return ownerEmail.equals(currentUserEmail);
    }

    @Override
    public Boolean isUserOwnedAccount(Long userId, Authentication authentication) {

        if (isAdmin(authentication))
            return true;

        String ownerEmail = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."))
                .getCredential()
                .getEmail();

        final String currentUserEmail = authentication.getName();

        return ownerEmail.equals(currentUserEmail);
    }

    @Override
    public Boolean isUserOwnedEvent(Long eventId, Authentication authentication) {

        if (isAdmin(authentication))
            return true;

        String ownerEmail = eventRepository.readById(eventId).getEventOrganizer()
                .getCardOwner().getCredential().getEmail();

        final String currentUserEmail = authentication.getName();

        return ownerEmail.equals(currentUserEmail);
    }

    @Override
    public Boolean isUserOwnedArtistCard(Long artistId, Authentication authentication) {
        if (isAdmin(authentication))
            return true;

        String ownerEmail = artistRepository.findById(artistId)
                .orElseThrow(() -> new NoSuchArtistException("There is no such artist"))
                .getCardOwner().getCredential().getEmail();

        final String currentUserEmail = authentication.getName();

        return ownerEmail.equals(currentUserEmail);
    }

    private Boolean isAdmin(@NotNull Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals(Roles.ADMIN));
    }
}
