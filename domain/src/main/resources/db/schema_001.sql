DROP TABLE IF EXISTS notification;
DROP TABLE IF EXISTS order_status;
DROP TABLE IF EXISTS food_order;
DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(254) NOT NULL
);

CREATE TABLE order_status
(
    id          BIGSERIAL PRIMARY KEY,
    description VARCHAR
);

CREATE TABLE food_order
(
    id              BIGSERIAL PRIMARY KEY,
    customer_id     BIGINT REFERENCES customer (id),
    description     VARCHAR,
    order_status_id BIGINT     -- REFERENCES order_status (id)
);

CREATE TABLE notification
(
    id          BIGSERIAL PRIMARY KEY,
    description VARCHAR,
    order_id    BIGINT       -- REFERENCES food_order (id)
);