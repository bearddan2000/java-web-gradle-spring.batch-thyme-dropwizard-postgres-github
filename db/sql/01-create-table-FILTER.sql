CREATE SEQUENCE sch_filter.seq_filter_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE SEQUENCE sch_filter.seq_color_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE SEQUENCE sch_filter.seq_build_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE SEQUENCE sch_filter.seq_lang_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE SEQUENCE sch_filter.seq_platform_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE SEQUENCE sch_filter.seq_tech_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE TABLE sch_filter.tbl_color (
    "fld_color_id" integer DEFAULT nextval('sch_filter.seq_color_id') NOT NULL UNIQUE,
    "fld_name" text NOT NULL
) WITH (oids = false);

CREATE TABLE sch_filter.tbl_build (
    "fld_build_id" integer DEFAULT nextval('sch_filter.seq_build_id') NOT NULL UNIQUE,
    "fld_name" text NOT NULL,
    "fld_color_id" int NOT NULL,
    "fld_type" int NOT NULL
) WITH (oids = false);

CREATE TABLE sch_filter.tbl_lang (
    "fld_lang_id" integer DEFAULT nextval('sch_filter.seq_lang_id') NOT NULL UNIQUE,
    "fld_name" text NOT NULL,
    "fld_color_id" int NOT NULL,
    "fld_type" int NOT NULL
) WITH (oids = false);

CREATE TABLE sch_filter.tbl_platform (
    "fld_platform_id" integer DEFAULT nextval('sch_filter.seq_platform_id') NOT NULL UNIQUE,
    "fld_name" text NOT NULL,
    "fld_color_id" int NOT NULL,
    "fld_type" int NOT NULL
) WITH (oids = false);

CREATE TABLE sch_filter.tbl_tech (
    "fld_tech_id" integer DEFAULT nextval('sch_filter.seq_tech_id') NOT NULL UNIQUE,
    "fld_name" text NOT NULL,
    "fld_color_id" int NOT NULL,
    "fld_type" int NOT NULL
) WITH (oids = false);

CREATE TABLE sch_filter.tbl_filter (
    "fld_filter_id" integer DEFAULT nextval('sch_filter.seq_filter_id') NOT NULL UNIQUE,
    "fld_recursive_id" int NOT NULL,
    "fld_name" text NOT NULL,
    "fld_color" text NOT NULL,
    "fld_type" int NOT NULL
) WITH (oids = false);

CREATE INDEX "IDX_NAME" on sch_filter.tbl_filter("fld_name");
CREATE INDEX "IDX_TYPE" on sch_filter.tbl_filter("fld_type");
