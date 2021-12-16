package com.github.service.impl;

import com.github.dto.TicketDto;
import com.github.entity.Ticket;
import com.github.mapper.IMapper;
import com.github.repository.impl.EventRepository;
import com.github.repository.impl.TicketRepository;
import com.github.repository.impl.UserRepository;
import com.github.service.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class TicketService implements ITicketService {

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final TicketRepository ticketRepository;

    private final IMapper<TicketDto, Ticket> ticketMapper;

    @Override
    public TicketDto createTicket(String email, TicketDto ticketDto) {
        Ticket currentTicket = ticketMapper.toEntity(ticketDto, Ticket.class)
                .setOwner(userRepository.findByUsername(email))
                .setEventHolding(eventRepository.readById(ticketDto.getEventHolding().getId()));

        currentTicket = ticketRepository.create(currentTicket);

        return ticketMapper.toDto(currentTicket, TicketDto.class);
    }

    @Override
    public TicketDto readTicket(Long ticketId) {
        return ticketMapper.toDto(ticketRepository.readById(ticketId), TicketDto.class);
    }

    @Override
    public TicketDto update(Long id, TicketDto ticketDto) {
        Ticket currentTicket = ticketMapper.toEntity(ticketDto, Ticket.class);

        ticketRepository.update(currentTicket);

        return ticketMapper.toDto(currentTicket, TicketDto.class);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
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
