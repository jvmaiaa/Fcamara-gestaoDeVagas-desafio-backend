# Sistema de gerenciamento de vagas [![NPM](https://img.shields.io/npm/l/react)](https://github.com/jvmaiaa/backend-test-java/blob/master/LICENSE)

## Sobre o projeto

Projeto que consiste em realizar a gerência de vagas veiculares (moto ou carro) em empresas, assim como, toda a organização de relatórios, mostrando a quantidade de entradas e saídas gerais e por hora. Além disso a aplicação possui também um sistema de **autenticação e autoriazação** para que nem todos os usuários não possam realizar algum tipo de ação indesejada, como exclusão de um outro usuário, veículos ou relatório. Utilizei um **Banco de dados SQL (PostgreSQL)** e as respostas das entidades podem ser feitas tanto em **XML** quanto em **JSON** como foi pedido nos requisitos. Implementei também, uma depência para uso facilitado de variaveis de amebiente em um arquivo `.env.properties`, e uma organização detalhada de tratamento de exceções.

## Diagrama de classes
A partir deste diagrama é possível encontrar toda a modelagem dos dados/entidades que estão presentes no sistema e como eles se conectam com as devidas cardinalidades.
![Modelagem](Modelagem.png)

## Perguntas do Desafio

### 1. GraphQL - (Implementação BFF - Backend For Frontend)
- **Pergunta 1**: Explique o que é o GraphQL e como ele se diferencia de uma API REST tradicional.

      R: É uma linguagem de consulta para APIs, ele utiliza apenas um endpoint para todas as consultas e a retorna apenas os dados que são desejados.
- **Pergunta 2**: Descreva como você implementaria o uso do GraphQL como BFF (Backend for Frontend) neste projeto de gerenciamento de estacionamento. Forneça exemplos práticos.

      R: Eu adionaria a dependência do GraphQL no projeto para permitir trabalhar com ele, criaria o schema que representaria as entidades e seus relacionamentos, com as querys então, criaria os resolvers para as querys serem mapeadas no back ou diretamente no DB. Além das queries adicionaria também mutations para permitir modificação dos dados.Infelizmente não deu tempo de realizar a implementação com GraphQL.
- **Pergunta 3**: Quais são os benefícios de utilizar GraphQL em relação à flexibilidade das consultas? Cite possíveis desafios ao utilizá-lo.

      R: É possível realizar uma busca mais flexíveis e customizada a cada consulta que é realizada ao banco, cria um BFF centralizada (uma interface que irá interpretar todos dispositivos) além que é possível evitar envio várias consultas, como ocorre com REST, tendo assim um maior controle dos erros e eficiência em grandes aplicações. Entre os desafios, se não for feito da maneira correta, pode causar um grande problema, devida as consultas pesadas que são feitas ao banco, dificuldade de manutenabilidade dos schemas.

### 2. Banco de Dados (Nível Básico)
   - **Pergunta 1**: Explique os principais conceitos de um banco de dados relacional, como tabelas, chaves primárias e estrangeiras.

    R: Um banco de dados relacional realiza organização dos seus dados através de tabelas (linhas e colunas), sendo as linhas os registros (objetos) e as colunas os atributos (características de cada objeto). Os principais conceitos de Banco SQL é que os seus objetos são identificados em uma tabela com objetos do mesmo tipo, por chaves únicas (chaves primárias) e em tabelas diferentes por chaves estrangeiras (chaves primárias em outras tabelas).
   - **Pergunta 2**: No contexto de uma aplicação de gerenciamento de estacionamento, como você organizaria a modelagem de dados para suportar as funcionalidades de controle de entrada e saída de veículos?

    R: A partir de um novo registro foi iniciado, buscaria o tipo desse veículo a partir da sua chave primária, e reduziria o número de vagas disponíveis daquele tipo. Quando esse mesmo registro for finalizado, adicionaria novamente essa vaga.
   - **Pergunta 3**: Quais seriam as vantagens e desvantagens de utilizar um banco de dados NoSQL neste projeto?

    R: Acredito que a maior vantagem desse projeto, seria no caso dessa aplicação escalar, já que os bancos NoSQL fornecem uma enorme vantagem para sistemas escaláveis por causa do seu desempenho e disponibilidade. Já as desvantagens seriam a perca da consistência de dados (possíveis duplicidades), falta de padronização e complexidade.
