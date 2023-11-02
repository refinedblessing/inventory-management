-- Clear all Data before adding new data
-- DROP and recreate your tables by setting spring.jpa.hibernate.ddl-auto=create-drop in
-- your application properties file and running & shutting down the app before running this script
-- make sure to shut down the app, then reset to update
-- spring.jpa.hibernate.ddl-auto=update before executing this script

-- Create data for 10 suppliers, 10 categories, and 20 items
-- Supplier 1
INSERT INTO supplier (name, address, email, phone) VALUES
    ('Acme Electronics', '123 Main Street, Suite 101', 'acme@example.com', '+12345678901');

-- Category for Supplier 1
INSERT INTO category (name, supplier_id) VALUES
    ('Consumer Electronics', 1);

-- Items for Category A
INSERT INTO item (name, short_description, price, quantity, category_id) VALUES
    ('Smartphone', 'Latest smartphone with advanced features', 499.99, 50, 1),
    ('Tablet', 'High-resolution tablet with fast performance', 349.99, 30, 1);

-- Supplier 2
INSERT INTO supplier (name, address, email, phone) VALUES
    ('Fashion Trends Inc.', '456 Fashion Avenue', 'fashion@example.com', '+23456789012');

-- Category for Supplier 2
INSERT INTO category (name, supplier_id) VALUES
    ('Clothing', 2);

-- Items for Category B
INSERT INTO item (name, short_description, price, quantity, category_id) VALUES
    ('Men\'s Suit', 'Elegant men\'s suit for formal occasions', 199.99, 20, 2),
    ('Women\'s Dress', 'Stylish women\'s dress for special events', 149.99, 25, 2);

-- Supplier 3
INSERT INTO supplier (name, address, email, phone) VALUES
    ('Deluxe Electronics', '123 Technology Park, Suite 101', 'deluxe.electronics@example.com', '+12345678903');

-- Category for Supplier 3
INSERT INTO category (name, supplier_id) VALUES
    ('Electronics', 3);

-- Items for Electronics
INSERT INTO item (name, short_description, price, quantity, category_id) VALUES
    ('Smartphone X1', 'High-quality smartphone with advanced features', 599.99, 50, 3),
    ('Tablet Pro', 'Powerful tablet for productivity and entertainment', 399.99, 35, 3);

-- Supplier 4
INSERT INTO supplier (name, address, email, phone) VALUES
    ('FashionFusion Apparel', '789 Fashion Avenue, Suite 202', 'fashionfusion@example.com', '+12345678904');

-- Category for Supplier 4
INSERT INTO category (name, supplier_id) VALUES
    ('Apparel', 4);

-- Items for Apparel
INSERT INTO item (name, short_description, price, quantity, category_id) VALUES
    ('Men\'s Business Suit', 'Elegant business suit for men', 299.99, 30, 4),
    ('Women\'s Casual Dress', 'Comfortable and stylish dress for women', 79.99, 70, 4);

-- Supplier 5
INSERT INTO supplier (name, address, email, phone) VALUES
    ('GreenLife Foods', '456 Garden Street, Suite 301', 'greenlife@example.com', '+12345678905');

-- Category for Supplier 5
INSERT INTO category (name, supplier_id) VALUES
    ('Organic Vegetables', 5);

-- Items for Organic Vegetables
INSERT INTO item (name, short_description, price, quantity, category_id) VALUES
    ('Organic Carrots (1 lb)', 'Fresh and healthy organic carrots', 2.99, 200, 5),
    ('Organic Spinach (8 oz)', 'Nutrient-rich organic spinach', 3.49, 150, 5);

-- Supplier 6
INSERT INTO supplier (name, address, email, phone) VALUES
    ('SweetTooth Treats', '789 Confectionery Lane, Suite 102', 'sweettooth@example.com', '+12345678906');

-- Category for Supplier 6
INSERT INTO category (name, supplier_id) VALUES
    ('Desserts and Sweets', 6);

-- Items for Desserts and Sweets
INSERT INTO item (name, short_description, price, quantity, category_id) VALUES
    ('Chocolate Chip Cookies (12-pack)', 'Delicious chocolate chip cookies', 7.99, 100, 6),
    ('Assorted Gummy Bears (1 lb)', 'A variety of tasty gummy bear flavors', 4.99, 300, 6);

-- Supplier 7
INSERT INTO supplier (name, address, email, phone) VALUES
    ('Sunrise Dairy Farm', '1010 Dairy Road, Suite 210', 'sunrisedairy@example.com', '+12345678907');

