package com.example.integrationservice.integration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "custom.email")
public class EmailSettings {

    private String username;
    private String password;
    private String host;
    private String mailBox;

    private long pollRate = 30_000;

    public String getImapUrl() {
        return String.format("imaps://%s:%s@%s/%s",
            this.username, this.password, this.host, this.mailBox);
    }


}
