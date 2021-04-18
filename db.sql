CREATE TYPE computer_type AS ENUM (
  'LAPTOP',
  'DESKTOP'
);

CREATE TABLE COMPUTERS
(
  model_number VARCHAR(30) PRIMARY KEY,
  maker VARCHAR(30),
  type computer_type,
  language VARCHAR(30),
  colors text[]
);

CREATE TABLE ssh_keys
(
  public_key VARCHAR(100) PRIMARY KEY,
  type VARCHAR(10),
  comment VARCHAR(50)
);





