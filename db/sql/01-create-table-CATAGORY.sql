CREATE SEQUENCE sch_catagory.seq_catagory_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE SEQUENCE sch_catagory.seq_class_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE SEQUENCE sch_catagory.seq_family_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE SEQUENCE sch_catagory.seq_worktable_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE SEQUENCE sch_catagory.seq_catagory_keyword_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE TABLE sch_catagory.tbl_catagory (
    "fld_catagory_id" integer DEFAULT nextval('sch_catagory.seq_catagory_id') NOT NULL UNIQUE,
    "fld_word" text NOT NULL,
    "fld_class_id" int NOT NULL,
    "fld_family_id" int NOT NULL
) WITH (oids = false);

CREATE INDEX "IDX_WORD" on sch_catagory.tbl_catagory("fld_word");
CREATE INDEX "IDX_CLASS" on sch_catagory.tbl_catagory("fld_class_id");
CREATE INDEX "IDX_FAMILY" on sch_catagory.tbl_catagory("fld_family_id");

CREATE TABLE sch_catagory.tbl_catagory_keyword (
    "fld_catagory_keyword_id" integer DEFAULT nextval('sch_catagory.seq_catagory_keyword_id') NOT NULL UNIQUE,
    "fld_catagory_id" int NOT NULL,
    "fld_keyword_id" int NOT NULL,
    "fld_build_id" int NOT NULL,
    "fld_lang_id" int NOT NULL,
    "fld_platform_id" int NOT NULL,
    "fld_tech_id" int NOT NULL
) WITH (oids = false);

CREATE INDEX "IDX_CATAGORY" on sch_catagory.tbl_catagory_keyword("fld_catagory_id");
CREATE INDEX "IDX_KEYWORD" on sch_catagory.tbl_catagory_keyword("fld_keyword_id");
CREATE INDEX "IDX_BUILD" on sch_catagory.tbl_catagory_keyword("fld_build_id");
CREATE INDEX "IDX_LANG" on sch_catagory.tbl_catagory_keyword("fld_lang_id");
CREATE INDEX "IDX_PLATFORM" on sch_catagory.tbl_catagory_keyword("fld_platform_id");
CREATE INDEX "IDX_TECH" on sch_catagory.tbl_catagory_keyword("fld_tech_id");

CREATE TABLE sch_catagory.tbl_class (
    "fld_class_id" integer DEFAULT nextval('sch_catagory.seq_class_id') NOT NULL UNIQUE,
    "fld_name" text NOT NULL
) WITH (oids = false);

CREATE TABLE sch_catagory.tbl_family (
    "fld_family_id" integer DEFAULT nextval('sch_catagory.seq_family_id') NOT NULL UNIQUE,
    "fld_name" text NOT NULL
) WITH (oids = false);

CREATE TABLE sch_catagory.tbl_worktable (
    "fld_worktable_id" integer DEFAULT nextval('sch_catagory.seq_worktable_id') NOT NULL UNIQUE,
    "fld_word" text NOT NULL,
    "fld_class" text NOT NULL,
    "fld_family" text NOT NULL
) WITH (oids = false);
