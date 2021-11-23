package com.github.mapper.api;

import java.util.Set;

public interface IMapper<T, W> {

    W toEntity(T dto, Class<?> entity);

    T toDto(W entity, Class<?> dto);

    Set<T> setToDto(Set<W> setOfEntities, Class<?> dto);

    Set<W> setToEntities(Set<T> setOfDto, Class<?> entity);

}
