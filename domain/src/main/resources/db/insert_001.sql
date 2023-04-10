-- DELETE FROM notification;
-- DELETE FROM order_status;
-- DELETE FROM food_order;
-- DELETE FROM customer;

INSERT INTO customer (name) VALUES ('Jenny'),
                                   ('Tom');

INSERT INTO food_order (customer_id, description, order_status_id) VALUES (1, 'Jennies order', NULL),
                                                                          (2, 'Toms order', NULL);

INSERT INTO order_status (id, description) VALUES (1, 'Created'),
                                                  (2, 'Completed'),
                                                  (3, 'Canceled');