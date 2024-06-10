CREATE TABLE books (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	title varchar(255) NOT NULL,
	isbn_10 varchar(10),
	isbn_13 varchar(13),
	image_url text,
	author varchar(255),
	recomended_counts INTEGER NOT NULL DEFAULT 0,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
