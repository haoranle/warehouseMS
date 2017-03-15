CREATE TABLE manager(
  id VARCHAR(50) PRIMARY KEY,
  name VARCHAR(100),
  account VARCHAR(100),
  password VARCHAR(100),
  phone VARCHAR(100),
  job VARCHAR(100),
  entry TIMESTAMP
);

CREATE TABLE supplier(
  id VARCHAR(50) primary KEY,
  name VARCHAR(100),
  phone VARCHAR(100),
  address VARCHAR(100)
);

CREATE TABLE warein(
  id VARCHAR(50) PRIMARY KEY,
  number VARCHAR(50),
  intime TIMESTAMP,
  m_id VARCHAR(50),
  CONSTRAINT FOREIGN KEY(m_id) REFERENCES manager(id)
);

CREATE TABLE wareout(
  id VARCHAR(50) PRIMARY KEY,
  number VARCHAR(50),
  outtime TIMESTAMP,
  m_id VARCHAR(50),
  CONSTRAINT FOREIGN KEY(m_id) REFERENCES manager(id)
);

CREATE TABLE warehouse(
  id VARCHAR(50) PRIMARY KEY,
  number VARCHAR(50),
  name VARCHAR(100),
  remarks VARCHAR(100)
);

CREATE TABLE shelf(
  id VARCHAR(50) PRIMARY KEY,
  number VARCHAR(50),
  remarks VARCHAR(100),
  w_id VARCHAR(50),
  CONSTRAINT FOREIGN KEY(w_id) REFERENCES warehouse(id)
);

CREATE TABLE goodsType(
  id VARCHAR(50) PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE goods(
  id VARCHAR(50) PRIMARY KEY,
  name VARCHAR(100),
  price DOUBLE(8,2),
  anount VARCHAR(100),
  remarks VARCHAR(100),
  t_id VARCHAR(50),
  CONSTRAINT FOREIGN KEY(t_id) REFERENCES goodsType(id)
);

CREATE TABLE wareinlist(
  id VARCHAR(50) PRIMARY KEY,
  number VARCHAR(50),
  amount VARCHAR(100),
  amountless VARCHAR(100),
  g_id VARCHAR(50),
  s_id VARCHAR(50),
  su_id VARCHAR(50),
  CONSTRAINT FOREIGN KEY(g_id) REFERENCES goods(id),
  CONSTRAINT FOREIGN KEY(s_id) REFERENCES shelf(id),
  CONSTRAINT FOREIGN KEY(su_id) REFERENCES supplier(id)
);

CREATE TABLE wareoutlist(
  id VARCHAR(50) PRIMARY KEY,
  number VARCHAR(50),
  amount VARCHAR(100),
  g_id VARCHAR(50),
  wil_id VARCHAR(50),
  CONSTRAINT FOREIGN KEY(g_id) REFERENCES goods(id),
  CONSTRAINT FOREIGN KEY(wil_id) REFERENCES wareinlist(id)
);

ALTER TABLE wareinlist DROP COLUMN number;
ALTER TABLE wareinlist ADD wi_id VARCHAR(50);
ALTER TABLE wareinlist ADD CONSTRAINT wareinlist_fk_4 FOREIGN KEY(wi_id) REFERENCES warein(id);

ALTER TABLE wareoutlist DROP COLUMN number;
ALTER TABLE wareoutlist ADD wo_id VARCHAR(50);
ALTER TABLE wareoutlist ADD CONSTRAINT wareoutlist_fk_4 FOREIGN KEY(wo_id) REFERENCES wareout(id);
