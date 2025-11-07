
CREATE EXTENSION IF NOT EXISTS "pgcrypto";  -- for gen_random_uuid()
CREATE EXTENSION IF NOT EXISTS "citext";    -- for case-insensitive text


CREATE TABLE tags (
                     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                     tag CITEXT UNIQUE NOT NULL
);

CREATE TABLE produce (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         name TEXT NOT NULL,
                         emoji TEXT NOT NULL
);

CREATE TABLE produce_tags (
                              produce_id UUID NOT NULL REFERENCES produce(id),
                              tag_id UUID NOT NULL REFERENCES tags(id),
                              PRIMARY KEY (produce_id, tag_id)
);
