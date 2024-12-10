-- Deterministic set of record each time a test runs. Create tests do not depend on the order in which the tests are run
truncate table products;

insert into products(code, name, description, image_url, price)
values
    ('P100', 'The Hunger Games', 'The Hunger Games is a dystopian novel by the American writer Suzanne Collins. It is written in the voice of 16-year-old Katniss Everdeen, who lives in the future, post-apocalyptic nation of Panem in North America.', 'https://images-na.ssl-images-amazon.com/images/I/51WIKlio9DL._SX331_BO1,204,203,200_.jpg', 10.99),
    ('P101', 'Harry Potter and the Philosopher''s Stone', 'Harry Potter and the Philosopher''s Stone is a fantasy novel written by British author J. K. Rowling. The first novel in the Harry Potter series and Rowling''s debut novel, it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry.', 'https://images-na.ssl-images-amazon.com/images/I/51UoqRAxwEL._SX331_BO1,204,203,200_.jpg', 7.99),
    ('P102', 'To Kill a Mockingbird', 'To Kill a Mockingbird is a novel by the American author Harper Lee. It was published in 1960 and was instantly successful. In the United States, it is widely read in high schools and middle schools.', 'https://images-na.ssl-images-amazon.com/images/I/51UoqRAxwEL._SX331_BO1,204,203,200_.jpg', 6.99),
    ('P103', 'Pride and Prejudice', 'Pride and Prejudice is a romantic novel of manners written by Jane Austen in 1813. The novel follows the character development of Elizabeth Bennet, the dynamic protagonist of the book who learns about the repercussions of hasty judgments and comes to appreciate the difference between superficial goodness and actual goodness.', 'https://images-na.ssl-images-amazon.com/images/I/51UoqRAxwEL._SX331_BO1,204,203,200_.jpg', 5.99),
    ('P104', 'The Book Thief', 'The Book Thief is a historical novel by Australian author Markus Zusak, and is his most popular work. Published in 2005, The Book Thief became an international bestseller and was translated into 63 languages and sold 16 million copies.', 'https://images-na.ssl-images-amazon.com/images/I/51UoqRAxwEL._SX331_BO1,204,203,200_.jpg', 8.99),
    ('P105', 'The Great Gatsby', 'The Great Gatsby is a novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway''s interactions with mysterious millionaire Jay Gatsby and Gatsby''s obsession to reunite with his former lover, Daisy Buchanan.', 'https://images-na.ssl-images-amazon.com/images/I/51UoqRAxwEL._SX331_BO1,204,203,200_.jpg', 9.99),
    ('P106', 'The Catcher in the Rye', 'The Catcher in the Rye is a novel by J. D. Salinger, partially published in serial form in 1945–1946 and as a novel in 1951. It was originally intended for adults, but is often read by adolescents for its themes of angst and alienation, and as a critique on superficiality in society.', 'https://images-na.ssl-images-amazon.com/images/I/51UoqRAxwEL._SX331_BO1,204,203,200_.jpg', 6.99),
    ('P107', 'The Alchemist', 'The Alchemist is a novel by Brazilian author Paulo Coelho that was first published in 1988. Originally written in Portuguese, it became a widely translated international bestseller.', 'https://images-na.ssl-images-amazon.com/images/I/51UoqRAxwEL._SX331_BO1,204,203,200_.jpg', 7.99),
    ('P108', '1984', '1984, a dystopian social science fiction novel and cautionary tale, written by the English writer George Orwell.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 8.49),
    ('P109', 'Moby-Dick', 'Moby-Dick is a novel by Herman Melville in which Captain Ahab narrates his obsessive quest to kill the white whale Moby Dick.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 10.99),
    ('P110', 'War and Peace', 'War and Peace is a novel by the Russian author Leo Tolstoy, first published serially and then in its entirety in 1869.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 12.99),
    ('P111', 'Jane Eyre', 'Jane Eyre is a novel by English writer Charlotte Brontë. It was published under her pen name Currer Bell.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 9.49),
    ('P112', 'Wuthering Heights', 'Wuthering Heights is Emily Brontë''s only novel. Written in 1847, it is a tale of passion, revenge, and the destructive power of love.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 8.99),
    ('P113', 'Brave New World', 'Brave New World is a dystopian social science fiction novel and a warning about the loss of individuality in a technologically advanced future.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 9.49),
    ('P114', 'The Hobbit', 'The Hobbit is a fantasy novel by J.R.R. Tolkien, introducing the world of Middle-earth and the journey of Bilbo Baggins.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 7.99),
    ('P115', 'Fahrenheit 451', 'Fahrenheit 451 is a dystopian novel by American writer Ray Bradbury, addressing themes of censorship and the suppression of ideas.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 6.99),
    ('P116', 'Crime and Punishment', 'Crime and Punishment is a novel by Russian author Fyodor Dostoevsky, exploring moral dilemmas and redemption.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 11.99),
    ('P117', 'The Kite Runner', 'The Kite Runner is a novel by Khaled Hosseini, depicting the unlikely friendship between a wealthy boy and the son of his father''s servant.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 8.49),
    ('P118', 'The Road', 'The Road is a novel by Cormac McCarthy, portraying a father and son''s journey through a post-apocalyptic world.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 9.99),
    ('P119', 'The Giver', 'The Giver is a novel by Lois Lowry, a story about a boy who learns the truth about his seemingly utopian community.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 6.99),
    ('P120', 'The Outsiders', 'The Outsiders is a coming-of-age novel by S.E. Hinton, exploring the divide between social classes and the struggles of youth.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 5.99),
    ('P121', 'Slaughterhouse-Five', 'Slaughterhouse-Five is a novel by Kurt Vonnegut, blending elements of science fiction and a critical examination of war.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 8.99),
    ('P122', 'Dracula', 'Dracula is a novel by Bram Stoker, introducing the iconic character of Count Dracula and establishing modern vampire lore.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 9.49),
    ('P123', 'The Odyssey', 'The Odyssey is an epic poem attributed to Homer, chronicling the adventures of Odysseus as he returns home from the Trojan War.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 10.99),
    ('P124', 'The Iliad', 'The Iliad is another epic poem attributed to Homer, depicting the events of the Trojan War and the hero Achilles.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 11.49),
    ('P125', 'Don Quixote', 'Don Quixote is a novel by Miguel de Cervantes, following the adventures of an eccentric nobleman and his loyal squire.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 9.99),
    ('P126', 'The Count of Monte Cristo', 'The Count of Monte Cristo is a novel by Alexandre Dumas, detailing a story of revenge and redemption.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 10.49),
    ('P127', 'Les Misérables', 'Les Misérables is a novel by Victor Hugo, exploring themes of justice, morality, and love in 19th-century France.', 'https://images-na.ssl-images-amazon.com/images/I/51uD38B2mKL._SX331_BO1,204,203,200_.jpg', 12.49);