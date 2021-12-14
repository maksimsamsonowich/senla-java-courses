package com.github.service.impl;

import com.github.metamodel.Roles;
import com.github.repository.impl.EventRepository;
import com.github.repository.impl.TicketRepository;
import com.github.repository.impl.UserRepository;
import com.github.service.IItemsSecurityExpressions;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ItemsSecurityExpressions implements IItemsSecurityExpressions {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    @Override
    public Boolean isUserOwnedTicket(Long ticketId, Authentication authentication) {
        boolean hasAdminRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals(Roles.ADMIN));

        if (hasAdminRole)
            return true;

        String ownerEmail = ticketRepository.read(ticketId).getOwner()
                .getCredential().getEmail();

        final String currentUserEmail = authentication.getName();

        return ownerEmail.equals(currentUserEmail);
    }

    @Override
    public Boolean isUserOwnedAccount(Long userId, Authentication authentication) {
        boolean hasAdminRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals(Roles.ADMIN));

        if (hasAdminRole)
            return true;

        String ownerEmail = userRepository.read(userId).getCredential().getEmail();

        final String currentUserEmail = authentication.getName();

        return ownerEmail.equals(currentUserEmail);
    }

    @Override
    public Boolean isUserOwnedEvent(Long eventId, Authentication authentication) {

        boolean hasAdminRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals(Roles.ADMIN));

        if (hasAdminRole)
            return true;

        String ownerEmail = eventRepository.read(eventId).getEventOrganizer()
                .getCardOwner().getCredential().getEmail();

        final String currentUserEmail = authentication.getName();

        return ownerEmail.equals(currentUserEmail);
    }

}
