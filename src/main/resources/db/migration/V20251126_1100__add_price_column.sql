-- V20251126_1100__add_price_column.sql
ALTER TABLE products
ADD COLUMN price NUMERIC(15,2) DEFAULT 0;
