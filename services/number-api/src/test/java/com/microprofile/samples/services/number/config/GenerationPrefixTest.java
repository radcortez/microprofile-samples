package com.microprofile.samples.services.number.config;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.microprofile.samples.services.number.config.GenerationPrefix.BK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestProfile(GenerationPrefixTest.TestProfile.class)
class GenerationPrefixTest {
    @Inject
    Config config;
    
    @Test
    void prefix() {
        assertEquals(BK, config.getValue("generation.prefix", GenerationPrefix.class));
        ConfigValue configValue = config.getConfigValue("generation.prefix");

        assertTrue(configValue.getSourceName().contains("config.properties"));
        assertEquals("config.properties", config.getConfigValue("smallrye.config.locations").getValue());
    }

    public static class TestProfile implements QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            Map<String, String> configs = new HashMap<>();
            configs.put("smallrye.config.locations", "config.properties");
            return configs;
        }

        @Override
        public String getConfigProfile() {
            return "test";
        }
    }
}