-- Category for Supplier 7
INSERT INTO category (name, supplier_id) VALUES
    ('Dairy Products', 7);

-- Items for Dairy Products
INSERT INTO item (name, short_description, price, quantity, category_id) VALUES
    ('Fresh Milk (1 gallon)', 'Farm-fresh and nutritious milk', 3.99, 50, 7),
    ('Greek Yogurt (32 oz)', 'Thick and creamy Greek yogurt', 2.49, 100, 7);

-- Supplier 8
INSERT INTO supplier (name, address, email, phone) VALUES
    ('Glam Beauty Essentials', '123 Beauty Blvd, Suite 501', 'glambeauty@example.com', '+12345678908');

-- Category for Supplier 8
INSERT INTO category (name, supplier_id) VALUES
    ('Skin Care Products', 8);

-- Items for Skin Care Products
INSERT INTO item (name, short_description, price, quantity, category_id) VALUES
    ('Hydrating Face Serum (1 oz)', 'Revitalize your skin with this hydrating serum', 24.99, 100, 8),
    ('Anti-Aging Eye Cream (0.5 oz)', 'Reduce fine lines and wrinkles with this eye cream', 19.99, 75, 8);

-- Supplier 9
INSERT INTO supplier (name, address, email, phone) VALUES
    ('Luxe Hair Creations', '789 Beauty Avenue, Suite 303', 'luxehair@example.com', '+12345678909');

-- Category for Supplier 9
INSERT INTO category (name, supplier_id) VALUES
    ('Hair Care Products', 9);

-- Items for Hair Care Products
INSERT INTO item (name, short_description, price, quantity, category_id) VALUES
    ('Moisturizing Shampoo (16 oz)', 'Nourish your hair with this moisturizing shampoo', 9.99, 200, 9),
    ('Styling Gel (8 oz)', 'Achieve your desired hairstyle with this styling gel', 5.49, 150, 9);

-- Supplier 10
INSERT INTO supplier (name, address, email, phone) VALUES
    ('Fragrance Haven', '101 Scent Street, Suite 401', 'fragrancehaven@example.com', '+12345678910');

-- Category for Supplier 10
INSERT INTO category (name, supplier_id) VALUES
    ('Fragrances and Perfumes', 10);

-- Items for Fragrances and Perfumes
INSERT INTO item (name, short_description, price, quantity, category_id) VALUES
    ('Eau de Parfum (1.7 oz)', 'Experience a delightful and lasting fragrance', 39.99, 50, 10),
    ('Aromatic Cologne (3 oz)', 'Create a lasting impression with this aromatic cologne', 29.99, 75, 10);


-- Create 10 stores
-- Store 1
INSERT INTO store (name, address, email, phone, type, opening_date) VALUES
    ('MegaMart Retail', '123 Main Street, City1', 'info@megamartretail.com', '+12345678901', 'RETAIL', '2023-01-15');

-- Store 2
INSERT INTO store (name, address, email, phone, type, opening_date) VALUES
    ('Bulk Warehouse', '456 Oak Avenue, City2', 'info@bulkwarehouse.com', '+23456789012', 'WAREHOUSE', '2023-02-20');

-- Store 3
INSERT INTO store (name, address, email, phone, type, opening_date) VALUES
    ('Grocery World', '789 Elm Street, City3', 'info@groceryworld.com', '+34567890123', 'SUPERMARKET', '2023-03-10');

-- Store 4
INSERT INTO store (name, address, email, phone, type, opening_date) VALUES
    ('Tech Haven Electronics', '101 Gadget Lane, City4', 'info@techhaven.com', '+45678901234', 'ELECTRONICS_STORE', '2023-04-05');

-- Store 5
INSERT INTO store (name, address, email, phone, type, opening_date) VALUES
    ('Fashionista Boutique', '222 Style Street, City5', 'info@fashionista.com', '+56789012345', 'BOUTIQUE', '2023-05-22');

-- Store 6
INSERT INTO store (name, address, email, phone, type, opening_date) VALUES
    ('Fix-It Hardware', '333 Handy Road, City6', 'info@fixithardware.com', '+67890123456', 'HARDWARE_STORE', '2023-06-14');

-- Store 7
INSERT INTO store (name, address, email, phone, type, opening_date) VALUES
    ('Sports Plus', '444 Active Avenue, City7', 'info@sportsplus.com', '+78901234567', 'SPORTING_GOODS_STORE', '2023-07-30');

-- Store 8
INSERT INTO store (name, address, email, phone, type, opening_date) VALUES
    ('Home Transform', '555 Renovation Road, City8', 'info@hometransform.com', '+89012345678', 'HOME_IMPROVEMENT', '2023-08-18');

