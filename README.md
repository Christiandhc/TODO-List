# TODO List

Uma aplicação de lista de tarefas (TODO list) desenvolvida em Java. Este projeto permite criar, atualizar, remover e listar tarefas, além de visualizar o número de tarefas por status.

## Funcionalidades

- **Adicionar Tarefa**: Insira novas tarefas com nome, descrição, data de término, prioridade, categoria e status.
- **Remover Tarefa**: Exclua tarefas existentes pelo nome.
- **Listar Tarefas**: Visualize todas as tarefas cadastradas.
- **Listar Tarefas por Categoria**: Filtre as tarefas pela categoria.
- **Listar Tarefas por Prioridade**: Filtre as tarefas pela prioridade.
- **Listar Tarefas por Status**: Filtre as tarefas pelo status (TODO, DOING, DONE).
- **Consultar Número de Atividades**: Veja a contagem de tarefas por status.
- **Atualizar Tarefa**: Modifique as informações de uma tarefa existente.

## Tecnologias

- **Java**: Linguagem de programação utilizada.
- **CSV**: Formato utilizado para persistência de dados.

## Estrutura do Projeto

- **src/**: Código-fonte da aplicação.
  - **controle/**: Contém a classe `GerenciadorTarefas`, responsável pelo gerenciamento das tarefas.
  - **molde/**: Contém a classe `Tarefa` e `Status`, que definem a estrutura das tarefas e seus estados.
  - **principal/**: Contém a classe `Main`, que executa a aplicação e interage com o usuário.
