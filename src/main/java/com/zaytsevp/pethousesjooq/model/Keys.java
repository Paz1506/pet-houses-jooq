/*
 * This file is generated by jOOQ.
 */
package com.zaytsevp.pethousesjooq.model;


import com.zaytsevp.pethousesjooq.model.tables.House;
import com.zaytsevp.pethousesjooq.model.tables.HouseKeeper;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseKeeperRecord;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
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
    public static final UniqueKey<HouseKeeperRecord> HOUSE_KEEPER_PKEY = UniqueKeys0.HOUSE_KEEPER_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<HouseRecord, HouseKeeperRecord> HOUSE__HOUSE_HOUSE_KEEPER_ID_FKEY = ForeignKeys0.HOUSE__HOUSE_HOUSE_KEEPER_ID_FKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<HouseRecord> HOUSE_PKEY = Internal.createUniqueKey(House.HOUSE, "house_pkey", House.HOUSE.ID);
        public static final UniqueKey<HouseKeeperRecord> HOUSE_KEEPER_PKEY = Internal.createUniqueKey(HouseKeeper.HOUSE_KEEPER, "house_keeper_pkey", HouseKeeper.HOUSE_KEEPER.ID);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<HouseRecord, HouseKeeperRecord> HOUSE__HOUSE_HOUSE_KEEPER_ID_FKEY = Internal.createForeignKey(com.zaytsevp.pethousesjooq.model.Keys.HOUSE_KEEPER_PKEY, House.HOUSE, "house__house_house_keeper_id_fkey", House.HOUSE.HOUSE_KEEPER_ID);
    }
}
