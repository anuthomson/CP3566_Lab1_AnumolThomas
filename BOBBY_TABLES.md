# BOBBY_TABLES.md

## SearchUnsafe Alice

Final SQL: SELECT id, name, program, gpa FROM student WHERE name LIKE '%Alice%'

1 | Alice Park | CS | 3.92

## SearchSafe Alice

1 | Alice Park | CS | 3.92

## SearchUnsafe "%' OR '1'='1"

Final SQL: SELECT id, name, program, gpa FROM student WHERE name LIKE '%% OR 1=1%'

No matching rows returned.

## SearchSafe "%' OR '1'='1"

No matching rows returned.
