------------------------------------------------------
-- 1. UNITS OF MEASURE (10)
------------------------------------------------------
INSERT INTO units_of_measure (name, symbol) VALUES
('Bucata', 'buc'),
('Kilogram', 'kg'),
('Gram', 'g'),
('Litru', 'l'),
('Metru', 'm'),
('Centimetru', 'cm'),
('Pachet', 'pct'),
('Sac', 'sac'),
('Milimetru', 'mm'),
('Mililitru', 'ml');

------------------------------------------------------
-- 2. SELLABLE ITEMS (SINGLE TABLE INHERITANCE)
------------------------------------------------------

-- PRODUCTS (ID 1–10)
INSERT INTO sellable_item (item_type, name, value, category, stock)
VALUES
('PRODUCT', 'Laptop', 120, 'Electronice', 3),
('PRODUCT', 'Telefon', 80, 'Electronice', 2),
('PRODUCT', 'Casti', 30, 'Accesorii', 6),
('PRODUCT', 'Tastatura', 25, 'Accesorii', 10),
('PRODUCT', 'Mouse', 15, 'Accesorii', 14),
('PRODUCT', 'Monitor', 150, 'Electronice', 5),
('PRODUCT', 'Imprimanta', 95, 'Electronice', 3),
('PRODUCT', 'Rucsac Laptop', 40, 'Accesorii', 7),
('PRODUCT', 'Powerbank', 22, 'Accesorii', 8),
('PRODUCT', 'Tableta', 110, 'Electronice', 4);

-- SERVICES (ID 11–19)
INSERT INTO sellable_item (item_type, name, status)
VALUES
('SERVICE', 'Instalare', 'ACTIVE'),
('SERVICE', 'Consultanta', 'ACTIVE'),
('SERVICE', 'Mentenanta', 'ACTIVE'),
('SERVICE', 'Configurare', 'ACTIVE'),
('SERVICE', 'Diagnosticare', 'DOWN'),
('SERVICE', 'Training', 'ACTIVE'),
('SERVICE', 'Audit Tehnic', 'DOWN'),
('SERVICE', 'Suport Tehnic', 'ACTIVE'),
('SERVICE', 'Optimizare', 'DOWN');


------------------------------------------------------
-- 3. CONTRACT TYPES (10)
------------------------------------------------------
INSERT INTO contract_types (name) VALUES
('Standard'),
('Premium'),
('Basic'),
('Enterprise'),
('Custom'),
('Trial'),
('Gold'),
('Silver'),
('Bronze'),
('VIP');


------------------------------------------------------
-- 4. CUSTOMERS (10)
------------------------------------------------------
INSERT INTO customers (name, currency, email, phonenumber)
VALUES
('Alina', 'EUR', 'alina@mail.com', '0712345678'),
('Andrei', 'USD', 'andrei@mail.com', '0712345679'),
('Maria', 'EUR', 'maria@mail.com', '0712345680'),
('Ioan', 'GBP', 'ioan@mail.com', '0712345681'),
('Elena', 'EUR', 'elena@mail.com', '0712345682'),
('Stefan', 'USD', 'stefan@mail.com', '0712345683'),
('Andreea', 'EUR', 'ana@mail.com', '0712345684'),
('Mihai', 'GBP', 'mihai@mail.com', '0712345685'),
('Roxana', 'EUR', 'roxana@mail.com', '0712345686'),
('David', 'RON', 'david@mail.com', '0745837121');


------------------------------------------------------
-- 5. CONTRACTS (10)
------------------------------------------------------
INSERT INTO contracts (name, customer_id, contract_type_id, status)
VALUES
('Contract A', 1, 1, 'ACTIVE'),
('Contract B', 2, 2, 'DOWN'),
('Contract C', 3, 3, 'ACTIVE'),
('Contract D', 4, 4, 'ACTIVE'),
('Contract E', 5, 5, 'DOWN'),
('Contract F', 6, 6, 'DOWN'),
('Contract G', 7, 7, 'ACTIVE'),
('Contract H', 8, 8, 'DOWN'),
('Contract I', 9, 9, 'ACTIVE'),
('Contract J', 10, 10, 'ACTIVE');


------------------------------------------------------
-- 6. CONTRACT LINES (10)
------------------------------------------------------
INSERT INTO contract_lines (contract_id, item_id, unit_id, quantity)
VALUES
(1, 1, 1, 2),
(2, 2, 2, 5),
(3, 3, 3, 1),
(4, 4, 4, 3),
(5, 5, 5, 7),
(6, 6, 6, 4),
(7, 7, 7, 6),
(8, 8, 8, 2),
(9, 9, 9, 8),
(10, 10, 10, 9);


----------------------------------------------------
 --7. ORDERS (10)
----------------------------------------------------
INSERT INTO orders (name, customer_id, contract_id, order_date, delivered)
VALUES
('Comanda Carte', 1, 1, '2025-01-15', false),
('Comanda Laptop', 2, 2, '2025-02-10', true),
('Comanda Instalare', 3, 1, '2025-01-20', false),
('Comanda Pachet Premium', 1, 2, '2025-03-01', false),
('Comanda Telefon', 2, 1, '2025-02-25', true),
('Comanda Reparatie Laptop', 3, 3, '2025-01-30', false),
('Comanda Accesorii', 1, 1, '2025-02-12', false),
('Comanda Abonament Servicii', 2, 3, '2025-03-05', true),
('Comanda Business', 3, 2, '2025-03-10', false),
('Comanda Intretinere', 1, 3, '2025-01-05', true);


------------------------------------------------------
-- 8. ORDER LINES (10)
------------------------------------------------------
INSERT INTO order_lines (order_id, item_id, unit_id, quantity)
VALUES
(1, 1, 1, 1),
(2, 11, 2, 2),
(3, 2, 3, 3),
(4, 12, 1, 4),
(5, 3, 2, 5),
(6, 13, 3, 1),
(7, 1, 2, 2),
(8, 11, 3, 3),
(9, 2, 1, 4),
(10, 12, 3, 5);
