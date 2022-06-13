package com.euller.sftp.test;

import com.euller.sftp.controller.CsvController;
import com.euller.sftp.controller.SftpController;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
@RequiredArgsConstructor
public class Test {

    private final SftpController sftpController;

    private final CsvController csvController;

    //@Bean
    public void testeVolume(){

        LocalDateTime inicio = LocalDateTime.now();
        System.out.println("Início: "+inicio);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Boolean>> callableTasks = new ArrayList<>();
        Callable<Boolean> callableTask;

        for(int i=0; i < 10; i++){
            int ii = i+1;
            callableTask = () -> sftpController.download(ii+"");
            callableTasks.add(callableTask);
        }

        List<Future<Boolean>> futures;
        try {
            //invokeAll: Executa as tarefas dadas, retornando uma lista de Futuros com seus status e resultados quando todos
            //estiverem concluídos. Future.isDone é verdadeiro para cada elemento da lista retornada.
            //Observe que uma tarefa concluída pode ser encerrada normalmente ou lançando uma exceção.
            //Os resultados deste método são indefinidos se a coleção especificada for modificada enquanto esta
            //operação estiver em andamento.
            futures =  executorService.invokeAll(callableTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //allMatch: Retorna true se todos os elementos deste fluxo correspondem ao predicado fornecido.
        //Pode não avaliar o predicado em todos os elementos se não for necessário para determinar o resultado.
        //Se o fluxo estiver vazio, true será retornado e o predicado não será avaliado.
        boolean result = futures.stream().allMatch(b ->{
            try {
                return b.get() == true;
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

       if(result) {

           LocalDateTime fim = LocalDateTime.now();
           System.out.println("Fim: " + fim);

           //Calcula a quantidade de tempo até outra data-hora em termos da unidade especificada.
           //Isso calcula a quantidade de tempo entre dois objetos LocalDateTime em termos de um único TemporalUnit.
           long millis = inicio.until(fim, ChronoUnit.MILLIS);

           long hours = TimeUnit.MILLISECONDS.toHours(millis);
           millis-=TimeUnit.HOURS.toMillis(hours);
           long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
           millis-=TimeUnit.MINUTES.toMillis(minutes);
           long secondes = TimeUnit.MILLISECONDS.toSeconds(millis);
           millis-=TimeUnit.SECONDS.toMillis(secondes);

           System.out.println("Tempo decorrido: " + hours + ":" + minutes + ":" + secondes + ":" + millis);

       }

    }

    @Bean
    public void testeManipulacao(){

        csvController.manipulateCSV(1, 5);

    }

}
