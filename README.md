# 🕸️ PMX-Net (Project Manager Experience Network)

O projeto **PMX-Net(Project Manager Experience Network)** consiste no desenvolvimento de uma aplicação Web que permite inserir dados, mapear, visualizar e analisar as redes de comunicação entre membros de equipes de projeto, com base em dados reais ou simulados de interações (reuniões, mensagens, tarefas conjuntas, commits, etc.). A ferramenta permitirá: Criar ou importar dados de interações de ferramentas, criar projetos, equipes, pessoas, ferramentas, etc, visualizar as relações como um grafo: nós representam pessoas e arestas representam interações, calcular métricas de centralidade, nós isolados, pontes de informação, etc., registrar feedback qualitativo sobre interações e gerar alertas visuais sobre pontos críticos de comunicação.

---

## Estrutura do Projeto

O projeto é dividido em duas pastas repositórios principais:

### `backend/`
- API REST construída com Spring Boot
- Persistência com SQLite

### `front-end/`
- Desenvolvido em Vue.js com Vuetify
- Visualização interativa com Cytoscape.js e Chart.js
- Formulários de cadastro e consulta

---

## Tecnologias Utilizadas

| Camada       | Tecnologias                                 |
|--------------|---------------------------------------------|
| Backend      | Spring Boot, JPA (Hibernate), SQLite        |
| Frontend     | Vue.js 3, Vuetify 3, Chart.js, Cytoscape.js |

---

## Arquivos do Repositório

| Arquivo/Folder            | Descrição                                     |
|---------------------------|-----------------------------------------------|
| `backend/`                | Código-fonte da API REST                      |
| `front-end/`              | Código-fonte do front-end Vue.js              |
| `.gitignore`              | Arquivos e pastas ignorados pelo Git         |
| `README.md`               | Documentação do projeto (este arquivo)        |
| `pmx-net-diagrams.vpp`    | Diagrama UML do sistema (Visual Paradigm)     |

---


## Para rodar o projeto

## Backend

´´´
> mvn clean install
> mvn spring-boot:run
´´´´


---



