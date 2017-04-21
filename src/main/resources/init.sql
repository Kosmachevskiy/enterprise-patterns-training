DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS product_item;
DROP TABLE IF EXISTS user;

CREATE TABLE product_category (
  id   IDENTITY AUTO_INCREMENT,
  name VARCHAR(50)
);
CREATE TABLE product (
  id          IDENTITY AUTO_INCREMENT,
  name        VARCHAR(50),
  price       FLOAT,
  category_id BIGINT,
  count       BIGINT,
  FOREIGN KEY (category_id) REFERENCES PUBLIC.PRODUCT_CATEGORY (id)
);
CREATE TABLE user (
  id       IDENTITY AUTO_INCREMENT,
  name     VARCHAR(50),
  password VARCHAR(50)
);
CREATE TABLE orders (
  id      IDENTITY AUTO_INCREMENT,
  user_id BIGINT,
  date    DATETIME,
  FOREIGN KEY (user_id) REFERENCES PUBLIC.USER (id)
);
CREATE TABLE product_item (
  id         IDENTITY AUTO_INCREMENT,
  product_id BIGINT,
  order_id   BIGINT,
  count      INT,
  FOREIGN KEY (product_id) REFERENCES PUBLIC.PRODUCT (id),
  FOREIGN KEY (order_id) REFERENCES PUBLIC.ORDERS (id)
);

INSERT INTO user (name, password) VALUES ('someUser', '!@#$%^&*()');
INSERT INTO product_category (name) VALUES ('Sneakers');
INSERT INTO product_category (name) VALUES ('Shoes');
INSERT INTO product_category (name) VALUES ('Stools');
INSERT INTO product (name, price, category_id, count) VALUES ('Reebok', 59.99, 1, 100500);
INSERT INTO product (name, price, category_id, count) VALUES ('Reebok Shoes', 9.99, 2, 100501);
INSERT INTO product (name, price, category_id, count) VALUES ('Nike', 69.69, 1, 100502);
INSERT INTO product (name, price, category_id, count) VALUES ('Nike Shoes', 59.99, 2, 100503);
INSERT INTO product (name, price, category_id, count) VALUES ('Nike Super', 899.99, 1, 100504);
INSERT INTO product (name, price, category_id, count) VALUES ('New Nike', 999.99, 1, 100504);
INSERT INTO product (name, price, category_id, count) VALUES ('Stool of Devil', 0.01, 3, 100505);
INSERT INTO product (name, price, category_id, count) VALUES ('Holy Stool', 9999.99, 3, 100506);