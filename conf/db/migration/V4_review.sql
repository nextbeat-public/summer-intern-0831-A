CREATE TABLE "review" (
  "id"          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  "cast_id"     VARCHAR(8)   NOT NULL,
  "user_id"     VARCHAR(255) NOT NULL,
  "updated_at"  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  "created_at"  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- 施設情報 (sample)
INSERT INTO "review" ("cast_id", "user_id") VALUES ('cast1', 'user1');
INSERT INTO "review" ("cast_id", "user_id") VALUES ('cast1', 'user2');
INSERT INTO "review" ("cast_id", "user_id") VALUES ('cast1', 'user3');