### 3. Agilidade (Nível Básico)
   - **Pergunta 1**: Explique o conceito de metodologias ágeis e como elas impactam o desenvolvimento de software.

    R: São um conjuntos de ações que são utilizadas no desenvolvimento de software para a gerência de projetos, para que possua uma entrega contínua, grande flexibilidade, organização no ambiente de desenvolvimento. 
   - **Pergunta 2**: No desenvolvimento deste projeto, como você aplicaria princípios ágeis para garantir entregas contínuas e com qualidade?

    R: Faria um quadro com a divisão das tarefas que serão executadas por áreas e dentro delas, cada uma das suas "sub-atividades". Com isso realizaria o controle e a marcação conforme as atividades fossem sendo desenvolvidas.
   - **Pergunta 3**: Qual a importância da comunicação entre as equipes em um ambiente ágil? Dê exemplos de boas práticas.

    R: Pois cada um terá um papel crucial no desenovilmento de um novo ciclo (sprints) impactando diretamente na atividade de outros colegas de trabalho e entregas futuras, facilitando a atividade que cada um poderá executar futuramente. Um exemplo é caso o produto mude em meio a entrega, deverá ser feito todo um novo planejamento para a nova entrega.
### 4. DevOps (Nível Básico)
   - **Pergunta 1**: O que é DevOps e qual a sua importância para o ciclo de vida de uma aplicação?

    R: É a área que realiza um mistura entre o desenvolvimento de um software e implantação. Eles são cruciais quando falamos de agilidade, já que realizam toda a parte de automatização da entrega de software para o ambiente de produção.
   - **Pergunta 2**: Descreva como você integraria práticas de DevOps no desenvolvimento desta aplicação de estacionamento. Inclua exemplos de CI/CD.

    R: Utilizaria ferramentas como o Github Actions para que toda vez que eu realizasse um commit ou um pull, o código fosse avaliado, compilado e validado CI. Após o CI utilizaria o CD para ter certeza que o código poderia ser posto em produção.
   - **Pergunta 3**: Cite as ferramentas que você usaria para automatizar o processo de deploy e monitoramento da aplicação.

    R: Gihub Actions, Docker e Kubernets.

## Documentação e endpoints disponíveis - Swagger
Caso você queira ver todas as rotas (endpoints) disponíveis, inicialize o projeto e acesse a seguinte URL pelo seu navegador: `http://localhost:8080/api/swagger-ui/index.html#/authentication-controller`

## Tecnologias e suas versões
<table>
  <tr>
    <td>Java</td>
    <td>Spring Boot</td>
    <td>PostgreSQL</td>
    <td>hateoas</td>
    <td>Lombok</td>
    <td>Jackson Dataformat Xml</td>
    <td>Spring Security</td>
    <td>SpringDoc OpenApi</td>
    <td>Flyway</td>
    <td>Java-jwt</td>
    <td>Java-dotenv</td>
  </tr>
  <tr>
    <td>17</td>
    <td>3.3.3</td>
    <td>16.1</td>
    <td>3.3.3</td>
    <td>1.18.30</td>
    <td>2.17.2</td>
    <td>3.2.3</td>
    <td>2.6.0</td>
    <td>10.10.0</td>
    <td>4.4.0</td>
    <td>5.2.2</td>
  </tr>
</table>

## Como Executar o projeto
Pré requisitos:
  - Java 17 instalado 
  - Portgres instalado
  - Algum Client para testar as rotas (endpoints). Recomendo o `Postman` por ser simples e amigável.
  - Caso queira executar alguma consulta específica com o banco, tenha um SGBD. Recomendo o `Dbeaver` pela variedade de Bancos Relacionais que ele oferece suporte.
1. Abra o seu terminal e execute `git clone https://github.com/jvmaiaa/backend-test-java.git` 
2. Vá até o arquivo `.env.properties.example` e renomeie ele para `.env.properties` e adicione as suas credenciais aos campos, para que o Spring reconheça as variáveis de ambiente.
3. Caso tenha dificuldade em configurar as variáveis de ambiente, exclua o arquivo `.env.properties` e configure as credenciais em cada campo do arquivo `application.yml`


