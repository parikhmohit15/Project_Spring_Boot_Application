INSERT INTO Ticket (ticketHolder, ticketPrice, seatNumber, eventLocation, eventDate, eventTime, guestName) 
VALUES 
('Michael Clark', 120.00, 'F12', 'Mega Convention Center', '2024-12-01', '20:30:00', 'VIP Guest'),
('Sophia Martinez', 55.00, 'G18', 'Central Park Open Air', '2024-12-02', '18:00:00', 'Guest 6'),
('Daniel Garcia', 90.00, 'H22', 'Royal Opera House', '2024-12-03', '19:45:00', 'Guest 7'),
('Olivia Wilson', 130.00, 'I25', 'Skyline Event Hall', '2024-12-04', '20:15:00', 'Guest 8'),
('William Rodriguez', 70.00, 'J30', 'Community Cultural Center', '2024-12-05', '17:30:00', 'Guest 9'),
('Emma Patel', 60.00, 'K14', 'City Convention Hall', '2024-12-06', '18:30:00', 'Guest 10'),
('James Lee', 95.00, 'L19', 'International Expo Center', '2024-12-07', '19:00:00', 'Guest 11'),
('Charlotte Wong', 110.00, 'M23', 'Heritage Concert Hall', '2024-12-08', '20:00:00', 'VIP Guest 2'),
('Lucas Kim', 45.00, 'N29', 'Greenwood Amphitheater', '2024-12-09', '16:45:00', 'Guest 12'),
('Amelia Nguyen', 85.00, 'O33', 'Downtown Plaza', '2024-12-10', '19:15:00', 'Guest 13');

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Ravi', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Malav', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Savi', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Rohit', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Kanika', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into sec_role (roleName)
values ('ROLE_VENDER');

insert into sec_role (roleName)
values ('ROLE_GUEST');
 
insert into user_role (userId, roleId)
values (1, 1);

insert into user_role (userId, roleId)
values (2, 2);

insert into user_role (userId, roleId)
values (3, 2);

insert into user_role (userId, roleId)
values (4, 2);

insert into user_role (userId, roleId)
values (5, 2);

insert into user_role (userId, roleId)
values (6, 2);



;