package com.github.mapper;

public interface IMapper<T, W> {

    W toEntity(T dto, Class<?> entity);

    T toDto(W entity, Class<?> dto);

}
