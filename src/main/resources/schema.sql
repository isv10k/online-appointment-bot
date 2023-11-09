-- todo
CREATE TABLE IF NOT EXISTS mytable (
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO mytable (name) VALUES ('John Doe');