
CREATE TABLE IF NOT EXISTS customer
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(254) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_status
(
    id          BIGSERIAL PRIMARY KEY,
    description VARCHAR
);

CREATE TABLE IF NOT EXISTS food_order
(
    id              BIGSERIAL PRIMARY KEY,
    customer_id     BIGINT REFERENCES customer (id),
    description     VARCHAR,
    order_status_id BIGINT     -- REFERENCES order_status (id)
);

CREATE TABLE IF NOT EXISTS notification
(
    id          BIGSERIAL PRIMARY KEY,
    description VARCHAR,
    order_id    BIGINT       -- REFERENCES food_order (id)
);