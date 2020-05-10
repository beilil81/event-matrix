-- CREATE database eventmatrixdb;
-- Use eventmatrixdb;

DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS type_events;


CREATE TABLE event (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  libelle VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  type_id INT NOT NULL,
  email_person VARCHAR(250) NOT NULL
);

INSERT INTO event (libelle, description, type_id,email_person) VALUES
  ('Java', 'Java 8',1,"ahmed@yahoo.fr"),
  ('C#', 'C# description',4,"aly@orange.fr"),
  ('C++', 'C++ description',2,"franck@sfr.fr");


  CREATE TABLE type_events (
  type_id INT AUTO_INCREMENT  PRIMARY KEY,
  title_event VARCHAR(250) NOT NULL
);

INSERT INTO type_events (title_event) VALUES
  ('atelier'),
  ('work shop 1'),
  ('workshop 2');
