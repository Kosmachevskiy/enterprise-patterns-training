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