-- Store 9
INSERT INTO store (name, address, email, phone, type, opening_date) VALUES
    ('Beauty Bliss', '666 Glam Street, City9', 'info@beautybliss.com', '+90123456789', 'DEPARTMENT_STORE', '2023-09-01');

-- Store 10
INSERT INTO store (name, address, email, phone, type, opening_date) VALUES
    ('Fresh Mart', '777 Green Avenue, City10', 'info@freshmart.com', '+01234567890', 'SUPERMARKET', '2023-10-11');



-- Create 5 purchase orders for 5 different stores
-- Generate binary(16) IDs for Purchase Orders
INSERT INTO purchase_order (id, store_id)
VALUES
    (UNHEX(REPLACE(UUID(), '-', '')), 1),
    (UNHEX(REPLACE(UUID(), '-', '')), 2),
    (UNHEX(REPLACE(UUID(), '-', '')), 3),
    (UNHEX(REPLACE(UUID(), '-', '')), 4),
    (UNHEX(REPLACE(UUID(), '-', '')), 5);


-- Purchase Order 1
-- Select items with categories matching the store type (RETAIL) and insert into purchase_order_item
INSERT INTO purchase_order_item (purchase_order_id, item_id, quantity)
SELECT
    (SELECT id FROM purchase_order WHERE store_id = 1), item.id, FLOOR(RAND() * 20) + 1
FROM item
WHERE item.category_id IN (
    SELECT id
    FROM category
    WHERE category.name IN ('Electronics', 'Clothing')
);

-- Purchase Order 2
-- Select items with categories matching the store type (WAREHOUSE) and insert into purchase_order_item
INSERT INTO purchase_order_item (purchase_order_id, item_id, quantity)
SELECT
    (SELECT id FROM purchase_order WHERE store_id = 2), item.id, FLOOR(RAND() * 20) + 1
FROM item
WHERE item.category_id IN (
    SELECT id
    FROM category
    WHERE category.name IN ('Consumer Electronics', 'Clothing')
);


-- Purchase Order 3
-- Select items with categories matching the store type (SUPERMARKET) and insert into purchase_order_item
INSERT INTO purchase_order_item (purchase_order_id, item_id, quantity)
SELECT
    (SELECT id FROM purchase_order WHERE store_id = 3), item.id, FLOOR(RAND() * 20) + 1
FROM item
WHERE item.category_id IN (
    SELECT id
    FROM category
    WHERE category.name IN ('Organic Vegetables', 'Desserts and Sweets')
);
-- Purchase Order 4
-- Select items with categories matching the store type (ELECTRONICS_STORE) and insert into purchase_order_item
INSERT INTO purchase_order_item (purchase_order_id, item_id, quantity)
SELECT
    (SELECT id FROM purchase_order WHERE store_id = 4), item.id, FLOOR(RAND() * 20) + 1
FROM item
WHERE item.category_id IN (
    SELECT id
    FROM category
    WHERE category.name IN ('Consumer Electronics', 'Electronics')
);

-- Purchase Order 5
-- Select items with categories matching the store type (BOUTIQUE) and insert into purchase_order_item
INSERT INTO purchase_order_item (purchase_order_id, item_id, quantity)
SELECT
    (SELECT id FROM purchase_order WHERE store_id = 5), item.id, FLOOR(RAND() * 20) + 1
FROM item
WHERE item.category_id IN (
    SELECT id
    FROM category
    WHERE category.name IN ('Clothing', 'Apparel')
);




-- Create User 1 - Admin
INSERT INTO app_user (password, email, first_name, last_name, username)
VALUES ('password', 'admin@example.com', 'John', 'Doe', 'admin');

-- Assign the 'ROLE_ADMIN' role to User 1
INSERT INTO user_role (user_id, role)
VALUES (1, 'ROLE_ADMIN');

-- Create User 2 - Store Manager
INSERT INTO app_user (password, email, first_name, last_name, username)
VALUES ('password', 'manager1@example.com', 'Alice', 'Smith', 'manager1');

-- Assign the 'ROLE_STORE_MANAGER' role to User 2
INSERT INTO user_role (user_id, role)
VALUES (2, 'ROLE_STORE_MANAGER');

-- Create User 3 - Store Manager
INSERT INTO app_user (password, email, first_name, last_name, username)
VALUES ('password', 'manager2@example.com', 'Bob', 'Johnson', 'manager2');

-- Assign the 'ROLE_STORE_MANAGER' role to User 3
INSERT INTO user_role (user_id, role)
VALUES (3, 'ROLE_STORE_MANAGER');

