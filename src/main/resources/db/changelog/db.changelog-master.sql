CREATE TABLE currencies (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    base_currency VARCHAR(255) NOT NULL DEFAULT 'RUB',
    price_change_range VARCHAR(255),
    description TEXT
);
