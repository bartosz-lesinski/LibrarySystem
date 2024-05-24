CREATE TABLE IF NOT EXISTS Author (
author_id INT AUTO_INCREMENT PRIMARY KEY,
author_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Book (
id INT AUTO_INCREMENT PRIMARY KEY,
book_title VARCHAR(255) NOT NULL,
author_id INT NOT NULL,
publish_year int,
isbn VARCHAR(20) UNIQUE,
available BOOLEAN DEFAULT TRUE,
FOREIGN KEY (author_id) REFERENCES Author(author_id)
);


CREATE TABLE IF NOT EXISTS LibraryUser (
user_id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(100) NOT NULL,
last_name Varchar(100) NOT NULL ,
address VARCHAR(255),
phone_number VARCHAR(20),
email VARCHAR(100) UNIQUE
);

CREATE TABLE IF NOT EXISTS Transaction (
transaction_id INT AUTO_INCREMENT PRIMARY KEY,
book_id INT,
user_id INT,
transaction_date DATE,
return_date DATE,
fine_amount DECIMAL(10,2),
FOREIGN KEY (book_id) REFERENCES Book(id),
FOREIGN KEY (user_id) REFERENCES LibraryUser(user_id)
);
