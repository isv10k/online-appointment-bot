DROP TABLE IF EXISTS users cascade;
DROP TABLE IF EXISTS time_slots cascade;
DROP TABLE IF EXISTS appointments;

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE time_slots (
    slot_id SERIAL PRIMARY KEY,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    is_available BOOLEAN NOT NULL
);

CREATE TABLE appointments (
    appointment_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    slot_id INT REFERENCES time_slots(slot_id),
    appointment_time TIMESTAMP
--     created_at TIMESTAMP DEFAULT NOW(),
--     updated_at TIMESTAMP DEFAULT NOW()
);

ALTER TABLE appointments ADD CONSTRAINT unique_user_slot_constraint UNIQUE (user_id, slot_id);


-- Insert sample users
INSERT INTO users (username, email)
VALUES
    ('john_doe', 'john@example.com'),
    ('jane_doe', 'jane@example.com'),
    ('alice_smith', 'alice@example.com'),
    ('bob_jones', 'bob@example.com');



-- Insert sample time slots
INSERT INTO time_slots (start_time, end_time, is_available)
VALUES
    ('2023-11-10 09:00:00', '2023-11-10 10:00:00', false),
    ('2023-11-10 10:00:00', '2023-11-10 11:00:00', false),
    ('2023-11-10 11:00:00', '2023-11-10 12:00:00', true),
    ('2023-11-10 11:00:00', '2023-11-10 13:00:00', false),
    ('2023-11-10 13:00:00', '2023-11-10 14:00:00', true),
    ('2023-11-10 14:00:00', '2023-11-10 15:00:00', true);

-- Insert sample appointments
INSERT INTO appointments (user_id, slot_id, appointment_time)
VALUES
    (1, 1, '2023-11-10 10:00:00'),
    (3, 2, '2023-11-10 11:00:00'),
    (4, 4, '2023-11-10 13:00:00');