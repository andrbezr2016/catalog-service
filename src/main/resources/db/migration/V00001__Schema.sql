CREATE TABLE IF NOT EXISTS books (
    id uuid NOT NULL,
    title varchar NOT NULL,
    description varchar,
    author varchar,
    publisher varchar,
    year_published integer,
    isbn varchar,
    pages integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tags (
    id uuid NOT NULL,
    name varchar NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS book_tags (
    book_id uuid NOT NULL REFERENCES books(id),
    tag_id uuid NOT NULL REFERENCES tags(id),
    PRIMARY KEY (book_id,tag_id)
);