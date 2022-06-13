package com.euller.sftp.service.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvServiceImp implements CsvService{

    public void manipulateCSV(int pci, int pcf){

        try{

            //BuferedReader: Lê texto de um fluxo de entrada de caracteres, armazenando caracteres em buffer para
            //fornecer uma leitura eficiente de caracteres, matrizes e linhas.
            //O tamanho do buffer pode ser especificado ou o tamanho padrão pode ser usado. O padrão é grande o
            //suficiente para a maioria dos propósitos.
            //Em geral, cada solicitação de leitura feita de um Reader faz com que uma solicitação de leitura
            //correspondente seja feita do caractere ou fluxo de bytes subjacente. Portanto, é aconselhável envolver
            //um BufferedReader em qualquer Reader cujas operações read() possam ser caras, como FileReaders e
            //InputStreamReaders. Por exemplo,
            //  Leitor em buffer em
            //    = new BufferedReader(new FileReader("foo.in"));
            //
            //armazenará em buffer a entrada do arquivo especificado. Sem buffer, cada chamada de read() ou readLine()
            //poderia fazer com que os bytes fossem lidos do arquivo, convertidos em caracteres e então retornados,
            //o que pode ser muito ineficiente.
            //Programas que usam DataInputStreams para entrada textual podem ser localizados substituindo cada
            //DataInputStream por um BufferedReader apropriado.

            //InputStreamReader: Um InputStreamReader é uma ponte de fluxos de bytes para fluxos de caracteres:
            //ele lê bytes e os decodifica em caracteres usando um charset. O charset que ele usa pode ser especificado
            //pelo nome ou pode ser dado explicitamente, ou o charset padrão da plataforma pode ser aceito.
            //Cada chamada de um dos métodos read() de um InputStreamReader pode fazer com que um ou mais bytes sejam
            //lidos do fluxo de entrada de bytes subjacente. Para habilitar a conversão eficiente de bytes em caracteres,
            //mais bytes podem ser lidos antes do fluxo subjacente do que o necessário para satisfazer a operação de
            //leitura atual.
            //Para maior eficiência, considere agrupar um InputStreamReader em um BufferedReader. Por exemplo:
            //Leitor em buffer em
            //   = new BufferedReader(new InputStreamReader(System.in));


            //FileInputStream: Cria um FileInputStream abrindo uma conexão com um arquivo real, o arquivo nomeado pelo
            //nome do caminho no sistema de arquivos. Um novo objeto FileDescriptor é criado para representar essa
            //conexão de arquivo.
            //Primeiro, se houver um gerenciador de segurança, seu método checkRead é chamado com o argumento name
            //como argumento.
            //Se o arquivo nomeado não existir, for um diretório em vez de um arquivo normal ou, por algum outro motivo,
            //não puder ser aberto para leitura, uma FileNotFoundException será lançada.

            //File: Cria uma nova instância de File convertendo a string de nome de caminho fornecida em um nome de
            //caminho abstrato. Se a string fornecida for a string vazia, o resultado será o nome do caminho abstrato vazio.

            BufferedReader bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(
                                    //ISO_8859_1: SO/IEC 8859-1 (informalmente, Latin1) é uma codificação de caracteres do alfabeto latino.
                                    //Inclui caracteres acentuados.
                                    //Este padrão é a base de outros mapeamentos amplamente usados como o ISO-8859-1 e o Windows-1252.
                                    //(CP-1252: Códificação padrão do excel)
                                    new FileInputStream(
                                            new File("src/main/resources/download/11/input/31-05-2022-0152359-clarobr.csv").getAbsolutePath()
                                    ),
                                    StandardCharsets.ISO_8859_1));

            //lines: Retorna um Stream, cujos elementos são linhas lidas deste BufferedReader. O Stream é preenchido
            //lentamente, ou seja, somente leitura ocorre durante a operação de fluxo do terminal.
            //O leitor não deve ser operado durante a execução da operação de fluxo do terminal. Caso contrário, o
            //resultado da operação de fluxo terminal é indefinido.
            //Após a execução da operação de fluxo do terminal, não há garantias de que o leitor estará em uma posição
            //específica para ler o próximo caractere ou linha.
            //Se uma IOException for lançada ao acessar o BufferedReader subjacente, ela será encapsulada em uma
            //UncheckedIOException que será lançada do método Stream que causou a leitura. Este método retornará um
            //Stream se invocado em um BufferedReader que está fechado. Qualquer operação nesse fluxo que exija a
            //leitura do BufferedReader depois que ele for fechado fará com que uma UncheckedIOException seja lançada.

            //collect: Executa uma operação de redução mutável nos elementos desse fluxo usando um Coletor.
            //Um Collector encapsula as funções usadas como argumentos para coletar (Supplier, BiConsumer, BiConsumer),
            //permitindo a reutilização de estratégias de coleta e composição de operações de coleta, como agrupamento
            //ou particionamento de vários níveis.
            //Se o fluxo for paralelo e o Coletor for concorrente e o fluxo não for ordenado ou o coletor não for
            //ordenado, uma redução simultânea será executada
            //Esta é uma operação terminal.
            //Quando executados em paralelo, vários resultados intermediários podem ser instanciados, preenchidos e
            //mesclados para manter o isolamento de estruturas de dados mutáveis. Portanto, mesmo quando executado em
            //paralelo com estruturas de dados não thread-safe (como ArrayList), nenhuma sincronização adicional é
            //necessária para uma redução paralela

            //Collectors: Implementações de Collector que implementam várias operações de redução úteis,
            //como acumular elementos em coleções, resumir elementos de acordo com vários critérios, etc.
            //Veja a seguir exemplos de uso dos coletores predefinidos para executar tarefas comuns de redução mutáveis:
            //
            // Acumula nomes em uma Lista List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());
            // Acumula nomes em um TreeSet Set<String> set = people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));
            // Converte elementos em strings e os concatena, separados por vírgulas String join = things.stream().map(Object::toString).collect(Collectors.joining(", "));
            // Calcula a soma dos salários do funcionário int total = employees.stream().collect(Collectors.summingInt(Employee::getSalary));
            // Agrupar funcionários por departamento Map<Department, List<Employee>> byDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
            // Calcula a soma dos salários por departamento Map<Department, Integer> totalByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingInt(Employee::getSalary)));
            // Particiona os alunos para aprovação e reprovação Map<Boolean, List<Student>> passandoFailing = students.stream().collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));

            //joining: Retorna um Collector que concatena os elementos de entrada, separados pelo delimitador
            //especificado, na ordem de encontro.

            //System.lineSeparator: Retorna a string do separador de linha dependente do sistema.
            //Ele sempre retorna o mesmo valor - o valor inicial da propriedade do sistema line.separator.
            //Em sistemas UNIX, retorna "\n"; em sistemas Microsoft Windows, ele retorna "\r\n".

            // Transforma o csv com colunas divididas por ; em um csv com colunas dividas em , ( O OpenCSV só funciona com CSVS dividos com ,)
            String linesInput = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
            linesInput = linesInput.replace(";", ",");

            //Opencsv: Opencsv é uma biblioteca de analisador CSV (valores separados por vírgula) fácil de usar para Java.
            //CVSReader: Um leitor de CSV muito simples
            //StringReader: Um fluxo de caracteres cuja origem é uma string.
            //csvReader.readAll: Lê o arquivo inteiro em uma lista com cada elemento sendo um String[] de tokens.

            //Lê o csv
            CSVReader csvReader = new CSVReader(new StringReader(linesInput));
            List<String[]> linesOutput = csvReader.readAll();

            //File: Cria uma nova instância de File convertendo a string de nome de caminho fornecida em um nome de
            //caminho abstrato. Se a string fornecida for a string vazia, o resultado será o nome do caminho abstrato vazio.

            //file.listFiles: Retorna uma matriz de nomes de caminho abstratos que denotam os arquivos no diretório
            //indicados por esse nome de caminho abstrato.
            //Se esse nome de caminho abstrato não denotar um diretório, esse método retornará nulo. Caso contrário,
            //uma matriz de objetos File é retornada, uma para cada arquivo ou diretório no diretório. Os nomes de
            //caminho que denotam o próprio diretório e o diretório pai do diretório não são incluídos no resultado.
            //Cada nome de caminho abstrato resultante é construído a partir desse nome de caminho abstrato usando o
            //construtor File(File, String). Portanto, se esse nome de caminho for absoluto, cada nome de caminho
            //resultante será absoluto; se este nome de caminho for relativo, cada nome de caminho resultante será
            //relativo ao mesmo diretório.
            //Não há garantia de que as strings de nomes na matriz resultante aparecerão em qualquer ordem específica;
            //eles não são, em particular, garantidos para aparecer em ordem alfabética.
            //Observe que a classe java.nio.file.Files define o método newDirectoryStream para abrir um diretório e
            //iterar sobre os nomes dos arquivos no diretório. Isso pode usar menos recursos ao trabalhar com diretórios
            //muito grandes.

            //Verifica se todos os vínculos (mp3) do csv existem
            File file =  new File( new File("src/main/resources/download/11/input").getAbsolutePath());
            File[] files = file.listFiles();

            List<Boolean> bs = new ArrayList<>();
            for (int i = 0; i < linesOutput.size(); i++) {
                if (i > 0) {
                    int ii = i;
                    //Arrays: Esta classe contém vários métodos para manipular arrays (como classificação e pesquisa).
                    //Essa classe também contém uma fábrica estática que permite que os arrays sejam vistos como listas.
                    //Todos os métodos nessa classe lançam um NullPointerException, se a referência de matriz especificada
                    //for nula, exceto onde indicado.
                    //A documentação para os métodos contidos nesta classe inclui breves descrições das implementações.
                    //Tais descrições devem ser consideradas como notas de implementação, em vez de partes da especificação.
                    //Os implementadores devem se sentir à vontade para substituir outros algoritmos, desde que a própria
                    //especificação seja respeitada. (Por exemplo, o algoritmo usado por sort(Object[]) não precisa ser um
                    //MergeSort, mas precisa ser estável.)

                    //stream: Retorna um Stream sequencial com a matriz especificada como origem.

                    //anyMatch: Retorna se algum elemento deste fluxo corresponde ao predicado fornecido.
                    //Pode não avaliar o predicado em todos os elementos se não for necessário para determinar o resultado.
                    //Se o fluxo estiver vazio, false será retornado e o predicado não será avaliado.

                    //equals: Compara essa string com o objeto especificado.
                    //O resultado é verdadeiro se e somente se o argumento não for nulo e for um objeto String que
                    //representa a mesma sequência de caracteres desse objeto.

                    //split: Divide essa string em torno de correspondências da expressão regular fornecida.
                    //Esse método funciona como se invocasse o método split de dois argumentos com a expressão fornecida
                    //e um argumento limite igual a zero.
                    //Strings vazias à direita, portanto, não são incluídas na matriz resultante.
                    //[4] = Vínculo

                    if (Arrays.stream(files).anyMatch(f -> linesOutput.get(ii)[4].equals(f.getName().split("\\.")[0]))) {
                        bs.add(true);
                    } else {
                        bs.add(false);
                    }
                }
            }

            //allMatch: Retorna true se todos os elementos deste fluxo correspondem ao predicado fornecido.
            //Pode não avaliar o predicado em todos os elementos se não for necessário para determinar o resultado.
            //Se o fluxo estiver vazio, true será retornado e o predicado não será avaliado.
            Boolean vd = bs.stream().allMatch(b -> b == true);

            //Troca uma coluna de lugar com outra se todos os vínculos (mp3) do csv existiren

            if(vd) {

                for (String[] columns : linesOutput) {
                    {

                        String temp = columns[pci];

                        columns[pci] = columns[pcf];
                        columns[pcf] = temp;

                    }
                }


                //csvReader.close(): A Closeable é uma origem ou destino de dados que podem ser fechados.
                //O método close é invocado para liberar recursos que o objeto está mantendo (como arquivos abertos).
                csvReader.close();

                //fileOutputStream: Cria um fluxo de saída de arquivo para gravar no arquivo com o nome especificado.
                //Um novo objeto FileDescriptor é criado para representar essa conexão de arquivo.
                //Primeiro, se houver um gerenciador de segurança, seu método checkWrite será chamado com o nome
                //como argumento.
                //Se o arquivo existir, mas for um diretório em vez de um arquivo normal, não existir, mas não puder
                //ser criado ou não puder ser aberto por qualquer outro motivo, uma FileNotFoundException será lançada.

                //File: Cria uma nova instância de File convertendo a string de nome de caminho fornecida em um nome de
                //caminho abstrato. Se a string fornecida for a string vazia, o resultado será o nome do caminho abstrato vazio.

                FileOutputStream fileOutputStream = new FileOutputStream(
                        new File("src/main/resources/download/11/output/31-05-2022-0152359-claro-v2.csv").getAbsolutePath());


                //BufferedWriter: Grava texto em um fluxo de saída de caracteres, armazenando caracteres em buffer para
                //fornecer a gravação eficiente de caracteres únicos, matrizes e strings.
                //O tamanho do buffer pode ser especificado ou o tamanho padrão pode ser aceito. O padrão é grande o
                //suficiente para a maioria dos propósitos.
                //Um método newLine() é fornecido, que usa a própria noção de separador de linha da plataforma, conforme
                //definido pela propriedade do sistema line.separator. Nem todas as plataformas usam o caractere de nova
                //linha ('\n') para terminar as linhas. Chamar esse método para encerrar cada linha de saída é, portanto,
                //preferível a escrever um caractere de nova linha diretamente.
                //Em geral, um Writer envia sua saída imediatamente para o fluxo de caracteres ou bytes subjacente.

                //OutputStreamWriter: Um OutputStreamWriter é uma ponte de fluxos de caracteres para fluxos de bytes:
                //os caracteres gravados nele são codificados em bytes usando um conjunto de caracteres especificado.
                //O charset que ele usa pode ser especificado pelo nome ou pode ser dado explicitamente, ou o charset
                //padrão da plataforma pode ser aceito.
                //Cada chamada de um método write() faz com que o conversor de codificação seja invocado no(s)
                //caractere(s) fornecido(s). Os bytes resultantes são acumulados em um buffer antes de serem gravados
                //no fluxo de saída subjacente. Observe que os caracteres passados ​​para os métodos write()
                //não são armazenados em buffer.
                //Para maior eficiência, considere agrupar um OutputStreamWriter em um BufferedWriter para evitar
                //chamadas frequentes de conversor. Por exemplo:
                //  BufferedWriter
                //    = new BufferedWriter(new OutputStreamWriter(System.out));
                //

                //CSVWriter: Um gravador de CSV muito simples
                //csvWriter.writeAll(): Escrita iterável em um arquivo CSV. A lista é considerada uma String[]:
                //csvWriter.flush(): Um Flushable é um destino de dados que pode ser liberado. O método flush é
                //invocado para gravar qualquer saída em buffer no fluxo subjacente.
                //csvWriter.close(): A Closeable é uma origem ou destino de dados que podem ser fechados.
                //O método close é invocado para liberar recursos que o objeto está mantendo (como arquivos abertos).

                //ISO_8859_1: (informalmente, Latin1) é uma codificação de caracteres do alfabeto latino.
                //Inclui caracteres acentuados.
                //Este padrão é a base de outros mapeamentos amplamente usados como o ISO-8859-1 e o Windows-1252.
                //(CP-1252: Códificação padrão do excel)

                BufferedWriter bufferedWriter = new BufferedWriter(
                                                    new OutputStreamWriter(
                                                            fileOutputStream,
                                                            StandardCharsets.ISO_8859_1
                                                    )
                                            );
                CSVWriter csvWriter = new CSVWriter(bufferedWriter, ';', '"', '"', "\n");
                csvWriter.writeAll(linesOutput);
                csvWriter.flush();
                csvWriter.close();

            }

        }catch (IOException | CsvException e){
            e.printStackTrace();
        }
    }
}
