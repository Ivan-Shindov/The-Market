INSERT INTO users (id, username, account)
VALUES (1,'user1', 500);

INSERT INTO users (id, username, account)
VALUES (2,'user2', 0);

INSERT INTO users (id, username, account)
VALUES (3,'user3', 156);

INSERT INTO users (id, username, account)
VALUES (4,'user4', 189.74);

INSERT INTO items (id, name, owner_id)
VALUES (1,'item1', 1);

INSERT INTO items (id, name, owner_id)
VALUES (2,'item2', 1);

INSERT INTO items (id, name, owner_id)
VALUES (3,'item3', 2);

INSERT INTO contracts (id, price, seller_id, item_id, buyer_id, active)
VALUES (1, 100, 1, 1, 3, true);

INSERT INTO contracts (id, price, seller_id, item_id, buyer_id, active)
VALUES (2, 10, 2, 3, 1, true);