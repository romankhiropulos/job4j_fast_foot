CREATE TABLE IF NOT EXISTS kitchen_job
(
    id          BIGSERIAL PRIMARY KEY,
    description VARCHAR,
    order_id    BIGINT       -- REFERENCES food_order (id)
);