-- Create User 4 - Store Staff
INSERT INTO app_user (password, email, first_name, last_name, username)
VALUES ('password', 'staff1@example.com', 'Eva', 'Brown', 'staff1');

-- Assign the 'ROLE_STORE_STAFF' role to User 4
INSERT INTO user_role (user_id, role)
VALUES (4, 'ROLE_STORE_STAFF');

-- Create User 5 - Store Staff
INSERT INTO app_user (password, email, first_name, last_name, username)
VALUES ('password', 'staff2@example.com', 'David', 'Lee', 'staff2');

-- Assign the 'ROLE_STORE_STAFF' role to User 5
INSERT INTO user_role (user_id, role)
VALUES (5, 'ROLE_STORE_STAFF');

-- Assign Stores to Store Managers and Staff

-- Assign Store 1 to User 2 (Store Manager)
INSERT INTO store_user (store_id, user_id)
VALUES (1, 2);

-- Assign Store 2 to User 3 (Store Manager)
INSERT INTO store_user (store_id, user_id)
VALUES (2, 3);

-- Assign Store 3 to User 4 (Store Staff)
INSERT INTO store_user (store_id, user_id)
VALUES (3, 4);

-- Assign Store 4 to User 5 (Store Staff)
INSERT INTO store_user (store_id, user_id)
VALUES (4, 5);



-- Create inventory for Store 1 (MegaMart Retail)
-- Retail store with 3 different categories of items

-- Map Retail store to the "Consumer Electronics" category (category_id = 1)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    1, id AS item_id, FLOOR(RAND() * 400), FLOOR(RAND() * 98) + 3
FROM item
WHERE category_id = 1
LIMIT 5;

-- Map Retail store to the "Clothing" category (category_id = 2)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    1, id AS item_id, FLOOR(RAND() * 400), FLOOR(RAND() * 98) + 3
FROM item
WHERE category_id = 2
LIMIT 5;

-- Map Retail store to the "Fragrances and Perfumes" category (category_id = 10)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    1, id AS item_id, FLOOR(RAND() * 400), FLOOR(RAND() * 98) + 3
FROM item
WHERE category_id = 10
LIMIT 5;

-- Create inventory for Store 2 (Bulk Warehouse)
-- Warehouse store with 2 different categories of items

-- Map Warehouse store to the "Consumer Electronics" category (category_id = 1)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    2, id AS item_id, FLOOR(RAND() * 400), FLOOR(RAND() * 98) + 3
FROM item
WHERE category_id = 1
LIMIT 5;

-- Map Warehouse store to the "Apparel" category (category_id = 2)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    2, id AS item_id, FLOOR(RAND() * 400), FLOOR(RAND() * 98) + 3
FROM item
WHERE category_id = 2
LIMIT 5;

-- Create inventory for Store 3 (Grocery World)
-- Supermarket store with 3 different categories of items

-- Map Supermarket store to the "Organic Vegetables" category (category_id = 5)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    3, id AS item_id, FLOOR(RAND() * 400), FLOOR(RAND() * 98) + 3
FROM item
WHERE category_id = 5
LIMIT 5;

-- Map Supermarket store to the "Dairy Products" category (category_id = 7)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    3, id AS item_id, FLOOR(RAND() * 400), FLOOR(RAND() * 98) + 3
FROM item
WHERE category_id = 7
LIMIT 5;

-- Map Supermarket store to the "Fragrances and Perfumes" category (category_id = 10)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    3, id AS item_id, FLOOR(RAND() * 400), FLOOR(RAND() * 98) + 3
FROM item
WHERE category_id = 10
LIMIT 5;

-- Create inventory for Store 4 (Tech Haven Electronics)
-- Electronics store with 2 different categories of items

-- Map Electronics store to the "Consumer Electronics" category (category_id = 1)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    4, id AS item_id, FLOOR(RAND() * 400), FLOOR(RAND() * 98) + 3
FROM item
WHERE category_id = 1
LIMIT 5;

-- Map Electronics store to the "Electronics" category (category_id = 3)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    4, id AS item_id, FLOOR(RAND() * 400), FLOOR(RAND() * 98) + 3
FROM item
WHERE category_id = 3
LIMIT 5;

-- Create inventory for Store 5 (Fashionista Boutique)
-- Boutique store with 2 different categories of items

-- Map Boutique store to the "Clothing" category (category_id = 2)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    5, id AS item_id, FLOOR(RAND() * 400), FLOOR(RAND() * 98) + 3
FROM item
WHERE category_id = 2
LIMIT 5;

