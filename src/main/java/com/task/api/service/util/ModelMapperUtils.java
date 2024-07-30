package com.task.api.service.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperUtils {

    private static ModelMapper modelMapperUtils = null;

    public ModelMapperUtils(ModelMapper mapper)
    {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapperUtils = mapper;
    }

    public static <T, Z> T map(Z model, Class<T> dtoClass)
    {
        return modelMapperUtils.map(model, dtoClass);
    }

}

