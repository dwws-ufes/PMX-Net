

# Projeto Ontop com SQLite

Uses ontop project


## 📌 Pré-requisitos
- Docker instalado
- Seu `pmxnet.db` na mesma pasta

## 📦 Estrutura
- `mapping.ttl` — mapeamento R2RML
- `ontology.properties` — configura JDBC

## 🚀 Rodar com Docker

1️⃣ Coloque `pmxnet.db` na mesma pasta.  
2️⃣ Execute:


docker-compose build up 

Baixar o RDF completo. 
http://localhost:8080/sparql?query=CONSTRUCT%20%7B%20%3Fs%20%3Fp%20%3Fo%20.%20%7D%20WHERE%20%7B%20%3Fs%20%3Fp%20%3Fo%20.%20%7D


http://localhost:8080/sparql?query=CONSTRUCT { ?s ?p ?o . } WHERE { ?s ?p ?o . }



# Buscando dados de um participante:

Nome e mail

PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?participante ?email WHERE {
  ?participante a foaf:Person ;
                foaf:name "Felipe" ;
   foaf:mbox ?email .

}

# Consulta a bases externas. 
# em cima de schema ou instanicas, tem que ser w3c. 

