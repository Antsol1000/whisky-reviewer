CREATE TABLE whisky(
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL,
    alcohol INT NOT NULL,
    type VARCHAR NOT NULL,
    age INT NULL,
    brand_id UUID NOT NULL,
    CONSTRAINT fk_brand FOREIGN KEY(brand_id) REFERENCES brand(id)
);