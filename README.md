# Sistema de Gestão de Projetos e Equipes

Projeto acadêmico em Java desenvolvido do zero, com foco em Programação Orientada a Objetos e organização inspirada no padrão MVC.

## Funcionalidades

- Cadastro de usuários com perfis:
  - Administrador
  - Gerente
  - Colaborador
- Cadastro de equipes
- Vinculação de membros às equipes
- Cadastro de projetos com gerente responsável
- Alocação de equipes em projetos
- Cadastro de tarefas por projeto
- Atualização de status de projeto e tarefa
- Relatório simples de acompanhamento do projeto
- Busca, ordenação e tratamento de exceções

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

## Observações

- Esta versão foi criada como MVP acadêmico e funciona em modo console.
- A estrutura já está preparada para evolução futura com persistência em banco de dados e interface gráfica.
- O diretório `database` inclui um modelo SQL que pode ser usado em uma próxima etapa do projeto.

## Sugestão para GitHub

1. Crie um repositório público.
2. Envie a pasta completa do projeto.
3. Adicione este `README.md`.
4. Se quiser, inclua prints da execução no repositório.
