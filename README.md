## 💻 Sobre o projeto

O projeto foca em um CRUD basico de usuário e CNPJ com consulta ao webservice : https://receitaws.com.br/v1/cnpj/{CNPJ}

---

## ⚙️ Funcionalidades

- [x] CRUD de usuário;
- [x] Consulta ao webservice para cadastrar cnpj;

---

## 📄 Documentação

Após iniciar o projeto, a documentação dos endpoints poderá ser acessada via browser no endereço: {url do projeto}/swagger-ui/index.html
contendo o detalhamento dos endpoints.

Modelo de json para POST
/cadastro
{
	"name":"usuário de teste",
	"email":"teste@gmail.com",
	"password":"senha@123",
	"cnpj":"13995981000104"
}

O sistema considera o email cadastrado como username.

---
## 🛠 Configurações

O Banco de dados utilizado foi o MySQL
database: desafiobf
username: root
password: root

---

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[MySQL](https://www.mysql.com)**
- **[Hibernate](https://hibernate.org)**
- **[Lombok](https://projectlombok.org)**


