package com.euller.sftp.service.sftp.thread;

import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class ThreadService {

    //ExecutorService: “Um Executor que fornece métodos para gerenciar o encerramento e métodos que podem produzir um
    //Future para rastrear o progresso de uma ou mais tarefas assíncronas.”.

    //Um ExecutorService pode ser encerrado, o que fará com que ele rejeite novas tarefas. Dois métodos diferentes são
    //fornecidos para encerrar um ExecutorService. O método shutdown permitirá que as tarefas enviadas anteriormente
    //sejam executadas antes de serem encerradas, enquanto o método shutdownNow impede que as tarefas em espera sejam
    //iniciadas e tenta interromper as tarefas em execução no momento. Após o término, um executor não tem tarefas em
    //execução ativa, nenhuma tarefa aguardando execução e nenhuma nova tarefa pode ser enviada. Um ExecutorService não
    //utilizado deve ser encerrado para permitir a recuperação de seus recursos.

    //Executors.newSingleThreadExecutor: Cria um Executor que usa um único thread de trabalho operando em uma fila
    //ilimitada. (Observe, no entanto, que se este único thread terminar devido a uma falha durante a execução antes do
    //desligamento, um novo assumirá seu lugar, se necessário, para executar as tasks subsequentes.) As tasks são
    //garantidas para executar sequencialmente e não mais de uma task estará ativa a qualquer momento. Ao contrário do
    //newFixedThreadPool(1) equivalente, o executor retornado é garantido para não ser reconfigurável para usar threads
    //adicionais.

    //Executors.newCachedThreadPool: Cria um pool de threads que cria novas threads conforme necessário,
    //mas reutilizará threads construídas anteriormente quando estiverem disponíveis. Esses pools geralmente
    //melhoram o desempenho de programas que executam muitas tasks assíncronas de curta duração. Chamadas para executar
    //irão reutilizar threads previamente construídas, se disponíveis. Se nenhuma thread existente estiver disponível,
    //um nova thread  será criada e adicionada ao pool. As threads que não foram usadas por sessenta segundos são
    //encerradas e removidas do cache. Assim, um pool que permanece ocioso por tempo suficiente não consumirá nenhum
    //recurso. Observe que pools com propriedades semelhantes, mas detalhes diferentes (por exemplo, parâmetros de tempo
    //limite) podem ser criados usando construtores ThreadPoolExecutor.

    //Executors.newFixedThreadPool: Cria um pool de threads que reutiliza um número fixo de threads operando
    //em uma fila ilimitada compartilhada. Em qualquer ponto, no máximo nThreads terão tasks de processamento
    //ativas. Se tasks adicionais forem enviadas quando todos as threads estiverem ativas, elas aguardarão na
    //fila até que uma thread esteja disponível. Se alguma thread for encerrada devido a uma falha durante a
    //execução antes do desligamento, uma nova tomará seu lugar, se necessário, para executar as tasks subsequentes.
    //As threads no pool existirão até que seja explicitamente encerrado.
    private final ExecutorService es = Executors.newFixedThreadPool(10);

    //Um Future representa o resultado de uma computação assíncrona. São fornecidos métodos para verificar se a
    //computação está completa, para aguardar sua conclusão e para recuperar o resultado da computação. O resultado só
    //pode ser recuperado usando o método get quando a computação for concluída, bloqueando se necessário até que
    //esteja pronto. O cancelamento é realizado pelo método de cancelamento. Métodos adicionais são fornecidos para
    //determinar se a tarefa foi concluída normalmente ou foi cancelada. Uma vez que um cálculo tenha sido concluído,
    //o cálculo não pode ser cancelado. Se você quiser usar um Future para fins de cancelamento, mas não fornecer um
    //resultado utilizável, poderá declarar tipos no formato Future<?> e retornar null como resultado da tarefa subjacente.
    public Future<?> create(String file) {
        //O método submit estende o método base Executor.execute(Runnable) criando e retornando um Future que pode ser
        //usado para cancelar a execução e/ou aguardar a conclusão.
        //Os métodos invokeAny e invokeAll executam as formas mais úteis de execução em massa, executando uma coleção
        //de tarefas e, em seguida, aguardando a conclusão de pelo menos uma, ou todas.
        //(A classe ExecutorCompletionService pode ser usada para escrever variantes personalizadas desses métodos.)
        //A classe Executors fornece métodos de fábrica para os serviços de executor fornecidos neste pacote.
        System.out.println("ES -> " + es);
        return es.submit(new DownloadFile(file));
    }


}
