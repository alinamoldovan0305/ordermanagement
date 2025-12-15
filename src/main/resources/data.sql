------------------------------------------------------
-- 1. UNITS OF MEASURE (10)
------------------------------------------------------
INSERT INTO units_of_measure (id, name, symbol) VALUES
(1, 'Bucata', 'buc'),
(2, 'Kilogram', 'kg'),
(3, 'Gram', 'g'),
(4, 'Litru', 'l'),
(5, 'Metru', 'm'),
(6, 'Centimetru', 'cm'),
(7, 'Mililitru', 'ml'),

-- SERVICE UNITS
(8, 'Sedinta', 'sed'),
(9, 'Abonament', 'ab'),
(10, 'Ora', 'h');


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
-- PRODUCTS
(1, 1, 1, 2),   -- Laptop – 2 buc
(2, 2, 1, 1),   -- Telefon – 1 buc
(3, 3, 1, 5),   -- Casti – 5 buc
(4, 6, 1, 2),   -- Monitor – 2 buc
(5, 4, 1, 4),   -- Tastatura – 4 buc

-- SERVICES
(6, 11, 10, 10), -- Instalare – 10 ore
(7, 12, 10, 6),  -- Consultanta – 6 ore
(8, 13, 8, 3),  -- Mentenanta – 3 sedinte
(9, 15, 8, 2),  -- Diagnosticare – 2 sedinte
(10, 16, 9, 1); -- Training – 1 abonament



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
-- PRODUCTS
(1, 1, 1, 1),   -- Laptop – 1 buc
(2, 2, 1, 1),   -- Telefon – 1 buc
(3, 3, 1, 3),   -- Casti – 3 buc
(4, 6, 1, 2),   -- Monitor – 2 buc
(5, 5, 1, 4),   -- Mouse – 4 buc

-- SERVICES
(6, 11, 10, 8),  -- Instalare – 8 ore
(7, 12, 10, 6),  -- Consultanta – 6 ore
(8, 16, 9, 1),  -- Training – abonament
(9, 18, 10, 12), -- Suport Tehnic – 12 ore
(10, 15, 8, 2); -- Diagnosticare – 2 sedinte
