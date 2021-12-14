package com.github.service;

import org.springframework.security.core.Authentication;

public interface IItemsSecurityExpressions {

    Boolean isUserOwnedTicket(Long ticketId, Authentication authentication);

    Boolean isUserOwnedAccount(Long userId, Authentication authentication);

    Boolean isUserOwnedEvent(Long eventId, Authentication authentication);

}
