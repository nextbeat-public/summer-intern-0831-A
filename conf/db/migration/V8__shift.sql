--------------
CREATE TABLE "shift" (
  "id"	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  "day_of_week" VARCHAR(255),
  "start_time" TIME,
  "end_time" TIME,
  "cast_id"	INT NOT NULL
) ENGINE=InnoDB;
