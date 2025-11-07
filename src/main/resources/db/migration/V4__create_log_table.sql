-- Enable pgcrypto for UUID generation
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Create log table
CREATE TABLE log (
                     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                     user_id UUID NOT NULL,
                     produce_id UUID NOT NULL,

                     quantity INT NOT NULL DEFAULT 1,
                     entry_date DATE NOT NULL,

                     created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
                     updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                     CONSTRAINT fk_log_user FOREIGN KEY (user_id) REFERENCES users(id),
                     CONSTRAINT fk_log_produce FOREIGN KEY (produce_id) REFERENCES produce(id)
);