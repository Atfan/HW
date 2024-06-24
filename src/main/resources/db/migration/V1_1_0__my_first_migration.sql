
CREATE TABLE IF NOT EXISTS places (
    id SERIAL PRIMARY KEY,
    name_place  varchar (60),
    address varchar (255)
    );

CREATE TABLE IF NOT EXISTS customers (
    id SERIAL PRIMARY KEY,
    first_name  varchar (30),
    last_name varchar (30),
    email  varchar (60),
    phone varchar (10)
    );

CREATE TABLE IF NOT EXISTS events (
    id SERIAL PRIMARY KEY,
    event_date DATE,
    name_event  varchar (30),
    place_id integer,
    FOREIGN KEY (place_id) REFERENCES places (id)  ON DELETE CASCADE,
    CONSTRAINT unique_event_per_day_per_place UNIQUE (event_date, place_id)
    );

CREATE TABLE IF NOT EXISTS tickets (
    id SERIAL PRIMARY KEY,
    cost DECIMAL(9,2),
    numbr integer,
    status varchar (5),
    customer_id integer,
    event_id integer,
    FOREIGN KEY (customer_id) REFERENCES customers (id)  ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events (id)  ON DELETE CASCADE,
    CONSTRAINT status_check CHECK (status IN ('FREE', 'SOLD'))
    );



