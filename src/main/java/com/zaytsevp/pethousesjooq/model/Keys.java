/*
 * This file is generated by jOOQ.
 */
package com.zaytsevp.pethousesjooq.model;


import com.zaytsevp.pethousesjooq.model.tables.House;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;

import javax.annotation.Generated;

import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<HouseRecord> HOUSE_PKEY = UniqueKeys0.HOUSE_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<HouseRecord> HOUSE_PKEY = Internal.createUniqueKey(House.HOUSE, "house_pkey", House.HOUSE.ID);
    }
}
