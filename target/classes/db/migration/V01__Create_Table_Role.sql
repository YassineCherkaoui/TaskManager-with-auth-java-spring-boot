CREATE TABLE IF NOT EXISTS tb_role(
	id UUID PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);