package com.euller.sftp.controller;

import lombok.RequiredArgsConstructor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SftpController {


    @GetMapping("/download/{d}")
    public void download(@PathVariable("d") String d)  {

        try {
            URI fromFtpUrl = new URIBuilder()
                    .setScheme("sftp")
                    .setHost("10.120.11.106")
                    .setPort(2222)
                    .setPath("/upload/"+d+"/in")
                    .addParameter("username", "usermirabr")
                    .addParameter("password", "a.123456")
                    //.addParameter("passiveMode", "false")
                    //.addParameter("initialDelay", "10s")
                    //.addParameter("delay", "50")
                    .addParameter("moveFailed", "/upload/"+d+"/error")
                    .addParameter("move", "/upload/"+d+"/done")
                    .addParameter("preMove", "/upload/"+d+"/in-progress")
                    //.addParameter("readLock", "changed")
                    //.addParameter("readLockMinAge", "1m")
                    //.addParameter("readLockTimeout", "70000")
                    //.addParameter("readLockCheckInterval", "5000")
                    //.addParameter("stepwise", "false")
                    //.addParameter("useUserKnownHostsFile", "false")
                    .build();

            // Cria o contexto do Camel
            try (CamelContext camel = new DefaultCamelContext()) {

                // Adiciona as rotas que podem ser escritas como uma classe anônima interna
                // (para todo o código ficar em um único arquivo Java)
                camel.addRoutes(new RouteBuilder() {
                    @Override
                    public void configure() {
                        from(fromFtpUrl.toString())
                                .log("Downloaded file ${file:name} complete.")
                                .to("file://D:\\Documents\\Study\\UFU\\CURSOS\\SFTP\\apache-camel_sftp\\src\\main\\resources\\download\\"+d);
                    }
                });

                // Iniciando
                camel.start();

                // roda por 10 segundos
                Thread.sleep(10_000);

                // e para de rodar tranquilamente
                camel.stop();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
