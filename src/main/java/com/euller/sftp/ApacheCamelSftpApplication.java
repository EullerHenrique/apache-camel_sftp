package com.euller.sftp;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@AllArgsConstructor
public class ApacheCamelSftpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApacheCamelSftpApplication.class, args);
    }

}
