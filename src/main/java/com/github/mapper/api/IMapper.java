package com.github.mapper.api;

public interface IMapper<T, W> {

    W toEntity(T dto, Class<?> entity);

    T toDto(W entity, Class<?> dto);

}
