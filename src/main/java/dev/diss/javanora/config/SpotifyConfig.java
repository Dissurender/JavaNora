package dev.diss.javanora.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "dev.diss.javanora")
public class SpotifyConfig {

    private App app = new App();

    @Data
    public class App {
        private String clientId;
        private String redirectURL;
    }
}
