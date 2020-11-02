DROP TABLE IF EXISTS documentData

CREATE TABLE documentData(
docid varchar(10),
docname varchar(30),
doclocation varchar(100));

INSERT INTO documentData
(docid, docname, doclocation)
VALUES('1','Aadhar','my D drive');

INSERT INTO documentData
(docid, docname, doclocation)
VALUES('2','PAN','HardDisk');
