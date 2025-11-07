CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       name TEXT NOT NULL,
                       email TEXT UNIQUE NOT NULL
);

INSERT INTO users(name, email) VALUES
                                   ('Harry Potter', 'harry.potter@hogwarts.wz'),
                                   ('Hermione Granger', 'hermione.granger@hogwarts.wz'),
                                   ('Ron Weasley', 'ron.weasley@hogwarts.wz'),
                                   ('Draco Malfoy', 'draco.malfoy@hogwarts.wz'),
                                   ('Luna Lovegood', 'luna.lovegood@hogwarts.wz'),
                                   ('Neville Longbottom', 'neville.longbottom@hogwarts.wz'),
                                   ('Ginny Weasley', 'ginny.weasley@hogwarts.wz'),
                                   ('Fred Weasley', 'fred.weasley@hogwarts.wz'),
                                   ('George Weasley', 'george.weasley@hogwarts.wz'),
                                   ('Severus Snape', 'severus.snape@hogwarts.wz');
