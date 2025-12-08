README – Projektaufgabe 4
Persistență cu MySQL, Relații între Entități, Validări și Thymeleaf
Acest proiect reprezintă a patra etapă a aplicației de gestiune a comenzilor, contractelor și produselor, dezvoltată în Java Spring Boot. După primele faze ale proiectului, unde structura de bază și logica CRUD au fost implementate folosind repository-uri pe fișiere,
această etapă migrează complet aplicația către o arhitectură reală bazată pe MySQL și Spring Data JPA.
Proiectul extinde funcționalitățile existente, păstrând principiile OOP, arhitectura MVC și organizarea pe straturi Model – Repository – Service – Controller.

Obiectivele acestei etape
Eliminarea repository-urilor pe fișiere
Persistență completă în MySQL
Implementarea relațiilor reale între entități (OneToMany și ManyToOne)
Implementarea validărilor backend (field level și business)
Afișarea erorilor în UI folosind Thymeleaf
Inițializarea automată a bazei de date cu fișierul data.sql
CRUD complet funcțional pentru toate entitățile
Interfață web completă cu formulare, liste și pagini de detalii

Model
Entitățile au fost mapate pe tabele MySQL folosind JPA. Fiecare clasă include adnotările @Entity, @Table, @Id, @GeneratedValue și relațiile corespunzătoare între entități. De asemenea, conțin validări pentru câmpuri, cum ar fi @NotBlank, @NotNull, @Positive, @Size sau @Email.

Entitățile proiectului:
Customer
Order, OrderLine
Contract, ContractLine, ContractType
SellableItem, Product, Service
UnitsOfMeasure

Relațiile implementate în această etapă:
Contract (1) – (N) ContractLine
ContractLine (N) – (1) SellableItem
ContractLine (N) – (1) UnitsOfMeasure
Principiile OOP sunt respectate: încapsulare, moștenire, compoziție și polimorfism.

Repository
Repository-urile pe fișiere au fost eliminate. Acum se folosesc repository-uri JPA, de forma:
public interface ContractRepository extends JpaRepository<Contract, Long> {}
Acestea oferă operațiile CRUD automat, asigură integrarea cu MySQL și permit crearea de metode personalizate.

Service
Serviciile gestionează logica de business și comunică între controller și repository. În acest strat sunt implementate și validările de business, cum ar fi verificarea existenței entităților, prevenirea duplicatelor sau validarea relațiilor.

Controller
Controller-ele gestionează interfața web și comunicarea cu utilizatorul. Fiecare controller oferă metode pentru:
afișarea listelor
crearea de entități
editare
ștergere
afișarea detaliilor
Erorile sunt trimise înapoi în același formular și nu se afișează Whitelabel Error Page.
Interfața Web – Thymeleaf

Pentru fiecare entitate există pagini:
index.html
form.html
details.html

Thymeleaf este folosit pentru:
afișarea câmpurilor
popularea listelor
validări în UI
afișarea erorilor cu th:errors și #fields.hasErrors
Interfața reflectă relațiile dintre entități, de exemplu afișarea contractelor și liniilor asociate.

Validări
A. Validări de câmp (Field-Level):
Acestea sunt realizate în clasele entităților folosind adnotări precum:
@NotBlank
@NotNull
@Positive
@Size
@Email
@Past / @Future
B. Validări de business (Backend Logic):
Aceste validări sunt implementate în serviciile aplicației și includ:
verificarea relațiilor dintre entități
prevenirea salvării unor date invalide
oprirea operațiilor care ar duce la inconsistențe în baza de date
Erorile sunt trimise în UI și afișate în formular.
Persistență MySQL
Conectarea la baza de date se face în application.properties.
Schema este creată folosind:
spring.jpa.hibernate.ddl-auto=create-drop
Inițializare automată – data.sql
Baza de date este populată automat la pornirea aplicației prin fișierul data.sql, aflat în:
src/main/resources/data.sql
Acesta conține cel puțin 10 înregistrări pentru fiecare entitate principală, conform cerinței.