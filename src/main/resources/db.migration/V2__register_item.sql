CREATE TABLE olio_items (
    id UUID PRIMARY KEY,
    title VARCHAR(100),
    description VARCHAR(100),
    city VARCHAR(100),
    is_listed BOOLEAN,
    is_expired BOOLEAN,
    is_arranged BOOLEAN
);
