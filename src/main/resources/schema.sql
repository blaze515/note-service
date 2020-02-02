DROP TABLE IF EXISTS notebooks;
DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS note_tags;

CREATE TABLE notebooks(id SERIAL AUTO_INCREMENT PRIMARY KEY, name varchar(255));
CREATE TABLE notes(id SERIAL AUTO_INCREMENT PRIMARY KEY, title varchar(255), body text, created timestamp, last_modified timestamp, notebook_id int references notebooks(id));
CREATE TABLE tags(id SERIAL AUTO_INCREMENT PRIMARY KEY, tag varchar(255));
CREATE TABLE note_tag(tag_id int references tags(id) ON UPDATE CASCADE ON DELETE CASCADE,
    note_id int references notes(id) ON UPDATE CASCADE ON DELETE CASCADE,
    constraint tag_note_pkey PRIMARY KEY (tag_id, note_id));