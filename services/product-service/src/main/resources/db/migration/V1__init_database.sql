CREATE TABLE IF NOT EXISTS category
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    name VARCHAR(255),
    PRIMARY KEY (id)
)AUTO_INCREMENT = 50;

CREATE TABLE IF NOT EXISTS product
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    name VARCHAR(255),
    available_quantity DOUBLE NOT NULL,
    price DECIMAL(38, 2),
    category_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category(id)
)AUTO_INCREMENT = 50;
