# ProjetoFinalBack

Projeto de Emprestimo em Java Spring para cadastrar cliente e solicitar emprestimos.

Projeto Desenvolvido junto com o programa Jovem Profissional e 40+ | Trainee de Sistemas Java e Angular, da empresa Minsait.

Instrutor Gleyser Bomfim Guimarães.

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

JDK 1.8 ou superior<br /> 
Maven 3.2 ou superior<br />
Spring Boot 2.0 ou superior<br />
O aplicativo estará disponível em http://localhost:8080.

## Endpoints

Cliente:
POST - http://localhost:8080/clientes<br />
GET - http://localhost:8080/clientes<br />
GET - http://localhost:8080/clientes/:cpf<br />
PUT - http://localhost:8080/clientes/:cpf<br />
DELETE - http://localhost:8080/clientes/:cpf<br />

Emprestimo:

POST - http://localhost:8080/clientes/emprestimos<br />
GET - http://localhost:8080/clientes/emprestimos<br />
GET - http://localhost:8080/clientes/:cpf/emprestimos/:id<br />
DELETE - http://localhost:8080/clientes/:cpf/emprestimo/:id<br />
