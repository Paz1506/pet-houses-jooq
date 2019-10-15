package com.zaytsevp.pethousesjooq.config;

import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

/**
 * Created by Pavel Zaytsev
 */
public class ExceptionTranslator extends DefaultExecuteListener {
    public void exception(ExecuteContext context) {
        SQLDialect dialect = context.configuration()
                                    .dialect();

        SQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator(dialect.name());

        context.exception(translator
                                  .translate("Access database using jOOQ",
                                             context.sql(),
                                             context.sqlException()));
    }
}
