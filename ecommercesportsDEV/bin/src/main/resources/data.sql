USE ecommerce_sports;


INSERT INTO user (id,createdat,enabled,password,updatedat,username, first_name,lastname,email) VALUES(1, "2020-03-22 00:00:01", 1, "$2y$12$a0iCfDJYp5GE7GSbvsv7j.W/WYVFnlRt9TUebcyzd1k/9N5RkgWlu", "2020-03-22 00:00:01", "user1","franco","aguirre","francoaguirre644@gmail.com");
/* user1 user  */
INSERT INTO user_role (id,createdat,role,updatedat,user_id) VALUES(1, "2020-03-22 00:00:01", "ROLE_USER", "2020-03-22 00:00:01", 1);
