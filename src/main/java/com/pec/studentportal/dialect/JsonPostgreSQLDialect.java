package com.pec.studentportal.dialect;

import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import org.hibernate.dialect.PostgreSQLDialect;

import java.sql.Types;

public class JsonPostgreSQLDialect extends org.hibernate.dialect.PostgresPlusDialect {
    public JsonPostgreSQLDialect() {
        super();
        this.registerHibernateType(
                Types.OTHER, JsonNodeBinaryType.class.getName()
        );
    }
}
