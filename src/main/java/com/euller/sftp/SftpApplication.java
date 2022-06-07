package com.euller.sftp;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@AllArgsConstructor
public class SftpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SftpApplication.class, args);
    }

}
