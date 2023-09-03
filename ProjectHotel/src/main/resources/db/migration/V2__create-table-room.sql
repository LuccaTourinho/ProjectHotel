CREATE TABLE Rooms (
    pk_ID BIGSERIAL PRIMARY KEY,
    hotel_id BIGINT NOT NULL,
    Capacity VARCHAR(15) NOT NULL,
    Number_room INTEGER NOT NULL,
    Available BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT unique_hotel_number UNIQUE (hotel_id, Number_room),
    FOREIGN KEY (hotel_id) REFERENCES Hoteis(pk_ID) ON DELETE cascade
);
