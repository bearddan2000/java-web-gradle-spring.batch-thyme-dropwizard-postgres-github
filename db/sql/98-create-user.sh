#!/usr/bin/env bash

set -e
set -u
	# GRANT SELECT ON "$POSTGRES_DB.$schema.$view" TO $usr;
function create_user_and_database() {
	local usr="usr_$1"
	local schema="sch_$1"
	local view="vu_$1"
	echo "  Creating user '$usr'"

	psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d "$POSTGRES_DB" <<-EOSQL
	    CREATE USER $usr WITH PASSWORD 'pass';
	    GRANT USAGE ON SCHEMA $schema TO $usr;
	
EOSQL
}

if [ -n "$POSTGRES_MULTIPLE_SCHEMAS" ]; then
	echo "Multiple users creation requested: $POSTGRES_MULTIPLE_SCHEMAS"
	for db in $(echo $POSTGRES_MULTIPLE_SCHEMAS | tr ',' ' '); do
		create_user_and_database $db
	done
	echo "Multiple users created"
fi
