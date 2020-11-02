DROP TABLE IF EXISTS Metadatainfo

CREATE TABLE Metadatainfo(
docid varchar(10),
doctype varchar(30),
docsize varchar(30));

INSERT INTO MetadataInfo
(docid, doctype, docsize)
VALUES('1','pdf','25Kb');

INSERT INTO MetadataInfo
(docid, doctype, docsize)
VALUES('2','jpg','196Kb');
