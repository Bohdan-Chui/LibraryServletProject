CREATE DATABASE IF NOT EXISTS librarydb;
USE  librarydb;

CREATE TABLE IF NOT EXISTS User
(
    id INT AUTO_INCREMENT,
    patronymic VARCHAR(50) NOT NULL,
    firstname VARCHAR(50)  NOT NULL,
    secondname VARCHAR(50)  NOT NULL,
    password VARCHAR(50)  NOT NULL,
    email VARCHAR(50) UNIQUE DEFAULT 'Unnamed',
    role VARCHAR(10)  NOT NULL DEFAULT 'user',
    blocked BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id)
    );
CREATE TABLE IF NOT EXISTS Book
(
    Id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    publisher VARCHAR(50) NOT NULL,
    count INT DEFAULT NULL,
    publishedTime DATE NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS Card
(
    id INT AUTO_INCREMENT,
    bookId INT,
    userId INT,
    place VARCHAR(20) DEFAULT 'library',
    status VARCHAR(20) DEFAULT 'not confirmed',
    returnDate DATE NOT NULL,
    fine INT,
    PRIMARY KEY(id),
    CONSTRAINT UserFK
        FOREIGN KEY (UserId) REFERENCES User(Id) ON DELETE CASCADE,
    CONSTRAINT BookFK
        FOREIGN KEY (BookId) REFERENCES Book(Id) ON DELETE CASCADE
);

INSERT INTO user (patronymic, firstname, secondname,  email, password, role)
VALUES  ('victior', 'victor', 'victorovich', 'victor@vict.vict','vctr', 'reader');
INSERT INTO user (patronymic, firstname, secondname,  email, password, role)
VALUES  ('katerinka', 'kate', 'katovna', 'kate@kate.vict','kt', 'reader');
INSERT INTO user (patronymic, firstname, secondname,  email, password, role)
VALUES  ('johnatan', 'john', 'john', 'john@john.vict','jhn', 'reader');
INSERT INTO user (patronymic, firstname, secondname,  email, password, role)
VALUES  ('johnatan', 'john', 'john', 'sd@ds.sd','sd', 'reader');

INSERT INTO Book (Name, Author,PublishedTime, Publisher, count) values ('Абетка', 'Беззславний', '1790.12.24', 'Абабагаламага',2 );
INSERT INTO Book (Name, Author,PublishedTime, Publisher, count) values ('Гаррі Поттер', 'Роулинз', '2014.05.10', 'firstPublisher',1 );
INSERT INTO Book (Name, Author,PublishedTime, Publisher, count) values ('Географія', 'Письменна', '2014.06.05', 'secondPublisher' ,2);