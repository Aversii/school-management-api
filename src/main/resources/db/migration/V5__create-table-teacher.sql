-- V5__
CREATE TABLE Teacher (
  id VARCHAR(63) PRIMARY KEY,
  class_id INT NOT NULL,
  course_id INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  FOREIGN KEY (id) REFERENCES Person(id),
  FOREIGN KEY (class_id) REFERENCES ClassRoom(id),
  FOREIGN KEY (course_id) REFERENCES Course(id)

)