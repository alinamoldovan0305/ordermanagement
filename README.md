Acest proiect reprezintă prima etapă dintr-o aplicație de gestiune a comenzilor, contractelor și produselor, creata in Java Spring Boot. Am construit structura de baza a apllicatiei respectand principiile OOP, arhitectura MVC si organizarea pe straturi Model - Repository - Service - Controller.

Model (Carla) - contine clase care definesc entitatile din diagrama : 
- Customer 
- Order, OrderLine
- Contract, ContractLine, ContractType
- SellableItem, Product, Service
- UnitsOfMeasure
  Clasele respecta principiile OOP(atribute private, getters/setters)
Alina:
- metode simple (toString, isinStock, increaseQuantity ...)
- cate doua atribute pentru trei clase diferite
    Product: category, stock
    Customer: email, phonenumber
    Order: orderDate, delivered

Repository (Alina) - fiecare entitate are asociat un repository care contine metodele CRUD(Create, Read, Update, Delete) de baza:
- save(T entity)
- findAll()
- findById(String id)
- delete(String id)
- update(String id, T updatedEntity)-Carla

Service (Alina) - contine logica de business pentru fiecare entitate. Fiecare serviciu comunica cu repository-ul corespunzator si implementeaza metode simple:
- save(T entity)
- getAll()
- getById(String id)
- delete()
- udate(String id, T updatedEntity)

Controller (Carla) - reprezinta interfata aplicatiei. Au fost implementate 3 controller-uri: unul de test si cele doua relevante pentru entitatile de baza ale aplicatiei:
- HelloController - endpoint de test
- CustomerController - CRUD
- ProductController - CRUD

PRINCIPII DE BAZA:

OOP:
- Incapsulare (atribute private + getteri/setteri)
- Moștenire (SellableItem → Product, Service)
- Compoziție (Customer → List<Order>, Contract → List<ContractLine>)

SOLID:
- Single Responsibility: fiecare clasă are un rol clar.
- Open/Closed: codul este extensibil (ex. pot fi adăugate noi tipuri de entități).
- Liskov Substitution: clasele derivate (Product, Service) pot înlocui baza (SellableItem).
- Interface Segregation: repository-urile oferă doar operațiile de bază.
- Dependency Inversion: (poate fi îmbunătățit ulterior prin @Autowired și interfețe).
