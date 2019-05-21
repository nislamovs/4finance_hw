CREATE TABLE IF NOT EXISTS users (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstname VARCHAR,
  lastname VARCHAR,
  address VARCHAR,
  email VARCHAR,
  phone VARCHAR,
  is_blocked BOOLEAN,
  created_date timestamp,
  last_modified_date timestamp
);

CREATE TABLE IF NOT EXISTS loans (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_pk BIGINT NOT NULL,
  loan_amount DECIMAL(15,2) NOT NULL,
  debt DECIMAL(15,2),
  loan_term VARCHAR NOT NULL,
  status VARCHAR NOT NULL,
  ip_address VARCHAR NOT NULL,
  created_date timestamp,
  last_modified_date timestamp,
  FOREIGN KEY (user_pk) references users(id)
);

CREATE TABLE IF NOT EXISTS extentions (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  loan_pk BIGINT NOT NULL,
  extention_period integer NOT NULL,
  created_date timestamp,
  last_modified_date timestamp,
  FOREIGN KEY (loan_pk) references loans(id)
);

