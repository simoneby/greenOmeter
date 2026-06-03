
-- Translations table
CREATE TABLE produce_translations (
    produce_id UUID NOT NULL REFERENCES produce(id) ON DELETE CASCADE,
    language TEXT NOT NULL,
    name TEXT NOT NULL,
    PRIMARY KEY (produce_id, language)
);

