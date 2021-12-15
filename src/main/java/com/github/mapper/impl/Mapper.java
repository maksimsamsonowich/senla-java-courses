package com.github.mapper.impl;
import com.github.mapper.IMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Mapper<T, W> implements IMapper<T, W> {

    private final ModelMapper mapper;

    @Override
    @SuppressWarnings("unchecked")
    public W toEntity(T dto, Class<?> entity) {
        return Objects.isNull(dto) ? null : (W) mapper.map(dto, entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T toDto(W entity, Class<?> dto) {
        return Objects.isNull(entity) ? null : (T) mapper.map(entity, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<T> setToDto(Set<W> setOfEntities, Class<?> dto) {
        return setOfEntities.stream()
                .map((entity) -> (T) mapper.map(entity, dto))
                .collect(Collectors.toSet());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<W> setToEntities(Set<T> setOfDto, Class<?> entity) {
        return setOfDto.stream()
                .map((dto) -> (W) mapper.map(dto, entity))
                .collect(Collectors.toSet());
    }

}
