INSERT INTO user_groups(email, groupname) VALUES
    ('admin@admin.com', 'admin'),
    ('jfernandez@gmail.com', 'users'),
    ('fran52@gmail.com', 'users'),
    ('sunhotels@gmail.com', 'business'),
    ('happyhotels@gmail.com', 'business');

INSERT INTO users(email, password, name, tel, nif, bdate ) VALUES
    ('jfernandez@gmail.com', 'rI3QaS7ZW6phRbNQ+tXPW2TZ64mGyWdAPs3LibI/9nQ=', 'Javier Fernández', '675234567', '45672345M', '1980-05-05'),
    ('fran52@gmail.com', 'rI3QaS7ZW6phRbNQ+tXPW2TZ64mGyWdAPs3LibI/9nQ=', 'Francisco Pérez', '659235785', '12893462Z', '1975-07-03');

INSERT INTO users(email, password, name, tel, cif, address, worth, data, verified ) VALUES
    ('sunhotels@gmail.com', 'rI3QaS7ZW6phRbNQ+tXPW2TZ64mGyWdAPs3LibI/9nQ=', 'Sun Hotels', '912849562', 'A34892634', 'Calle Mayor 20, Barcelona', '1200000', 'Datos de Sun Hotels', 'true'),
    ('happyhotels@gmail.com', 'rI3QaS7ZW6phRbNQ+tXPW2TZ64mGyWdAPs3LibI/9nQ=', 'Happy Hotels', '976541236', 'A45581236', 'Plaza España 3, Valencia', '2500000', 'Datos de Happy Hotels', 'false');

INSERT INTO users(email, password, name, tel, verified) VALUES
    ('admin@admin.com','jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'admin', 0, true);

INSERT INTO chatlog(message) VALUES
    ('Javier se unió'),
    ('Sun Hotels se unió'),
    ('Javier: Hola'),
    ('Javier: Hola?'),
    ('Javier: Hay alguien?'),
    ('Sun Hotels: Hola Javier, en qué podemos ayudarte?'),
    ('Javier: Hola, el hotel dispone de piscina?'),
    ('Sun Hotels: Podrías decirnos el nombre del hotel?'),
    ('Javier: El hotel bahía en Benidorm'),
    ('Sun Hotels: Lamentablemente no dispone de piscina'),
    ('Sun Hotels: Pero la playa está a 200 metros del hotel'),
    ('Javier: Vale, muchas gracias'),
    ('Javier se desconectó'),
    ('Fran: Yo también tenía una duda sobre el mismo hotel'),
    ('Fran: A que hora son los desayunos'),
    ('Sun Hotels: Los desayunos son de 8:30 a 11:00'),
    ('Fran: No podría ir antes?'),
    ('Sun Hotels: Lo he consultado y me temo que eso va a ser imposible'),
    ('Fran: De acuerdo'),
    ('Fran se desconectó'),
    ('Sun Hotels se desconectó');

INSERT INTO hotel(name, city, rooms, price, services, owner) VALUES
    ('Hotel Bahía', 'Benidorm', '350', '65.89', 'Wifi, Buffet', 'sunhotels@gmail.com'),
    ('Hotel El Reclamo', 'Valencia', '170', '70.59', 'Wifi, Piscina', 'sunhotels@gmail.com'),
    ('Hotel Reposo', 'Valencia', '290', '53.49', 'Wifi, Buffet, Spa', 'sunhotels@gmail.com');

INSERT INTO reserve(customer, hotel, day) VALUES
    ('jfernandez@gmail.com', '1', '2022-06-19');