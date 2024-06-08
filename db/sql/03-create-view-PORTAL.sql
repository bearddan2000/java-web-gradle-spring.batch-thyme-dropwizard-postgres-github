CREATE VIEW db_app.sch_portal.vu_portal AS
WITH CTE_FILTER_BUILD AS (
  SELECT fld_portal_id as id, jsonb_build_object('name', filter.fld_name, 'color', fld_color) as filter_json
  FROM sch_portal.tbl_portal as portal
  JOIN sch_filter.vu_filter as filter ON filter.fld_filter_id = portal.fld_build_id
), CTE_FILTER_LANG AS (
  SELECT fld_portal_id as id, jsonb_build_object('name', filter.fld_name, 'color', fld_color) as filter_json
  FROM sch_portal.tbl_portal as portal
  JOIN sch_filter.vu_filter as filter ON filter.fld_filter_id = portal.fld_lang_id
), CTE_FILTER_PLATFORM AS (
  SELECT fld_portal_id as id, jsonb_build_object('name', filter.fld_name, 'color', fld_color) as filter_json
  FROM sch_portal.tbl_portal as portal
  JOIN sch_filter.vu_filter as filter ON filter.fld_filter_id = portal.fld_platform_id
), CTE_FILTER_TECH AS (
  SELECT fld_portal_id as id, jsonb_build_object('name', filter.fld_name, 'color', fld_color) as filter_json
  FROM sch_portal.tbl_portal as portal
  JOIN sch_filter.vu_filter as filter ON filter.fld_filter_id = portal.fld_tech_id
), CTE_FILTER AS (
  SELECT fld_portal_id as id,
  build_filter.filter_json as build_filter_json,
  lang_filter.filter_json as lang_filter_json,
  platform_filter.filter_json as platform_filter_json,
  tech_filter.filter_json as tech_filter_json
  FROM sch_portal.tbl_portal as portal
  JOIN CTE_FILTER_BUILD as build_filter ON build_filter.id = portal.fld_portal_id
  JOIN CTE_FILTER_LANG as lang_filter ON lang_filter.id = portal.fld_portal_id
  JOIN CTE_FILTER_PLATFORM as platform_filter ON platform_filter.id = portal.fld_portal_id
  JOIN CTE_FILTER_TECH as tech_filter ON tech_filter.id = portal.fld_portal_id
), CTE_KEYWORD AS (
  SELECT fld_portal_id as id,
  jsonb_agg( jsonb_build_object('word', keyword.fld_word)) as keyword_json
  FROM sch_portal.tbl_portal as portal
  JOIN sch_keyword.tbl_keyword as keyword ON keyword.fld_keyword_id = ANY(portal.fld_keyword_id)
  GROUP BY fld_portal_id
), CTE_DIST_CATAGORY AS (
  SELECT keyword.fld_keyword_id as id,
  clas.fld_name as distinct_cls, family.fld_name as distinct_family
  FROM sch_catagory.tbl_catagory as catagory
  JOIN sch_catagory.tbl_class as clas ON clas.fld_class_id = catagory.fld_class_id
  JOIN sch_catagory.tbl_family as family ON family.fld_family_id = catagory.fld_family_id
  JOIN sch_catagory.tbl_catagory_keyword as catagory_keyword ON catagory_keyword.fld_catagory_id = catagory.fld_catagory_id
  RIGHT JOIN sch_keyword.tbl_keyword as keyword ON keyword.fld_keyword_id = catagory_keyword.fld_keyword_id
  GROUP BY keyword.fld_keyword_id, clas.fld_name, family.fld_name
), CTE_CATAGORY AS (
  SELECT keyword.fld_keyword_id as id,
  json_agg( jsonb_build_object('class', cte_cls.distinct_cls, 'family', cte_cls.distinct_family) ) as catagory_json
  FROM sch_catagory.tbl_catagory as catagory
  JOIN sch_catagory.tbl_catagory_keyword as catagory_keyword ON catagory_keyword.fld_catagory_id = catagory.fld_catagory_id
  RIGHT JOIN sch_keyword.tbl_keyword as keyword ON keyword.fld_keyword_id = catagory_keyword.fld_keyword_id
  JOIN CTE_DIST_CATAGORY as cte_cls ON cte_cls.id = keyword.fld_keyword_id
  GROUP BY keyword.fld_keyword_id
)

SELECT fld_portal_id as id, fld_name as name, fld_description as description,
cte_catagory.catagory_json,
cte_filter.build_filter_json, cte_filter.lang_filter_json,
cte_filter.platform_filter_json, cte_filter.tech_filter_json,
cte_keyword.keyword_json
FROM sch_portal.tbl_portal as portal
JOIN CTE_FILTER as cte_filter ON cte_filter.id = portal.fld_portal_id
JOIN CTE_KEYWORD as cte_keyword ON cte_keyword.id = portal.fld_portal_id
JOIN CTE_CATAGORY as cte_catagory ON cte_catagory.id = ANY(portal.fld_keyword_id)