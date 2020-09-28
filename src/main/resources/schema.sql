DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS order_line;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS BILL;

CREATE TABLE IF NOT EXISTS product (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name varchar(100),
  weight float,
  price float
);

CREATE TABLE IF NOT EXISTS orders (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  status varchar(100),
  shipment_amount float,
  total_amount float,
  total_weight float
);

CREATE TABLE IF NOT EXISTS order_line(
  id INT AUTO_INCREMENT PRIMARY KEY,
  order_id INT,
  product_id INT,
  quantity INT,
  foreign key (order_id) references orders(id) ON DELETE CASCADE,
  foreign key (product_id) references product(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS bill(
    id INT AUTO_INCREMENT PRIMARY KEY,
    creation_date date,
    order_id INT,
    amount float,
    foreign key (order_id) references orders(id) ON DELETE CASCADE
)
