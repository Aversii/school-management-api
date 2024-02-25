-- V4__
CREATE TABLE Student (
  id VARCHAR(63) PRIMARY KEY,
  class_id INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  FOREIGN KEY (id) REFERENCES Person(id),
  FOREIGN KEY (class_id) REFERENCES ClassRoom(id)
)