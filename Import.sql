CREATE TABLE Users (
    email varchar(255) NOT NULL,
    password varchar(64) NOT NULL,
    name varchar(50) NOT NULL,
    tel int NOT NULL,
    cif varchar(15),
    address varchar(255),
    worth float,
    nif varchar(15),
    bdate date,
    data varchar(600),
    PRIMARY KEY (email)
);

CREATE TABLE User_groups (
    email varchar(255) NOT NULL,
    groupname VARCHAR(32) NOT NULL,
    PRIMARY KEY (email)
);

CREATE TABLE Hotel (
    name varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    rooms int NOT NULL,
    price float NOT NULL,
    services varchar(255),
    owner varchar(255) NOT NULL,
    PRIMARY KEY (name),
    CONSTRAINT FK_UserHotel FOREIGN KEY (owner) REFERENCES Users(email)
);

CREATE TABLE Reserve (
    id serial NOT NULL,
    customer varchar(50) NOT NULL,
    hotel varchar(255) NOT NULL,
    day date NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_UserReserve FOREIGN KEY (customer) REFERENCES Users(email),
    CONSTRAINT FK_HotelReserve FOREIGN KEY (hotel) REFERENCES Hotel(name)
);

INSERT INTO user_groups(email, groupname) VALUES
    ('admin@admin.com', 'admin');

INSERT INTO users(email, password, name, tel) VALUES
    ('admin@admin.com','jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'admin', 0);