-- Map Boutique store to the "Fragrances and Perfumes" category (category_id = 10)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    5, id AS item_id, FLOOR(RAND() * 400), FLOOR(RAND() * 98) + 3
FROM item
WHERE category_id = 10
LIMIT 5;

-- Create inventory for Store 6 (Fix-It Hardware)
-- Hardware store with 5 different categories of items

-- Map Hardware store to the "Consumer Electronics" category (category_id = 1)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    6, id AS item_id, LEAST(FLOOR(RAND() * 100), 100), LEAST(FLOOR(RAND() * 70) + 3, 70)
FROM item
WHERE category_id = 1
LIMIT 5;

-- Map Hardware store to the "Apparel" category (category_id = 4)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    6, id AS item_id, LEAST(FLOOR(RAND() * 100), 100), LEAST(FLOOR(RAND() * 70) + 3, 70)
FROM item
WHERE category_id = 4
LIMIT 5;

-- Map Hardware store to the "Organic Vegetables" category (category_id = 5)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    6, id AS item_id, LEAST(FLOOR(RAND() * 100), 100), LEAST(FLOOR(RAND() * 70) + 3, 70)
FROM item
WHERE category_id = 5
LIMIT 5;

-- Map Hardware store to the "Desserts and Sweets" category (category_id = 6)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    6, id AS item_id, LEAST(FLOOR(RAND() * 100), 100), LEAST(FLOOR(RAND() * 70) + 3, 70)
FROM item
WHERE category_id = 6
LIMIT 5;

-- Map Hardware store to the "Fragrances and Perfumes" category (category_id = 10)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    6, id AS item_id, LEAST(FLOOR(RAND() * 100), 100), LEAST(FLOOR(RAND() * 70) + 3, 70)
FROM item
WHERE category_id = 10
LIMIT 5;

-- Create inventory for Store 7 (Sports Plus)
-- Sporting Goods store with 3 different categories of items

-- Map Sporting Goods store to the "Consumer Electronics" category (category_id = 1)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    7, id AS item_id, LEAST(FLOOR(RAND() * 100), 100), LEAST(FLOOR(RAND() * 70) + 3, 70)
FROM item
WHERE category_id = 1
LIMIT 5;

-- Map Sporting Goods store to the "Clothing" category (category_id = 2)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    7, id AS item_id, LEAST(FLOOR(RAND() * 100), 100), LEAST(FLOOR(RAND() * 70) + 3, 70)
FROM item
WHERE category_id = 2
LIMIT 5;

-- Map Sporting Goods store to the "Organic Vegetables" category (category_id = 5)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    7, id AS item_id, LEAST(FLOOR(RAND() * 100), 100), LEAST(FLOOR(RAND() * 70) + 3, 70)
FROM item
WHERE category_id = 5
LIMIT 5;

-- Map Sporting Goods store to the "Desserts and Sweets" category (category_id = 6)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    7, id AS item_id, LEAST(FLOOR(RAND() * 100), 100), LEAST(FLOOR(RAND() * 70) + 3, 70)
FROM item
WHERE category_id = 6
LIMIT 5;

-- Map Sporting Goods store to the "Fragrances and Perfumes" category (category_id = 10)
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    7, id AS item_id, LEAST(FLOOR(RAND() * 100), 100), LEAST(FLOOR(RAND() * 70) + 3, 70)
FROM item
WHERE category_id = 10
LIMIT 5;

-- Create inventory for Store 8 (Home Transform)
-- Home Improvement store with a variety of items
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    8,
    id AS item_id,
    FLOOR(RAND() * 100),  -- Random quantity up to 100
    FLOOR(RAND() * 70) + 3  -- Random threshold between 3 and 70
FROM item
LIMIT 10;  -- Generate inventories for 10 random items

-- Create inventory for Store 9 (Beauty Bliss)
-- Department Store with a variety of items
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    9,
    id AS item_id,
    FLOOR(RAND() * 100),  -- Random quantity up to 100
    FLOOR(RAND() * 70) + 3  -- Random threshold between 3 and 70
FROM item
LIMIT 10;  -- Generate inventories for 10 random items

-- Create inventory for Store 10 (Fresh Mart)
-- Supermarket with a variety of items
INSERT INTO inventory (store_id, item_id, quantity, threshold)
SELECT
    10,
    id AS item_id,
    FLOOR(RAND() * 100),  -- Random quantity up to 100
    FLOOR(RAND() * 70) + 3  -- Random threshold between 3 and 70
FROM item
LIMIT 10;  -- Generate inventories for 10 random items
