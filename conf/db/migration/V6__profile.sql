
--------------
CREATE TABLE "profile" (
  "id"	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  "comment"	VARCHAR(255),
  "description"	VARCHAR(255),
  "user_id"	INT NOT NULL,
  "store_id" INT NOT NULL
) ENGINE=InnoDB;
