-- 1. Tạo bảng products
CREATE TABLE products (
    -- BIGSERIAL tương đương với Long Auto Increment (Identity)
    id BIGSERIAL PRIMARY KEY,

    -- Các cột dữ liệu chính
    name VARCHAR(255),

    -- Các cột Auditing (từ BaseEntity)
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    -- Cột Soft Delete
    deleted_at TIMESTAMP
);

-- 2. Tạo Index cho cột deleted_at (CỰC KỲ QUAN TRỌNG)
-- Lý do: Vì câu @Where(clause = "deleted_at IS NULL") sẽ được chèn vào MỌI câu query.
-- Nếu không có index này, DB sẽ bị chậm khi dữ liệu lớn.
CREATE INDEX idx_products_deleted_at ON products (deleted_at);

-- (Optional) Tạo Partial Index nếu dùng Postgres
-- Chỉ index những dòng chưa xóa (tiết kiệm dung lượng index)
-- CREATE INDEX idx_products_active ON products (id) WHERE deleted_at IS NULL;