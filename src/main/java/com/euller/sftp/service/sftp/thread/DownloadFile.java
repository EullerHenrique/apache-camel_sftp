package com.euller.sftp.service.sftp.thread;

import com.jcraft.jsch.ChannelSftp;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.http.client.utils.URIBuilder;

import java.io.*;
import java.net.URI;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

//Callable: Uma task que retorna um resultado e pode lançar uma exceção.
//Os implementadores definem um único método sem argumentos chamado call.
//A interface Callable é semelhante à Runnable, pois ambas são projetadas para classes cujas instâncias são
//potencialmente executadas por outro thread.
//Um Runnable, no entanto, não retorna um resultado e não pode lançar uma exceção verificada.
//A classe Executors contém métodos utilitários para converter de outros formulários comuns em classes Callable.
public class DownloadFile implements Callable<Boolean> {

    private final String file;

    public DownloadFile(String file){
        this.file = file;
    }

    public Boolean download() throws IOException {

        System.out.println("Init "+this.file);

        try {
            URI fromFtpUrl = new URIBuilder()
                    .setScheme("sftp")
                    .setHost("10.120.11.106")
                    .setPort(2222)
                    .setPath("/upload/"+this.file)
                    .addParameter("username", "usermirabr")
                    .addParameter("password", "a.123456")
                    .addParameter("noop", "true")
                    //.addParameter("passiveMode", "false")
                    //.addParameter("initialDelay", "10s")
                    //.addParameter("delay", "50")
                    //.addParameter("moveFailed", "/upload/"+this.file+"/error")
                    //.addParameter("move", "/upload/"+this.file+"/done")
                    //.addParameter("preMove", "/upload/"+this.file+"/in-progress")
                    //.addParameter("readLock", "changed")
                    //.addParameter("readLockMinAge", "1m")
                    //.addParameter("readLockTimeout", "70000")
                    //.addParameter("readLockCheckInterval", "5000")
                    //.addParameter("stepwise", "false")
                    //.addParameter("useUserKnownHostsFile", "false")
                    .build();

            try (CamelContext camel = new DefaultCamelContext()) {

                camel.addRoutes(new RouteBuilder() {
                    @Override
                    public void configure() {
                        from(fromFtpUrl.toString())
                                .log("Downloaded file ${file:name} complete.")
                                .to("file://D:\\Documents\\Study\\UFU\\CURSOS\\SFTP\\apache-camel_sftp\\src\\main\\resources\\download\\"+file)
                                .process(new Processor() {
                                    @Override
                                    public void process(Exchange exchange) throws Exception{
                                        if((Boolean) exchange.getAllProperties().get("CamelBatchComplete")){

                                            //https://stackoverflow.com/questions/43124437/how-to-stop-camel-context-immediately

                                            camel.getShutdownStrategy().setTimeUnit(TimeUnit.MILLISECONDS);
                                            camel.getShutdownStrategy().setTimeout(1);
                                            camel.getShutdownStrategy().setShutdownNowOnTimeout(true);

                                            camel.stop();
                                        }
                                    }
                                });
                    }
                });

                camel.start();
                while(!camel.isStopped()){}

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public Boolean call() {
        try {
            return this.download();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
