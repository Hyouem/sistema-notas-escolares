# Sistema de Notas Escolares

Sistema de gerenciamento de notas escolares desenvolvido
em Java 25, com interface de linha de comando (CLI).

## Funcionalidades

- Cadastro de alunos com matricula unica
- Registro de tres notas por aluno
- Validacao de notas entre 0 e 10
- Calculo da media aritmetica
- Classificacao do desempenho escolar
- Listagem de alunos
- Consulta de boletim individual
- Tratamento de entradas e consultas invalidas

## Regras de classificacao

| Media | Situacao |
|---|---|
| Maior ou igual a 7 | APROVADO |
| Maior ou igual a 5 e menor que 7 | RECUPERACAO |
| Menor que 5 | REPROVADO |

## Tecnologias

- Java 25
- Apache Maven
- JUnit 5
- GitHub Actions

## Arquitetura

- `Aluno`: entidade, notas, validacoes e media
- `SituacaoAluno`: enumeracao das classificacoes
- `AlunoService`: cadastro e consulta de alunos
- `App`: interface de terminal

## Executar

Requisitos: JDK 25 e Maven.

```bash
mvn clean verify
java -cp target/classes br.com.portfolio.notas.App
```

## Testes

Execute:

```bash
mvn clean verify
```

O projeto possui testes unitarios e de regressao
para validacoes, calculo de medias, cadastro,
duplicidade e tratamento de consultas inexistentes.

## Limitacoes

Os dados sao armazenados em memoria e nao persistem
apos o encerramento da aplicacao.

## Possiveis evolucoes

- Persistencia em CSV ou banco de dados
- Cadastro de disciplinas
- Historico de notas
- Exportacao de boletins
- API REST com Spring Boot
