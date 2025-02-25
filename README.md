# DCC061
Projeto realizado durante a disciplina DCC061 - Engenharia de Software.

# Como rodar o servidor do banco de dados
- Certifique-se que você está na pasta reveste
- Execute o comando sudo docker-compose up -d ou docker-compose up -d
- Abra uma página no seu navegador e digite localhost:8082
- Em JDBC URL, cole jdbc:h2:tcp://localhost:1521//opt/h2-data/ecommerce
- Clique em conectar

# Como desligar o servidor do banco de dados
- Certifique-se que você está na pasta reveste
- Execute o comando sudo docker-compose down ou docker-compose down

# Como rodar o servidor do sistema
- Em RevesteApplication, clique em Run, que fica logo acima de 	
    "public static void main(String[] args) {
		SpringApplication.run(RevesteApplication.class, args);
	}"