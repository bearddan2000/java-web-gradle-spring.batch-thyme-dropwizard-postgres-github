CREATE OR REPLACE FUNCTION sch_catagory.fn_ins_up_before()
  RETURNS trigger AS
$func$
DECLARE
    v_class_id int;
    v_family_id int;
BEGIN

IF NOT EXISTS (SELECT 1 FROM sch_catagory.tbl_class
           WHERE (fld_name)
           = (NEW.fld_class)) THEN
   
    INSERT INTO sch_catagory.tbl_class (fld_name)
    VALUES (NEW.fld_class);

END IF;

IF NOT EXISTS (SELECT 1 FROM sch_catagory.tbl_family
           WHERE (fld_name)
           = (NEW.fld_family)) THEN

    INSERT INTO sch_catagory.tbl_family (fld_name)
    VALUES (NEW.fld_family);

END IF;

    SELECT fld_class_id 
    INTO v_class_id
    FROM sch_catagory.tbl_class
    WHERE (fld_name) = (NEW.fld_class);

    SELECT fld_family_id
    INTO v_family_id
    FROM sch_catagory.tbl_family
    WHERE (fld_name) = (NEW.fld_family);

    INSERT INTO sch_catagory.tbl_catagory (fld_word, fld_class_id, fld_family_id)
    VALUES(NEW.fld_word, v_class_id, v_family_id);

RETURN NEW;

END
$func$  LANGUAGE plpgsql;

CREATE TRIGGER trg_ins_up_before
BEFORE INSERT OR UPDATE OF fld_word
ON sch_catagory.tbl_worktable
FOR EACH ROW EXECUTE PROCEDURE sch_catagory.fn_ins_up_before();
