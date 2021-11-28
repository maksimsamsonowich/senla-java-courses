package com.github.service;

import com.github.dao.TicketDao;
import com.github.dto.EventDto;
import com.github.dto.TicketDto;
import com.github.dto.UserDto;
import com.github.entity.Event;
import com.github.entity.Ticket;
import com.github.entity.User;
import com.github.mapper.api.IMapper;
import com.github.service.api.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Component
@Transactional
@AllArgsConstructor
public class TicketService implements ITicketService {

    private final IMapper<TicketDto, Ticket> ticketMapper;
    private final IMapper<EventDto, Event> eventMapper;
    private final IMapper<UserDto, User> userMapper;

    private final TicketDao iTicketDao;

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        return  ticketMapper.toDto(iTicketDao.create(ticketMapper.toEntity(ticketDto, Ticket.class)), TicketDto.class);
    }

    @Override
    public TicketDto readTicket(Integer id) {
        return ticketMapper.toDto(iTicketDao.read(id), TicketDto.class);
    }

    @Override
    public TicketDto update(TicketDto ticketDto) {
        return ticketMapper.toDto(iTicketDao.update(ticketMapper.toEntity(ticketDto, Ticket.class)), TicketDto.class);
    }

    @Override
    public void deleteTicket(TicketDto ticketDto) {
        iTicketDao.delete(iTicketDao.read(ticketMapper.toEntity(ticketDto, Ticket.class).getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<TicketDto> getEventTickets(Integer id) {
        return ticketMapper.setToDto(iTicketDao.getEventTickets(id), TicketDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<TicketDto> getUserTickets(Integer id) {
        return ticketMapper.setToDto(iTicketDao.getTicketsByUser(id), Ticket.class);
    }


}
