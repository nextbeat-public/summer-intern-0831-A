--------------
CREATE TABLE "review" (
  "id"	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  "title"	VARCHAR(255),
  "body"	TEXT,
  "user_id"	INT NOT NULL,
  "cast_id" INT NOT NULL
) ENGINE=InnoDB;
