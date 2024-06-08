DO
$$
    BEGIN
        IF NOT EXISTS (SELECT schema_name FROM information_schema.schemata WHERE schema_name = 'keycloak') THEN
            EXECUTE 'CREATE SCHEMA keycloak';
        END IF;
    END
$$;

DO
$$
    BEGIN
        IF NOT EXISTS (SELECT schema_name FROM information_schema.schemata WHERE schema_name = 'api') THEN
            EXECUTE 'CREATE SCHEMA api';
        END IF;
    END
$$;
