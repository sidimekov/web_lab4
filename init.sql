CREATE TABLE "AppUser" (
                        id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        login VARCHAR2(255) NOT NULL UNIQUE,
                        password VARCHAR2(255) NOT NULL
);

CREATE TABLE Point (
                       id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                       x NUMBER(10, 2) NOT NULL,
                       y NUMBER(10, 2) NOT NULL,
                       r NUMBER(10, 2) NOT NULL,
                       is_in CHAR(1) CHECK (is_in IN ('Y', 'N')) NOT NULL,
                       point_date VARCHAR2(255) NOT NULL,
                       exec_time NUMBER(10, 2) NOT NULL,
                       user_id NUMBER NOT NULL,
                       CONSTRAINT fk_app_user
                           FOREIGN KEY (app_user_id)
                               REFERENCES "AppUser" (id)
                               ON DELETE CASCADE
);

COMMIT;
