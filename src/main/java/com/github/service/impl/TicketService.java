package com.github.service.impl;

import com.github.dto.TicketDto;
import com.github.entity.Ticket;
import com.github.mapper.IMapper;
import com.github.repository.impl.EventRepository;
import com.github.repository.impl.TicketRepository;
import com.github.repository.impl.UserRepository;
import com.github.service.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class TicketService implements ITicketService {

    private final IMapper<TicketDto, Ticket> ticketMapper;

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    @Override
    public TicketDto createTicket(String email, TicketDto ticketDto) {
        Ticket currentTicket = ticketMapper.toEntity(ticketDto, Ticket.class)
                .setOwner(userRepository.findByUsername(email))
                .setEventHolding(eventRepository.read(ticketDto.getEventHolding().getId()));

        ticketRepository.create(currentTicket);

        return ticketMapper.toDto(currentTicket, TicketDto.class);
    }

    @Override
    public TicketDto readTicket(Boolean hasAdminRole, String requestEmail, Long ticketId) {
        Ticket currentTicket = ticketRepository.read(ticketId);

        String ticketOwnerEmail = currentTicket.getOwner().getCredential().getEmail();

        if (hasAdminRole || ticketOwnerEmail.equals(requestEmail) )
            return ticketMapper.toDto(ticketRepository.read(ticketId), TicketDto.class);
        else
            throw new AccessDeniedException("Forbidden");
    }

    @Override
    public TicketDto update(Long id, TicketDto ticketDto) {
        ticketDto.setId(id);
        return ticketMapper.toDto(ticketRepository.update(ticketMapper.toEntity(ticketDto, Ticket.class)), TicketDto.class);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.delete(ticketRepository.read(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<TicketDto> getEventTickets(Long id) {
        return ticketMapper.setToDto(ticketRepository.getEventTickets(id), TicketDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<TicketDto> getUserTickets(Long id) {
        return ticketMapper.setToDto(ticketRepository.getTicketsByUser(id), Ticket.class);
    }


}
