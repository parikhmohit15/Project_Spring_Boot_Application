CREATE TABLE item (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    price DOUBLE,  
    category VARCHAR(50),
    powerlevel INT,
    material VARCHAR(100),
    weight DOUBLE,  
    stockStatus VARCHAR(50)
);