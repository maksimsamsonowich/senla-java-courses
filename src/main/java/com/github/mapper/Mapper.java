package com.github.mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

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

}
