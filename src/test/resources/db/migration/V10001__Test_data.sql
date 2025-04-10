INSERT INTO books (id, title, description, author, publisher, year_published, isbn, pages) VALUES
('66c25e62-7363-4fe7-8f05-91791db65789', 'A Book', 'Some A Description', 'Andrey B', 'AAA', 2002, '2-22-xxxxxx-x', 202),
('450436a2-c9de-4ade-8a2c-5230313de3e4', 'B Book', 'Some B Description', 'Alice A', 'AAA', 2004, '1-11-xxxxxx-x', 101),
('2e4a8829-5f2e-41d6-97ea-acc564d64949', 'C Book', 'Some C Description', 'Bob B', 'BBB', 900, '5-55-xxxxxx-x', 505),
('92605494-a209-417b-b0da-9e70187c5c35', 'D Book', 'Some D Description', 'Ivan I', 'BBB', 17, '3-33-xxxxxx-x', 303),
('02db4b9b-7739-4bb8-884c-a8a975e5ae6c', 'E Book', 'Some E Description', 'Ivan I', 'AAA', 40000, '4-44-xxxxxx-x', 404),
('5d643b16-ebad-4b1b-b586-2ee92b070d00', 'H Book', 'Some H Description', 'Rob R', 'CCC', 2002, '6-66-xxxxxx-x', 606);

INSERT INTO tags (id, name) VALUES
('7efab360-8043-4eee-bb90-835917339fdb', 'Tag 1'),
('104105a6-a8ad-4feb-afc5-075d1b34dda8', 'Tag 2'),
('d7fb1cc0-d8c4-41cb-80fa-b87969c0b2f6', 'Tag 3'),
('0edf5bcc-d5df-442f-858e-8705969af83f', 'Tag 4'),
('c14e2444-6657-4b0a-956c-f95841cbe805', 'Tag 5');

INSERT INTO book_tags (book_id, tag_id) VALUES
('66c25e62-7363-4fe7-8f05-91791db65789', '7efab360-8043-4eee-bb90-835917339fdb'),
('450436a2-c9de-4ade-8a2c-5230313de3e4', '104105a6-a8ad-4feb-afc5-075d1b34dda8'),
('2e4a8829-5f2e-41d6-97ea-acc564d64949', 'd7fb1cc0-d8c4-41cb-80fa-b87969c0b2f6'),
('92605494-a209-417b-b0da-9e70187c5c35', '0edf5bcc-d5df-442f-858e-8705969af83f'),
('02db4b9b-7739-4bb8-884c-a8a975e5ae6c', 'c14e2444-6657-4b0a-956c-f95841cbe805'),
('02db4b9b-7739-4bb8-884c-a8a975e5ae6c', 'd7fb1cc0-d8c4-41cb-80fa-b87969c0b2f6');
