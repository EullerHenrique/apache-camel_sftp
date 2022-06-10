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
                    .setPath("/upload/upload2/"+this.file)
                    .addParameter("username", "usermirabr")
                    .addParameter("password", "a.123456")
                    //noop: Se true, o arquivo não será movido ou excluído de forma alguma.
                    //Essa opção é boa para dados somente leitura ou para requisitos de tipo ETL.
                    //Se noop=true, o Camel também definirá idempotent=true, para evitar consumir os mesmos arquivos
                    //repetidamente.
                    //--
                    //idempont: Opção para usar o padrão Idempotent Consumer EIP para permitir que o Camel ignore
                    //arquivos já processados. Por padrão, usará um LRUCache baseado em memória que contém 1.000 entradas.
                    //Se noop=true, o idempotente também será habilitado para evitar consumir os mesmos arquivos
                    //repetidamente.
                    .addParameter("noop", "true")
                    //useUserKnownHostsFile: Se knownHostFile não tiver sido configurado explicitamente,
                    //use o arquivo host de System.getProperty(user.home)/.ssh/known_hosts.
                    .addParameter("useUserKnownHostsFile", "false")
                    //https://stackoverflow.com/questions/20576630/genericfileoperationfailedexception-cannot-change-directory-to-when-tryin
                    //--
                    //stepwise: Define se devemos alterar os diretórios passo a passo enquanto percorremos estruturas
                    //de arquivos ao fazer download de arquivos ou também ao fazer upload de um arquivo para um diretório.
                    //Você pode desativá-lo se, por exemplo, estiver em uma situação em que não possa alterar o
                    //diretório no servidor FTP por motivos de segurança. Stepwise não pode ser usado junto com
                    //streamDownload.
                    .addParameter("stepwise", "false")
                    //streamDownload: Define o método de download a ser usado quando não estiver usando um diretório de
                    //trabalho local.
                    //Se definido como true, os arquivos remotos são transmitidos para a rota à medida que são lidos.
                    //Quando definido como false, os arquivos remotos são carregados na memória antes de serem enviados
                    //para a rota. Se habilitar esta opção, você deve definir stepwise=false, pois ambos não podem ser
                    //habilitados ao mesmo tempo.
                    .addParameter("streamDownload", "true")
                    //.addParameter("passiveMode", "false")
                    //.addParameter("initialDelay", "10s")
                    //.addParameter("delay", "50")
                    //.addParameter("readLock", "changed")
                    //.addParameter("readLockMinAge", "1m")
                    //.addParameter("readLockTimeout", "70000")
                    //.addParameter("readLockCheckInterval", "5000")
                    .build();

            //CamelContext: Interface usada para representar o CamelContext usado para configurar rotas e
            //as políticas a serem usadas durante as trocas de mensagens entre os endpoints.
            //O CamelContextfornece acesso a muitos serviços úteis, sendo os mais notáveis componentes, conversores de
            //tipo, registro, terminais, rotas, formatos de dados e idiomas.
            try (CamelContext camel = new DefaultCamelContext()) {

                //routes: As rotas são o principal mecanismo de funcionamento do Camel. Através delas é possível
                //definir as regras de ligação entre diferentes pontos de interconexão (endpoints).
                //Para declarar uma rota é necessário especializar a classe RouteBuilder e implementar o
                //esquema de ligação entre os endpoints no método configure.

                camel.addRoutes(new RouteBuilder() {
                    @Override
                    public void configure() {
                        //from: E um método da Java DSL utilizado para definir o endpoint inicial da rota. É a partir das
                        //mensagens recebidas no endpoint especificado nessa declaração que o Camel inicia uma
                        //troca de mensagens.
                        from(fromFtpUrl.toString())
                                //log:
                                .log("Downloaded file ${file:name} complete.")
                                //to: É um método da Java DSL utilizado para definir o(s) endpoint(s) final(is) da rota.
                                //É opcional e pode estar presente ou não, de acordo com o padrão de integração utilizado.
                                .to("file://D:\\Documents\\Study\\UFU\\CURSOS\\SFTP\\apache-camel_sftp\\src\\main\\resources\\download\\"+file)
                                //process: A forma mais flexível de manipular dados em trânsito é através de processadores.
                                //Um processador é uma classe com propósito de fazer manipulações complexas no conteúdo
                                //de um exchange.
                                .process(new Processor() {
                                    @Override
                                    public void process(Exchange exchange) {
                                        //Exchange: O Exchange é uma interface que define um tipo de objeto responsável
                                        //por armazenar as informações da mensagem dentro das rotas do Camel.

                                        //https://stackoverflow.com/questions/27692396/apache-camel-ftp-how-can-we-know-
                                        //that-all-the-files-are-downloaded-from-the-ftp

                                        //CamelBatchComplete: Um booleano que indica o último Exchange no lote.
                                        //(No caso, o útimo arquivo da pasta)
                                        //É apenas true para a última entrada.
                                        if((Boolean) exchange.getAllProperties().get("CamelBatchComplete")){

                                            //https://stackoverflow.com/questions/43124437/how-to-stop-camel-context-immediately

                                            //Interrompe a execução do apache camel logo após camel.stop ser chamado

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

                //Enquanto o apache camel estiver lendo a pasta, o resto do código não será executado
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
