
CREATE TABLE Hoteis (
    pk_ID SERIAL PRIMARY KEY,
    Name_hotel VARCHAR(50) NOT NULL,
    Country VARCHAR(2) NOT NULL,
    State_code VARCHAR(2) NOT NULL,
    City VARCHAR(50) NOT NULL,
    Quality VARCHAR(10) NOT NULL,
    Email VARCHAR(50) UNIQUE NOT NULL,
    Telefone VARCHAR(20) UNIQUE NOT NULL
);
