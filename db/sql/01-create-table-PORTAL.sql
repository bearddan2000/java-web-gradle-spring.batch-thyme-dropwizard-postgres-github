CREATE SEQUENCE sch_portal.seq_portal_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1;

CREATE TABLE sch_portal.tbl_portal (
    "fld_portal_id" integer DEFAULT nextval('sch_portal.seq_portal_id') NOT NULL UNIQUE,
    "fld_name" text NOT NULL,
    "fld_description" text NOT NULL,
    "fld_build_id" int NOT NULL,
    "fld_lang_id" int NOT NULL,
    "fld_platform_id" int NOT NULL,
    "fld_tech_id" int NOT NULL,
    "fld_keyword_id" int[] NOT NULL
) WITH (oids = false);

CREATE INDEX "IDX_KEYWORD" on sch_portal.tbl_portal("fld_keyword_id");
CREATE INDEX "IDX_BUILD" on sch_portal.tbl_portal("fld_build_id");
CREATE INDEX "IDX_LANG" on sch_portal.tbl_portal("fld_lang_id");
CREATE INDEX "IDX_PLATFORM" on sch_portal.tbl_portal("fld_platform_id");
CREATE INDEX "IDX_TECH" on sch_portal.tbl_portal("fld_tech_id");
