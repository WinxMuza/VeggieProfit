-- VeggieProfit: evidencia a analytika predajne ovocia a zeleniny

-- ---------- Číselníky ----------

CREATE TABLE category (
  id   BIGINT      NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE expense_category (
  id   BIGINT      NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

-- ---------- Hlavné entity ----------

CREATE TABLE supplier (
  id     BIGINT       NOT NULL AUTO_INCREMENT,
  name   VARCHAR(80)  NOT NULL,
  phone  VARCHAR(30),
  email  VARCHAR(100),
  active BOOLEAN      NOT NULL DEFAULT TRUE,
  PRIMARY KEY (id)
);

CREATE TABLE product (
  id          BIGINT        NOT NULL AUTO_INCREMENT,
  name        VARCHAR(80)   NOT NULL UNIQUE,
  category_id BIGINT        NOT NULL,
  unit        VARCHAR(10)   NOT NULL DEFAULT 'kg',
  sale_price  DECIMAL(10,2) NOT NULL CHECK (sale_price >= 0),   -- predajná cena za kg
  min_stock   DECIMAL(10,3) NOT NULL DEFAULT 0 CHECK (min_stock >= 0),
  stock       DECIMAL(10,3) NOT NULL DEFAULT 0 CHECK (stock >= 0),  -- aktuálny stav zásob
  avg_cost    DECIMAL(10,4) NOT NULL DEFAULT 0 CHECK (avg_cost >= 0), -- vážený priemer nákupnej ceny
  active      BOOLEAN       NOT NULL DEFAULT TRUE,
  PRIMARY KEY (id),
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
  sale_date DATE   NOT NULL,
  PRIMARY KEY (id)
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
  expense_date        DATE          NOT NULL,
  expense_category_id BIGINT        NOT NULL,
  amount              DECIMAL(10,2) NOT NULL CHECK (amount > 0),
  description         VARCHAR(200),
  PRIMARY KEY (id),
  FOREIGN KEY (expense_category_id) REFERENCES expense_category (id)
);

-- ---------- Východiskové dáta číselníkov ----------

INSERT INTO category (name) VALUES ('Ovocie'), ('Zelenina');
INSERT INTO expense_category (name) VALUES ('Nájomné'), ('Doprava'), ('Energie'), ('Iné');
