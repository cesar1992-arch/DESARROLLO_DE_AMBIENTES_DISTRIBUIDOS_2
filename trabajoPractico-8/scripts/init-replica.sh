#!/usr/bin/env bash
set -euo pipefail

: "${PGDATA:?PGDATA must be set}"
: "${REPLICATION_PASSWORD:?REPLICATION_PASSWORD must be set}"

mkdir -p "$PGDATA"
chmod 0700 "$PGDATA"

printf 'postgres-primary:5432:*:replicator:%s\n' "$REPLICATION_PASSWORD" > "$HOME/.pgpass"
chmod 0600 "$HOME/.pgpass"
export PGPASSFILE="$HOME/.pgpass"

if [[ ! -s "$PGDATA/PG_VERSION" ]]; then
  if [[ -n "$(find "$PGDATA" -mindepth 1 -maxdepth 1 -print -quit)" ]]; then
    echo "PGDATA is not empty but has no PG_VERSION; refusing to overwrite it" >&2
    exit 1
  fi

  export PGPASSWORD="$REPLICATION_PASSWORD"

  echo "Initializing standby from postgres-primary with pg_basebackup"
  pg_basebackup \
    --host=postgres-primary \
    --username=replicator \
    --pgdata="$PGDATA" \
    --format=plain \
    --wal-method=stream \
    --write-recovery-conf \
    --progress
fi

exec postgres -c hot_standby=on