## Autenticação e Autorização

Dentro do projeto é possível encontrar três níveis de usuários: **GERENTE, FUNCIONÁRIO** e **CLIENTE** cada um com as suas respectivas permissões.
- **GERENTE**: É a maior permissão e que pode executar qualquer tipo de ação dentro do sistema.
- **FUNCIONÁRIO**: Possui controle limitado dentro do sistema, mas com algumas ações que podem ser utilizadas para realizar a gestão dos clientes e dos relatórios.
- **CLIENTE**: É o usuário com ações e que pode realizar apenas ações que não comprometam o sistema

OBS: Existe alguns usuários que estão sendo pré-cadastrados no arquivo `data.sql` que está presente no projeto, para que possa ser feito a autenticação e autorização. A senda de todos eles é 123.

<details>
<summary>Como cadastrar-se</summary>

**CLIENTE:** Deve acessar a URL `localhost:8080/api/usuario/cliente`
**FUNCIONÁRIO:** Deve acessar a URL `localhost:8080/api/usuario/funcionario`
**GERENTE:** Deve acessar a URL `localhost:8080/api/usuario/gerente` 

No corpo da requisição passar a seguinte estrutura **JSON**.
```
{
  "login" : "<seu_login>",
  "senha" : "<seu_senha>"
}
``` 
Automaticamente o seu usuário irá receber a devida permissão por estar cadastrando a partir de determinada URL.
</details>
<details>
<summary>Como autenticar-se</summary>

Deverá acessar o endpoint `localhost:8080/api/auth` passando o seu login e senha que está cadastrado no sistema, através de um arquivo JSON.
```
{
  "login" : "<seu_login>",
  "senha" : "<seu_senha>"
}
``` 
Caso suas credenciais estejam corretas, você receberá um Json Web Token (JWT) semelhante ao seguinte: `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImNsaSIsImV4cCI6MTcyNjQ3NTkxOX0.KFptyIfkI_-Xa1riZwruf7GDImLMQ0ePDD-rY82HdgE`
</details>
<details>
<summary>Como acessar uma rota protegida</summary>

Com o JWT em mãos, obtido no passo anterior, acesse a uma rota qualquer protegida, passando ele no no campo `Bearer Token` do seu API Client (recomendo o Postman) e execute a requisição. Caso você tenha a permissão desejada, irá ser feito com sucesso.

</details>

## Entidades do Projeto
-> OBS: O projeto deve estar em execução antes de realizer qualquer ação para a URL de cada entidade ser acessada
<details>
<summary>Endereço</summary>

Você irá realizar o cadastro de um endereço a partir da URL `localhost:8080/api/endereco` para que possa ser cadastrada uma empresa futuramente, pois não é possível cadastrar uma empresa sem endereço. O único usuário que pode manipular a entidade `Endereço` é o usuário com a permissão de `GERENTE`. 

Cadastrar um endereço - **POST** -> `localhost:8080/api/endereco`
```
{
    "cep" : "123",
    "rua" : "rua teste",
    "numero" : 20,
    "bairro" : "centro",
    "cidade" : "fualin",
    "estado" : "beltranin"
}
```
Buscar endereços **paginados** - **GET** -> `localhost:8080/api/endereco`

Buscar um endereço por id - **GET** -> `localhost:8080/api/endereco/{id}` passando um Id.

Atualizar um endereço - **PUT** -> `localhost:8080/api/endereco/{id}` passando um Id no parâmetro e um JSON semelhante acima.

Delete um endereço - **DELETE** -> `localhost:8080/api/endereco/{id}` passando um Id no parâmetro.

</details>

<details>
<summary>Empresa</summary>

Você irá realizar um cadastro de uma empresa a partir da URL `localhost:8080/api/empresa` para que possa ser feito um relatório futuramente, pois não é possível cadastrar um relaório sem Veículo e Empresa. O único usuário que pode manipular a entidade `Empresa` é o usuário com a permissão de `GERENTE`. 

