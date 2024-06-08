INSERT INTO sch_filter.tbl_color (fld_name)
VALUES
('aqua'),('burlywood'),('cadetblue'),('darkgrey'),
('khaki'),('orange'),('pink'),('purple'),
('sienna');

INSERT INTO sch_filter.tbl_build (fld_type, fld_color_id, fld_name)
VALUES
(1, 7, 'unclass'),(1, 6,'xcode'),
(1, 5,'cargo'),(1, 3, 'ant'),(1, 1, 'android'),
(1, 1, 'bazel'),(1, 3, 'buck'),(1, 3, 'gradle'),
(1, 3, 'maven'),(1, 3, 'mill'),(1, 1, 'pants'),
(1, 1, 'spa'),(1, 3, 'sbt'),(1, 3, 'bloop');

INSERT INTO sch_filter.tbl_lang (fld_type, fld_color_id, fld_name)
VALUES
(2, 7,'unclass'),(2, 6,'swift'),
(2, 5,'lua'), (2, 3,'java'),(2, 3,'jruby'),(2, 3,'jython'),
(2, 3,'kotlin'),(2, 3,'groovy'),(2, 3,'scala'),
(2, 5,'rust'),(2, 9,'dart'),(2, 5,'c'),(2, 5,'cpp'),
(2, 2,'csharp'),(2, 5, 'perl'),(2, 3,'python'),
(2, 5,'php'),(2, 9,'nodejs'),(2, 9,'javascript'),
(2, 9,'flutter'),(2, 3,'clojure'),(2, 5,'ruby'),
(2, 5,'crystal'),(2, 2,'vbnet'),(2, 6,'erlang'),
(2, 6,'elixir'),(2, 6,'haskell'),(2, 3,'golang'),
(2, 4,'julia'),(2, 5,'dlang');

INSERT INTO sch_filter.tbl_platform (fld_type, fld_color_id, fld_name)
VALUES
(3, 7,'unclass'),
(3, 3,'web'),(3, 6,'desktop'),(3, 9,'cli');

INSERT INTO sch_filter.tbl_tech (fld_type, fld_color_id, fld_name)
VALUES
(4, 7,'unclass'),
(4, 8,'ansible'),(4, 8,'saltstack'),(4, 8,'chef'),
(4, 8,'powershell'),(4, 8,'bash');

WITH RECURSIVE CTE_BUILD AS
(
  SELECT 1 as filter_id, 'unclass' as name, 'pink' as color, 1 as type_filter
  UNION ALL
  SELECT filter_id+1, build.fld_name as name,
  color.fld_name as color, build.fld_type
  FROM CTE_BUILD as cte
  JOIN sch_filter.tbl_build as build ON build.fld_build_id = cte.filter_id+1
  JOIN sch_filter.tbl_color as color ON color.fld_color_id = build.fld_color_id
  WHERE filter_id < (SELECT max(fld_build_id)+1 FROM sch_filter.tbl_build)
), CTE_LANG AS
(
  SELECT 1 as filter_id, 'unclass' as name, 'pink' as color, 2 as type_filter
  UNION ALL
  SELECT filter_id+1, lang.fld_name as name,
  color.fld_name as color, lang.fld_type
  FROM CTE_LANG as cte
  JOIN sch_filter.tbl_lang as lang ON lang.fld_lang_id = cte.filter_id+1
  JOIN sch_filter.tbl_color as color ON color.fld_color_id = lang.fld_color_id
  WHERE filter_id < (SELECT max(fld_lang_id)+1 FROM sch_filter.tbl_lang)
), CTE_PLATFORM AS
(
  SELECT 1 as filter_id, 'unclass' as name, 'pink' as color, 3 as type_filter
  UNION ALL
  SELECT filter_id+1, platform.fld_name as name,
  color.fld_name as color, platform.fld_type
  FROM CTE_PLATFORM as cte
  JOIN sch_filter.tbl_platform as platform ON platform.fld_platform_id = cte.filter_id+1
  JOIN sch_filter.tbl_color as color ON color.fld_color_id = platform.fld_color_id
  WHERE filter_id < (SELECT max(fld_platform_id)+1 FROM sch_filter.tbl_platform)
), CTE_TECH AS
(
  SELECT 1 as filter_id, 'unclass' as name, 'pink' as color, 4 as type_filter
  UNION ALL
  SELECT filter_id+1, tech.fld_name as name,
  color.fld_name as color, tech.fld_type
  FROM CTE_TECH as cte
  JOIN sch_filter.tbl_tech as tech ON tech.fld_tech_id = cte.filter_id+1
  JOIN sch_filter.tbl_color as color ON color.fld_color_id = tech.fld_color_id
  WHERE filter_id < (SELECT max(fld_tech_id)+1 FROM sch_filter.tbl_tech)
)

INSERT INTO sch_filter.tbl_filter (fld_recursive_id, fld_name, fld_color, fld_type)
SELECT * FROM CTE_BUILD
UNION ALL
SELECT * FROM CTE_LANG
UNION ALL
SELECT * FROM CTE_PLATFORM
UNION ALL
SELECT * FROM CTE_TECH;
