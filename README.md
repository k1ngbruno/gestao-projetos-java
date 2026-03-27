# Sistema de Gestão de Projetos e Equipes

Projeto acadêmico desenvolvido para a atividade A3 da disciplina **Programação de Soluções Computacionais**, do curso de **Big Data e Inteligência Analítica** da **Universidade São Judas**.

## Sobre o projeto

Este sistema foi desenvolvido em **Java** com foco nos conceitos de **Programação Orientada a Objetos (POO)**, organização em camadas inspirada no padrão **MVC** e aplicação de boas práticas de desenvolvimento.

A proposta do projeto é auxiliar no controle de **projetos**, **equipes**, **usuários** e **tarefas**, permitindo uma gestão mais organizada das atividades e do andamento dos projetos.

## Funcionalidades

- Cadastro de usuários
- Definição de perfis de acesso:
  - Administrador
  - Gerente
  - Colaborador
- Cadastro de equipes
- Vinculação de membros às equipes
- Cadastro de projetos
- Definição de gerente responsável
- Alocação de equipes em projetos
- Cadastro de tarefas por projeto
- Atualização de status de projetos e tarefas
- Relatório simples de acompanhamento
- Validação de dados e tratamento de exceções

## Tecnologias utilizadas

- Java
- Programação Orientada a Objetos
- Estrutura inspirada no padrão MVC
- SQL (modelo de banco incluído no projeto)

## Estrutura do projeto

```text
src/
 └── br/com/a3/gestaoprojetos/
     ├── controller/
     ├── exception/
     ├── model/
     ├── repository/
     ├── util/
     ├── view/
     └── Main.java
```

## Estrutura principal

- `model`: classes de domínio do sistema
- `controller`: regras e controle das operações
- `repository`: armazenamento em memória
- `view`: interação via menu no console
- `exception`: exceções personalizadas
- `util`: validações auxiliares

## Como executar

### Compilar
No terminal, dentro da pasta do projeto:

```bash
javac -d out $(find src -name "*.java")
```

### Executar
```bash
java -cp out br.com.a3.gestaoprojetos.Main
```

## Banco de dados

A pasta `database` contém os arquivos:

- `schema.sql`
- `sample_data.sql`

Esses arquivos representam a base inicial para futura integração com banco de dados relacional.

## Observações

- Este projeto foi desenvolvido como um **MVP acadêmico**.
- A execução atual ocorre em **modo console**.
- A estrutura foi organizada para facilitar futuras melhorias, como integração real com banco de dados e interface gráfica.

## Autor

**Bruno de Abreu**  
Atividade A3 - Programação de Soluções Computacionais  
Universidade São Judas