Cadastrar uma Empresa - **POST** -> `localhost:8080/api/empresa`
```
{
    "nome" : "João Víctor",
    "cnpj" : "3333",
    "telefone" : [
        "33383383",
        "23883383"
    ],
    "vagasMoto" : 100,
    "vagasCarro" : 100,
    "idEndereco" : 1
    
}
```
Buscar empresas **paginadas** - **GET** -> `localhost:8080/api/empresa`

Buscar uma empresa por id - **GET** -> `localhost:8080/api/empresa/{id}` passando um Id.

Atualizar uma empresa - **PUT** -> `localhost:8080/api/empresa/{id}` passando um Id no parâmetro e um JSON semelhante acima.

Delete uma empresa - **DELETE** -> `localhost:8080/api/empresa/{id}` passando um Id no parâmetro.

</details>

<details>
<summary>Veículo</summary>

Você irá realizar um cadastro de um veículo a partir da URL `localhost:8080/api/veiculo` para que possa ser cadastrada um relatório futuramente, pois não é possível cadastrar um relatório sem veículo e empresa. Todos usuários podem manipular a entidade `Veículo` com limitações para alguns. 

Cadastrar um veículo - **POST** -> `localhost:8080/api/veiculo`. Pode ser feito por **todos usuários**.
```
{
    "marca" : "Fiat",
    "modelo" : "uno",
    "cor" : "Branco",
    "placa" : "ABC-123",
    "tipoDeVeiculo" : "CARRO"
}
```
Buscar veículos **paginados** - **GET** -> `localhost:8080/api/veiculo`. Pode ser feito por **GERENTE** e **FUNCIONÁRIO**.

Buscar um veículo por id - **GET** -> `localhost:8080/api/veiculo/{id}` passando um Id. Pode ser feito por **GERENTE** e **FUNCIONÁRIO**.

Atualizar um veículo - **PUT** -> `localhost:8080/api/veiculo/{id}` passando um Id no parâmetro e um JSON semelhante acima. Pode ser feito por **GERENTE** e **FUNCIONÁRIO**.

Delete um veículo - **DELETE** -> `localhost:8080/api/veiculo/{id}` passando um Id no parâmetro. Pode ser feito por **GERENTE** e **FUNCIONÁRIO**.

</details>
<details>
<summary>Relatório</summary>

Você irá realizar um cadastro de um relatório a partir da URL `localhost:8080/api/relatorio` para que possa ser feita a gestão de vagas e controle de entrada e saída. Apenas os usuários com permissões de **GERENTE** e **FUNCIONÁRIO** podem manipular a entidade `Relatório`.

Cadastrar um relatório - **POST** -> `localhost:8080/api/relatorio`. A partir do momento que essa requisição for feita, ela irá buscar a empresa que está presente na requisição e o tipo do veículo, e então, irá decrementar (subtrair) a vaga daquele tipo de veículo da empresa.
```
{
    "idEmpresa" : 1,
    "idVeiculo" : 1
}
```
Buscar relatórios **paginados** - **GET** -> `localhost:8080/api/relatorio/registro-geral`. Traz as informações gerais de um relatório.

Buscar um relatório por id - **GET** -> `localhost:8080/api/relatorio/registro-geral/{id}` passando um Id.

Finalizar um relatório - **PUT** -> `localhost:8080/api/relatorio/registra-saida/{id}` passando um Id no parâmetro. Após o relatório ser finalizado, a data de finalização será cadastrada naquele momento exato e a vaga do veículo que estava no relatório finalizado, será incrementada (somada), mostrando informando que o veículo já saiu do local.

Busca a **quantidade total** de **entradas e saídas** que ocorreram no sistema - **GET** -> `localhost:8080/api/relatorio/contagem-total/{id}` passando um Id como parâmetro.

Busca a **quantidade total** de **entradas e saídas por HORA** que ocorreram no sistema - **GET** -> `localhost:8080/api/relatorio/contagem-por-hora/{id}` passando um Id como parâmetro. Deve-se passar o **Id da empresa**, **horário de inicio** e **horário de fim** para que seja feita a busca da maneira correta, segue o exemplo:

![Imagem Exemplo](Requisicao_Por_Hora.png)

Delete um relatório - **DELETE** -> `localhost:8080/api/relatorio/{id}` passando um Id no parametro.

</details>
