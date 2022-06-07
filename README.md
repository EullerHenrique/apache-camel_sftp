# Protocol-sftp

## Sumário 

- [Conceito](#conceito)

- [Tecnologias Utilizadas](#tecnologias-utilizadas)
 
- [Configuração](#configuração)


## Conceito

"

FTP – File Transfer Protocol

Como o nome revela, o FTP é um protocolo de transferência de arquivos. Isso significa que o FTP cria um canal de comunicação entre o seu computador e 
o servidor que hospeda o seu site para que você possa enviar os dados que quer alocar no seu site ou fazer modificações nele.

Por que isso é tão importante? Bem, vamos imaginar que você tem um site em WordPress, um dos CMS mais famosos e utilizados no mundo. Enquanto edita seu site,
você, acidentalmente, acaba apagando uma linha de código importante e o seu site sai do ar. Sem acesso ao painel do WordPress fica impossível voltar ao
editor CSS para corrigir o erro. E agora?

Agora uma das soluções é acessar o FTP! Lá você vai encontrar tudo o que estiver na sua hospedagem, inclusive a pasta com os arquivos do WordPress. 
Aí é só encontrar o problema, ajeitar o código ou simplesmente apagar a pasta do template e começar de novo.

Se você não estiver trabalhando com um CMS como o WordPress, ter acesso ao FTP também te permite editar e subir direto na hospedagem aplicações e dados
que você precisa ter por lá.

Claro que isso não significa que você precisa fazer tudo pelo FTP, necessariamente. Mas é um acesso que pode agilizar o processo de transferência de 
arquivos e resolver problemas como esse exemplo do WordPress.

Quando quiser, você pode acessar o FTP por meio de um endereço e acesso específicos na sua hospedagem ou utilizando um dos vários softwares de FTP que 
existem no mercado.

FTPS – File Transfer Protocol over SSL

FTPS nada mais é do que um FTP com uma camada extra de segurança SSL. As informações trafegadas entre os dois canais (o seu computador e o servidor) não 
possuem um recurso de segurança que cuide exclusivamente dessa transmissão.

No FTPS os dados são criptografados usando a tecnologia SSL (Secure Socket Layer). Na prática, isso quer dizer que as informações viram uma espécie de 
enigma quando saem do seu computador e só são decifradas quando chegam ao servidor.

Desta forma, mesmo que alguém consiga interceptar o tráfego com a intenção de roubar dados, não vai poder ler as informações.

Existem dois tipos de FTPS, o com SSL implícito e explícito. No primeiro, todas as conexões têm que ser, obrigatoriamente, protegidas por criptografia. 
Qualquer tentativa de transmitir ou receber informações fora do SSL são rejeitadas pelo servidor. No segundo, o cliente (forma como o seu computador é 
chamado) pode escolher quais informações quer que sejam encriptadas.

Nesse segundo caso, você pode escolher transferir informações que não precisam ser protegidas sem o SSL, e criptografar apenas dados confidenciais, como 
números de cartão de crédito de clientes e senhas de acesso.

SFTP – Secure File Transfer Protocol

O SFTP assemelha-se ao FTPS pelo fato de ambos oferecerem uma proteção extra aos arquivos e alterações que estão sendo feitas na hospedagem. No entanto, o 
SFTP utiliza-se da tecnologia SSH (Secure Shell) para autenticar o contato e estabelecer uma conexão segura entre as máquinas.

O SSH é um outro protocolo de rede criptográfico que tem como objetivo fornecer uma conexão segura para troca de dados e é amplamente usado no caso de 
logins remotos para acesso de sistemas de computadores.

Quando há transmissão com SFTP, as informações não são transmitidas por canais de fluxo direto, mas empacotadas em SSH. O usuário também pode configurar 
chaves privadas para reforçar a segurança enquanto os pacotes de dados são enviados do seu computador para o servidor. 

" - Fonte: https://meunegocio.uol.com.br/blog/ftp-ftps-e-sftp-o-que-sao-e-para-o-que-servem/

"

SFTP

É o único protocolo de transferência de arquivos que protege contra ataques em qualquer ponto do processo de transferência de dados,
sendo então o protocolo preferido.

Durante a transferência de arquivos, todos os dados são divididos em pacotes e enviados através de uma única conexão segura.

Informações sensíveis serão criptografadas e não poderão ser lidas enquanto estão sendo transferidas entre o cliente e o servidor. 
Em outras palavras, o conteúdo original (plaintext) será substituído por uma string de caracteres incoerentes (chipertext).

Apenas quem vai receber as informações, ou seja, quem tem uma chave de decodificação que poderá ver o conteúdo original. 
Isso evita qualquer acesso não autorizado na transferência de arquivos.

O FTP tradicional possui dois canais diferentes para trocar dados, o canal de comando e o canal de dados. 
Por outro lado, o SFTP possui apenas um canal criptografado no qual os dados são trocados em pacotes criptografados e formatados.

" - Fonte: https://www.hostinger.com.br/tutoriais/como-usar-sftp-ssh-file-transfer-protocol

"

Como funciona o protocolo SFTP?

É importante observar que o protocolo SFTP é executado na porta 22, mas pode ser atribuído a qualquer número. Ele é baseado em pacotes, 
em vez de texto, o que significa que é mais fácil processá-lo por ser muito compacto. Por causa disso, o SFTP é mais rápido que outros protocolos.

Para ficar mais fácil de entender, o protocolo SFTP possui dois componentes: o cliente e o servidor. Um cliente SFTP é o software que fornece 
a capacidade de se conectar ao servidor. Também permite fazer upload de arquivos a serem armazenados no servidor, bem como download de arquivos que já estão armazenados.

Um servidor SFTP é o local em que os arquivos são armazenados e de onde você pode se conectar e recuperá-los. O servidor fornece seus serviços 
para que os usuários possam armazenar e transferir dados com segurança. 

O servidor usa o protocolo de transferência de arquivos SSH para manter a conexão segura. O SFTP trabalha junto ao SSH, enviando conexões de 
dados criptografados entre o cliente e o servidor para permitir que senhas e outras informações confidenciais sejam transferidas com segurança 
pela rede. Juntos, eles são o SSH File Transfer Protocol.

De modo geral, o Secure File Transfer Protocol (SFTP) funciona sobre o fluxo de dados Secure Shell (SSH) para estabelecer uma conexão segura e 
fornecer às organizações um nível mais alto de proteção à transferência de arquivos. 

Isso ocorre porque o SFTP usa algoritmos de criptografia para mover dados com segurança para o servidor e manter os arquivos ilegíveis durante 
o processo. Assim, a autenticação impede o acesso não autorizado a arquivos durante a operação. 

Embora o protocolo SFTP não exija autenticação de dois fatores, existe a opção de solicitar um ID de usuário e uma senha, bem como chaves SSH, 
para uma conexão mais segura. Agora, que você já sabe um pouco mais sobre como usar o SFTP, vamos falar sobre como ele funciona na prática, seguindo uma linha cronológica.

Antes de estabelecer uma conexão, o servidor SFTP envia uma impressão digital criptografada de suas chaves públicas de host para garantir que a 
conexão SFTP troque dados com o servidor correto;
 Na primeira vez que a conexão é estabelecida, esta chave ainda não é conhecida pelo programa cliente e deve, portanto, ser confirmada pelo 
usuário antes da troca de dados; 
Depois de estabelecer uma conexão com um servidor FTP e ter certeza de que é realmente o servidor correto, o protocolo SFTP salva as informações 
da impressão digital localmente; 
Isso permite que você compare as informações da impressão digital com os dados salvos sempre que estabelecer uma nova conexão, para garantir que 
não haja ninguém entre você e o servidor; 
Diferentes servidores emitem impressões digitais apenas uma vez. Eles são gerados pela chave privada de um servidor.
O protocolo SFTP oferece a opção de realizar uma ampla variedade de tarefas para arquivos confidenciais, desde a remoção de arquivos até a retomada 
de transferências pausadas. 

Vantagens do protocolo SFTP

Qualquer empresa ou organização que trabalhe com dados sigilosos e confidenciais deve usar o SFTP para protegê-los. As empresas que usam SFTP podem 
transferir informações como dados de faturamento, fundos e arquivos de recuperação de dados, com segurança. 

Ele se baseia no software File Transfer Protocol (FTP), usa o protocolo SSH (Secure Shell) para transferir arquivos e requer que o cliente seja 
autenticado pelo servidor para elementos de segurança aprimorados. O SFTP proporciona a tranquilidade de saber que os dados são protegidos durante 
a transferência, garantindo que os hackers não sejam capazes de obtê-los.

Existem muitos motivos pelos quais as empresas optam por implementar protocolos de transferência de arquivos seguros em suas estratégias. Abaixo, você confere uma lista dos principais. 

Velocidade
Os servidores usados com SFTP podem facilmente suportar grandes transferências de arquivos, bem como vários arquivos de uma vez. Por isso, você 
economizará tempo ao mover dados de um servidor para outro.

Gerenciamento
O protocolo oferece a capacidade de gerenciar facilmente seu servidor usando uma interface da web ou um cliente SFTP.

Segurança
Graças à criptografia, à autenticação de chave pública e à segurança de dados, o protocolo SFTP preserva a integridade dos dados. Há mais 
tranquilidade em saber que os dados também são verificados para garantir que estão vindo de uma fonte confiável e que os clientes e as fontes 
são verificados antes que uma conexão seja estabelecida.

Firewalls
SFTP e firewalls andam de mãos dadas. Dados, comandos e informações confidenciais são enviados por meio de uma única conexão para a porta 22. 
Por padrão, ela é habilitada com firewalls com seus próprios parâmetros de segurança predefinidos.

Metadados
Os usuários do SFTP podem acessar os metadados de seus arquivos, como dados, hora, tamanho, permissões e outras informações, garantindo que 
todos os documentos sejam mais fáceis de encontrar.

Como nenhuma tecnologia é perfeita, o Protocolo de Transferência de Arquivos Seguro vem com algumas desvantagens: as chaves são mais difíceis 
de gerenciar e validar, podendo ser mais difícil configurá-las corretamente sem o suporte de fornecedores de software. Além disso, os padrões 
de configuração SFTP podem levar a problemas de compatibilidade entre títulos de software e diferentes fornecedores.

" - Fonte: https://www.hostgator.com.br/blog/o-que-e-protocolo-sftp/

"

O SFTP é um protocolo cliente-servidor que pode ser iniciado como uma linha de comando ou por meio de uma interface gráfica do usuário (GUI) .
 No primeiro tipo de configuração, o usuário deve digitar linhas de comando específicas para gerar o protocolo SFTP, geralmente em ambiente Linux.
 A última opção faz uso de um programa que abstrai visualmente o uso do SFTP para usuários finais.

O protocolo SFTP é executado no protocolo SSH usando a porta SSH normal 22 e suporta várias operações simultâneas. O cliente identifica cada
 operação com um número único que deve corresponder à resposta do servidor. As solicitações podem ser processadas de forma assíncrona.
 O protocolo SFTP é iniciado somente quando o usuário usa o SSH para efetuar login no servidor para evitar deixar portas adicionais
 expostas ou manter autenticações adicionais.

Um servidor SFTP requer que ambas as partes comunicantes se autentiquem fornecendo um ID de usuário e senha ou validando uma chave SSH (ou ambos).
 Metade da chave SSH é armazenada no computador dos dois clientes, enquanto a outra metade é carregada no servidor e associada à sua conta
 ( chave pública ). Somente quando o par de chaves SSH corresponder, a autenticação poderá ocorrer.

Alguns usuários que são relativamente novos no SFTP como protocolo perguntam se é preferível usar SFTP ou uma rede privada virtual (VPN) .
 Ambos os sistemas protegerão os dados, mas não são a mesma coisa. O SFTP é um protocolo, enquanto a VPN é um túnel criptografado seguro
 para dados. Pensando nisso, as informações também podem ser enviadas usando o protocolo SFTP por meio de uma VPN, tornando a transferência 
ainda mais segura.

O SFTP também pode ser visto como uma melhoria em relação ao FTPS, que é apenas um protocolo FTP executado sobre Transport Layer Security (TLS)
 ou Secure Sockets Layer (SSL) . O FTPS, de fato, requer configurações complexas de firewall, pois as portas 989 e 990 precisam estar abertas,
 depende de uma autoridade de certificação pública centralizada e é propenso a corrupção de arquivos, pois o padrão é o modo ASCII.

" - Fonte: https://www.venafi.com/blog/what-secure-file-transfer-protocol-sftp-and-how-use-it

## Tecnologias Utilizadas

- Spring Boot
- Docker
- atmoz/sftp
- Lombok

## Configuração

  1. Clone o repósitorio e abra-o em uma IDE (Ex: Intellij)
  2. Instale o docker (https://www.docker.com/products/docker-desktop/) e abra-o
  3. Navegue pelo terminal até src/main/resources ou navegue pela IDE até src/main/resources/docker-compose.yaml
  4. Execute o comando docker-compose up -d ou aperte o botão play localizado ao lado do campo services
  5. Navegue pela IDE até SftpApplication
  6. Aperte o botão play localizado ao lado de "public class SftpApplication"


