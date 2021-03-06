package com.example.integrationservice.integration;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "textInChanel")
public interface FileWriterGateway {

    void writeToFile(@Header(FileHeaders.FILENAME) String fileName, String data);

}
