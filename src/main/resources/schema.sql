CREATE TABLE IF NOT EXISTS usersessions (
   username VARCHAR(512) NOT NULL,
   token VARCHAR(255) NOT NULL,
   session_date TIMESTAMP NOT NULL
);
