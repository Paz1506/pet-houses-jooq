/*
 * This file is generated by jOOQ.
 */
package com.zaytsevp.pethousesjooq.model;


import com.zaytsevp.pethousesjooq.model.tables.House;
import com.zaytsevp.pethousesjooq.model.tables.HouseKeeper;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index HOUSE_PKEY = Indexes0.HOUSE_PKEY;
    public static final Index HOUSE_KEEPER_PKEY = Indexes0.HOUSE_KEEPER_PKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index HOUSE_PKEY = Internal.createIndex("house_pkey", House.HOUSE, new OrderField[] { House.HOUSE.ID }, true);
        public static Index HOUSE_KEEPER_PKEY = Internal.createIndex("house_keeper_pkey", HouseKeeper.HOUSE_KEEPER, new OrderField[] { HouseKeeper.HOUSE_KEEPER.ID }, true);
    }
}
