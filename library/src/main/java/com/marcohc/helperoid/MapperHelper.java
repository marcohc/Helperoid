package com.marcohc.helperoid;

import org.modelmapper.ModelMapper;

public class MapperHelper {

    private static ModelMapper mapper;

    public static ModelMapper getMapper() {
        if (mapper == null) {
            mapper = new ModelMapper();
        }
        return mapper;
    }
}
