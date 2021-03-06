CREATE TABLE Users (
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email varchar(255) UNIQUE NOT NULL,
    password varchar(64) NOT NULL,
    name varchar(50) NOT NULL,
    tel int NOT NULL,
    cif varchar(15),
    address varchar(255),
    worth float,
    nif varchar(15),
    bdate date,
    data varchar(600),
    verified boolean
);

CREATE TABLE User_groups (
    email varchar(255) NOT NULL,
    groupname VARCHAR(32) NOT NULL,
    PRIMARY KEY (email)
);

CREATE TABLE Hotel (
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar(255) UNIQUE NOT NULL,
    city varchar(255) NOT NULL,
    rooms int NOT NULL,
    price float NOT NULL,
    services varchar(255),
    owner varchar(255) NOT NULL,
    CONSTRAINT FK_UserHotel FOREIGN KEY (owner) REFERENCES Users(email)
);

CREATE TABLE Reserve (
    id serial NOT NULL,
    customer varchar(50) NOT NULL,
    hotel int NOT NULL,
    day date NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_UserReserve FOREIGN KEY (customer) REFERENCES Users(email),
    CONSTRAINT FK_HotelReserve FOREIGN KEY (hotel) REFERENCES Hotel(id)
);

CREATE TABLE Chatlog (
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    message varchar(500) NOT NULL
);

