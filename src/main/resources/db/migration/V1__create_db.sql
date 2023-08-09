CREATE TABLE client (
    id IDENTITY PRIMARY KEY ,
    name VARCHAR(200) CHECK ( LENGTH(NAME) BETWEEN 3 AND 200)
);

CREATE TABLE planet (
 id VARCHAR(20) PRIMARY KEY CHECK (REGEXP_LIKE(id, '^[A-Z0-9]+$')),
 name VARCHAR(500) CHECK (LENGTH(name) BETWEEN 1 AND 500)
);


CREATE TABLE ticket(
    id IDENTITY PRIMARY KEY,
    created_at TIMESTAMP,
    client_id BIGINT,
    from_planet_id VARCHAR(20),
    to_planet_id VARCHAR(20),
    FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE ,
    FOREIGN KEY (from_planet_id) REFERENCES planet(id) ON DELETE CASCADE,
    FOREIGN KEY (to_planet_id) REFERENCES planet(id) ON DELETE CASCADE
);
