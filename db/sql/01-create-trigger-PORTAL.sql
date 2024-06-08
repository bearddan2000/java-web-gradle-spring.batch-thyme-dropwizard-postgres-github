CREATE OR REPLACE FUNCTION sch_portal.fn_ins_up_before()
  RETURNS trigger AS
$func$
BEGIN

IF EXISTS (SELECT 1 FROM sch_portal.tbl_portal
           WHERE (fld_name)
           = (NEW.fld_name)) THEN
   RETURN NULL;
END IF;

RETURN NEW;

END
$func$  LANGUAGE plpgsql;

CREATE TRIGGER trg_ins_up_before
BEFORE INSERT OR UPDATE OF fld_name
ON sch_portal.tbl_portal
FOR EACH ROW EXECUTE PROCEDURE sch_portal.fn_ins_up_before();
