create table bookings(
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50),
email_address VARCHAR(50),
phone_number VARCHAR(50),
reservation_date DATETIME,
comments LONGTEXT,
unique(email_address)
);

insert into bookings(name, email_address, phone_number, reservation_date, comments) VALUES
('Ng Chin Ling', 'ncl@gmail.com', '99998888', '2023-04-07 15:30:00', 'For buffet'),
('Byron Smith', 'byron@gmail.com', '99797888', '2023-04-09 12:30:00', 'For lunch. Table for 5'),
('Joe Barack', 'joe@gmail.com', '1234 6789', '2023-04-07 19:30:00', 'For dinner. Lobster set'),
('Obama Biden', 'obama@whitehouse.com', '8879 6888', '2023-04-08 14:30:00', 'Bringing foreign guests from France. Prefer French menu');
('Kusuma Tako', 'kusuma@tokyo.com', '7777 6666', '2023-04-09 15:30:00', 'Seafood preferred. Seats next to sea view'),
('Chen Pingping', 'pingping@taiwan.com', '6565 3333', '2023-04-07 18:00:00', 'Try dumplings. Smoking area');
