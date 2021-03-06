package br.com.cursojava.petshop.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.stream.Collectors;

public class MapperEntidadeDTO<U, T> {
    private final ObjectMapper objectMapper;

    private final Class<U> entityClass;
    private final Class<T> dtoClass;

    public MapperEntidadeDTO(Class<U> entityClass, Class<T> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        objectMapper = new ObjectMapper()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public List<U> toEntity(List<T> ts) {
        return ts.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public U toEntity(T t) {
        return objectMapper.convertValue(t, entityClass);
    }

    public List<T> toDto(List<U> us) {
        return us.stream().map(this::toDto).collect(Collectors.toList());
    }

    public T toDto(U u) {
        return objectMapper.convertValue(u, dtoClass);
    }
}