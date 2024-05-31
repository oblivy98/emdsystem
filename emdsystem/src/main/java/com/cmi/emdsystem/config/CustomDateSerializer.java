package com.cmi.emdsystem.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends com.fasterxml.jackson.databind.ser.std.StdSerializer<LocalDateTime> {

    private static final long serialVersionUID = 1L;

    public CustomDateSerializer() {
        this(null);
    }

    public CustomDateSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(LocalDateTime value,
            JsonGenerator gen,
            SerializerProvider arg2) throws IOException, JsonProcessingException {
        gen.writeString(value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
    }

}