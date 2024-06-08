CREATE SEQUENCE sch_keyword.seq_keyword_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE SEQUENCE sch_keyword.seq_keyword_portal_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE TABLE sch_keyword.tbl_keyword (
    "fld_keyword_id" integer DEFAULT nextval('sch_keyword.seq_keyword_id') NOT NULL UNIQUE,
    "fld_word" text NOT NULL,
    "fld_catagory_id" int NULL
) WITH (oids = false);

CREATE INDEX "IDX_CATAGORY" on sch_keyword.tbl_keyword("fld_catagory_id");

CREATE TABLE sch_keyword.tbl_keyword_portal (
    "fld_keyword_portal_id" integer DEFAULT nextval('sch_keyword.seq_keyword_portal_id') NOT NULL UNIQUE,
    "fld_portal_id" int NOT NULL,
    "fld_keyword_id" int NOT NULL
) WITH (oids = false);

CREATE INDEX "IDX_PORTAL" on sch_keyword.tbl_keyword_portal("fld_portal_id");
CREATE INDEX "IDX_KEYWORD" on sch_keyword.tbl_keyword_portal("fld_keyword_id");
