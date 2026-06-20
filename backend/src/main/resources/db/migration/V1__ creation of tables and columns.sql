CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users (
    user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL,
    user_role VARCHAR(45) DEFAULT 'ROLE_USER'
);

CREATE TABLE transacao (
    transaction_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    description VARCHAR(200) NOT NULL,
    value NUMERIC(19,2) NOT NULL,
    category VARCHAR(255) NOT NULL,
    transaction_status VARCHAR(45) NOT NULL,
    transaction_date DATE NOT NULL,
    payment_method VARCHAR(45) NOT NULL,
    transaction_type VARCHAR(31) NOT NULL,
    CONSTRAINT fk_transacao_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);