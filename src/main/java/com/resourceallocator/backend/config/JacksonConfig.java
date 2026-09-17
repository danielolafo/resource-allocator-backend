package com.resourceallocator.backend.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.resourceallocator.backend.domain.model.ProficiencyLevel;
import com.resourceallocator.backend.domain.model.ProjectStatus;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer enumCustomizer() {
        SimpleModule module = new SimpleModule();

        module.addSerializer(ProficiencyLevel.class, new JsonSerializer<ProficiencyLevel>() {
            @Override
            public void serialize(ProficiencyLevel value, JsonGenerator gen,
                                  SerializerProvider serializers) throws IOException {
                gen.writeString(value.label());
            }
        });
        module.addDeserializer(ProficiencyLevel.class, new StdDeserializer<ProficiencyLevel>(ProficiencyLevel.class) {
            @Override
            public ProficiencyLevel deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                return ProficiencyLevel.fromLabel(p.getValueAsString());
            }
        });

        module.addSerializer(ProjectStatus.class, new JsonSerializer<ProjectStatus>() {
            @Override
            public void serialize(ProjectStatus value, JsonGenerator gen,
                                  SerializerProvider serializers) throws IOException {
                gen.writeString(value.label());
            }
        });
        module.addDeserializer(ProjectStatus.class, new StdDeserializer<ProjectStatus>(ProjectStatus.class) {
            @Override
            public ProjectStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                return ProjectStatus.fromLabel(p.getValueAsString());
            }
        });

        return builder -> builder.modulesToInstall(module);
    }
}