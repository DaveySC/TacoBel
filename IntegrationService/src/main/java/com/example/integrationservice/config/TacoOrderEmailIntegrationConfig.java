package com.example.integrationservice.config;

import com.example.integrationservice.integration.EmailSettings;
import com.example.integrationservice.integration.EmailToOrderTransformer;
import com.example.integrationservice.integration.OrderSubmitMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TacoOrderEmailIntegrationConfig {

    @Bean
    public IntegrationFlow tacoOrderEmailFlow(
            EmailSettings emailSettings,
            EmailToOrderTransformer emailToOrderTransformer,
            OrderSubmitMessageHandler orderSubmitMessageHandler) {
        return IntegrationFlows
                .from(Mail.imapInboundAdapter(emailSettings.getImapUrl()),
                    e -> e.poller((
                            Pollers.fixedDelay(emailSettings.getPollRate()))))
                .transform(emailToOrderTransformer)
                .handle(orderSubmitMessageHandler)
                .get();

    }

    @Bean
    public static RestTemplate rest() {
        return new  RestTemplate();
    }

}
