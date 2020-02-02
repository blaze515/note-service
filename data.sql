INSERT INTO notebooks (name) VALUES('My Notebook') RETURNING id;
commit;
--WITH note AS (
--    INSERT INTO notebooks VALUES('My Notebook') RETURNING id;
--)
--INSERT INTO notes VALUES('Test Note', 'Some text', current_timestamp, current_timestamp, note.id);