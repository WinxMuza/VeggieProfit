-- VeggieProfit: evidencia a analytika predajne ovocia a zeleniny

-- ---------- Registacie ----------

CREATE TABLE app_user (
  id            BIGINT       NOT NULL AUTO_INCREMENT,
  username      VARCHAR(50)  NOT NULL UNIQUE,
  password_hash VARCHAR(100) NOT NULL,
  created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

-- ---------- Číselníky ----------

CREATE TABLE category (
  id      BIGINT      NOT NULL AUTO_INCREMENT,
  user_id BIGINT      NOT NULL,
  name    VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (user_id, name),
  FOREIGN KEY (user_id) REFERENCES app_user (id)
);


CREATE TABLE expense_category (
  id      BIGINT      NOT NULL AUTO_INCREMENT,
  user_id BIGINT      NOT NULL,
  name    VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (user_id, name),
  FOREIGN KEY (user_id) REFERENCES app_user (id)
);

-- ---------- Hlavné entity ----------

CREATE TABLE supplier (
  id      BIGINT       NOT NULL AUTO_INCREMENT,
  user_id BIGINT       NOT NULL,
  name    VARCHAR(80)  NOT NULL,
  phone   VARCHAR(30),
  email   VARCHAR(100),
  active  BOOLEAN      NOT NULL DEFAULT TRUE,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES app_user (id)
);

CREATE TABLE product (
  id          BIGINT        NOT NULL AUTO_INCREMENT,
  category_id BIGINT        NOT NULL,
  name        VARCHAR(80)   NOT NULL,
  sale_price  DECIMAL(10,2) NOT NULL CHECK (sale_price >= 0),
  min_stock   DECIMAL(10,3) NOT NULL DEFAULT 0 CHECK (min_stock >= 0),
  stock       DECIMAL(10,3) NOT NULL DEFAULT 0 CHECK (stock >= 0),
  avg_cost    DECIMAL(10,4) NOT NULL DEFAULT 0 CHECK (avg_cost >= 0),
  active      BOOLEAN       NOT NULL DEFAULT TRUE,
  PRIMARY KEY (id),
  UNIQUE (category_id, name),
  FOREIGN KEY (category_id) REFERENCES category (id)
);

-- ---------- Dodávky ----------

CREATE TABLE delivery (
  id            BIGINT NOT NULL AUTO_INCREMENT,
  supplier_id   BIGINT NOT NULL,
  delivery_date DATE   NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (supplier_id) REFERENCES supplier (id)
);

CREATE TABLE delivery_item (
  id             BIGINT        NOT NULL AUTO_INCREMENT,
  delivery_id    BIGINT        NOT NULL,
  product_id     BIGINT        NOT NULL,
  quantity       DECIMAL(10,3) NOT NULL CHECK (quantity > 0),
  purchase_price DECIMAL(10,2) NOT NULL CHECK (purchase_price >= 0),
  PRIMARY KEY (id),
  UNIQUE (delivery_id, product_id),
  FOREIGN KEY (delivery_id) REFERENCES delivery (id),
  FOREIGN KEY (product_id)  REFERENCES product (id)
);

-- ---------- Predaje ----------

CREATE TABLE sale (
  id        BIGINT NOT NULL AUTO_INCREMENT,
  user_id   BIGINT NOT NULL,
  sale_date DATE   NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES app_user (id)
);

CREATE TABLE sale_item (
  id         BIGINT        NOT NULL AUTO_INCREMENT,
  sale_id    BIGINT        NOT NULL,
  product_id BIGINT        NOT NULL,
  quantity   DECIMAL(10,3) NOT NULL CHECK (quantity > 0),
  unit_price DECIMAL(10,2) NOT NULL CHECK (unit_price >= 0),  -- predajná cena v čase predaja
  unit_cost  DECIMAL(10,4) NOT NULL CHECK (unit_cost >= 0),   -- nákupná hodnota v čase predaja (snapshot)
  PRIMARY KEY (id),
  UNIQUE (sale_id, product_id),
  FOREIGN KEY (sale_id)    REFERENCES sale (id),
  FOREIGN KEY (product_id) REFERENCES product (id)
);

-- ---------- Výdavky ----------

CREATE TABLE expense (
  id                  BIGINT        NOT NULL AUTO_INCREMENT,
  expense_category_id BIGINT        NOT NULL,
  expense_date        DATE          NOT NULL,
  amount              DECIMAL(10,2) NOT NULL CHECK (amount > 0),
  description         VARCHAR(200),
  PRIMARY KEY (id),
  FOREIGN KEY (expense_category_id) REFERENCES expense_category (id)
);