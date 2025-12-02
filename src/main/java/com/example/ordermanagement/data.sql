------------------------------------------------------
-- CONTRACT TYPES (10)
------------------------------------------------------
INSERT INTO contract_types (name) VALUES
('Standard'),
('Premium'),
('Enterprise'),
('Annual'),
('Monthly'),
('Quarterly'),
('Basic'),
('Gold'),
('Silver'),
('Ultimate');

------------------------------------------------------
-- CUSTOMERS (10)
------------------------------------------------------
INSERT INTO customers (name, email, phone) VALUES
('John Doe', 'john@example.com', '0700000001'),
('Alice Pop', 'alice@example.com', '0700000002'),
('TechCorp SRL', 'techcorp@example.com', '0712345678'),
('MediaVision', 'contact@mediavision.ro', '0723456789'),
('GreenFarm', 'office@greenfarm.ro', '0734567890'),
('BlueSoft', 'info@bluesoft.ro', '0756789012'),
('NovaPrint', 'print@nova.ro', '0765432198'),
('RedBox SRL', 'red@box.ro', '0743219876'),
('CityLogistics', 'support@citylog.ro', '0721987654'),
('DigitalPro', 'hello@digitalpro.ro', '0709876543');

------------------------------------------------------
-- UNITS OF MEASURE (10)
------------------------------------------------------
INSERT INTO units_of_measure (name, symbol) VALUES
('Kilogram', 'kg'),
('Gram', 'g'),
('Litru', 'l'),
('Mililitru', 'ml'),
('Metru', 'm'),
('Centimetru', 'cm'),
('Bucată', 'pcs'),
('Cutie', 'box'),
('Pachet', 'pkt'),
('Oră', 'h');

------------------------------------------------------
-- SELLABLE ITEMS (10 total: 5 PRODUCT + 5 SERVICE)
------------------------------------------------------

-- PRODUCTS (5)
INSERT INTO sellable_item (item_type, name, value, category, stock)
VALUES
('PRODUCT', 'Laptop', 3500, 'Electronics', 20),
('PRODUCT', 'Monitor', 700, 'Electronics', 50),
('PRODUCT', 'Mouse', 75, 'Accessories', 200),
('PRODUCT', 'Keyboard', 150, 'Accessories', 120),
('PRODUCT', 'Desk Chair', 450, 'Furniture', 30);

-- SERVICES (5)
INSERT INTO sellable_item (item_type, name, status)
VALUES
('SERVICE', 'IT Maintenance', 'ACTIVE'),
('SERVICE', 'Cleaning', 'INACTIVE'),
('SERVICE', 'Consulting', 'ACTIVE'),
('SERVICE', 'Training', 'ACTIVE'),
('SERVICE', 'Delivery', 'SUSPENDED');

------------------------------------------------------
-- CONTRACTS (10)
------------------------------------------------------
INSERT INTO contracts (name, customer_id, contract_type_id, status)
VALUES
('Contract 1', 1, 1, 'ACTIVE'),
('Contract 2', 2, 2, 'ACTIVE'),
('Contract 3', 3, 3, 'ACTIVE'),
('Contract 4', 4, 4, 'INACTIVE'),
('Contract 5', 5, 5, 'ACTIVE'),
('Contract 6', 6, 6, 'INACTIVE'),
('Contract 7', 7, 7, 'ACTIVE'),
('Contract 8', 8, 8, 'ACTIVE'),
('Contract 9', 9, 9, 'SUSPENDED'),
('Contract 10', 10, 10, 'ACTIVE');

------------------------------------------------------
-- CONTRACT LINES (10)
------------------------------------------------------
INSERT INTO contract_lines (contract_id, item_id, unit_id, quantity)
VALUES
(1, 1, 1, 5),
(2, 2, 2, 10),
(3, 3, 3, 2),
(4, 4, 4, 8),
(5, 5, 5, 1),
(6, 6, 6, 6),
(7, 7, 7, 3),
(8, 8, 8, 9),
(9, 9, 9, 4),
(10, 10, 10, 7);

------------------------------------------------------
-- ORDERS (10)
------------------------------------------------------
INSERT INTO orders (name, customer_id, contract_id, order_date, delivered)
VALUES
('Order 1', 1, 1, '2025-01-01', false),
('Order 2', 2, 2, '2025-01-02', true),
('Order 3', 3, 3, '2025-01-03', false),
('Order 4', 4, 4, '2025-01-04', true),
('Order 5', 5, 5, '2025-01-05', false),
('Order 6', 6, 6, '2025-01-06', true),
('Order 7', 7, 7, '2025-01-07', false),
('Order 8', 8, 8, '2025-01-08', true),
('Order 9', 9, 9, '2025-01-09', false),
('Order 10', 10, 10, '2025-01-10', true);

------------------------------------------------------
-- ORDER LINES (10)
------------------------------------------------------
INSERT INTO order_lines (order_id, item_id, unit_id, quantity)
VALUES
(1, 1, 1, 1),
(2, 2, 2, 2),
(3, 3, 3, 3),
(4, 4, 4, 4),
(5, 5, 5, 5),
(6, 6, 6, 6),
(7, 7, 7, 7),
(8, 8, 8, 8),
(9, 9, 9, 9),
(10, 10, 10, 10);
