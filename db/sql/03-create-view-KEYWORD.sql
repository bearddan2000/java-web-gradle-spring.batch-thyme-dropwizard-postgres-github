CREATE VIEW db_app.sch_keyword.vu_keyword AS
WITH CTE_KEYWORD AS (
  SELECT fld_keyword_portal_id as id, COUNT(*) as count
  FROM sch_keyword.tbl_keyword_portal
  GROUP BY fld_keyword_portal_id
)
SELECT cte.id as id, fld_word as word, cte.count as count
FROM sch_keyword.tbl_keyword keyword
JOIN CTE_KEYWORD as cte ON cte.id = keyword.fld_keyword_id
ORDER BY fld_word;
