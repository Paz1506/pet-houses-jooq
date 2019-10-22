/*
 * This file is generated by jOOQ.
 */
package com.zaytsevp.pethousesjooq.model.tables;


import com.zaytsevp.pethousesjooq.model.Indexes;
import com.zaytsevp.pethousesjooq.model.Keys;
import com.zaytsevp.pethousesjooq.model.Public;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class House extends TableImpl<HouseRecord> {

    private static final long serialVersionUID = 811923340;

    /**
     * The reference instance of <code>public.house</code>
     */
    public static final House HOUSE = new House();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<HouseRecord> getRecordType() {
        return HouseRecord.class;
    }

    /**
     * The column <code>public.house.id</code>.
     */
    public final TableField<HouseRecord, String> ID = createField("id", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "");

    /**
     * The column <code>public.house.name</code>.
     */
    public final TableField<HouseRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.house.capacity</code>.
     */
    public final TableField<HouseRecord, Integer> CAPACITY = createField("capacity", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.house.object_size</code>.
     */
    public final TableField<HouseRecord, String> OBJECT_SIZE = createField("object_size", org.jooq.impl.SQLDataType.VARCHAR(5).nullable(false).defaultValue(org.jooq.impl.DSL.field("'XS'::character varying", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>public.house.status</code>.
     */
    public final TableField<HouseRecord, String> STATUS = createField("status", org.jooq.impl.SQLDataType.VARCHAR.nullable(false).defaultValue(org.jooq.impl.DSL.field("'ACTIVE'::character varying", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>public.house.filled</code>.
     */
    public final TableField<HouseRecord, Boolean> FILLED = createField("filled", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.house.house_keeper_id</code>.
     */
    public final TableField<HouseRecord, String> HOUSE_KEEPER_ID = createField("house_keeper_id", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "");

    /**
     * Create a <code>public.house</code> table reference
     */
    public House() {
        this(DSL.name("house"), null);
    }

    /**
     * Create an aliased <code>public.house</code> table reference
     */
    public House(String alias) {
        this(DSL.name(alias), HOUSE);
    }

    /**
     * Create an aliased <code>public.house</code> table reference
     */
    public House(Name alias) {
        this(alias, HOUSE);
    }

    private House(Name alias, Table<HouseRecord> aliased) {
        this(alias, aliased, null);
    }

    private House(Name alias, Table<HouseRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> House(Table<O> child, ForeignKey<O, HouseRecord> key) {
        super(child, key, HOUSE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.HOUSE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<HouseRecord> getPrimaryKey() {
        return Keys.HOUSE_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<HouseRecord>> getKeys() {
        return Arrays.<UniqueKey<HouseRecord>>asList(Keys.HOUSE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<HouseRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<HouseRecord, ?>>asList(Keys.HOUSE__HOUSE_HOUSE_KEEPER_ID_FKEY);
    }

    public HouseKeeper houseKeeper() {
        return new HouseKeeper(this, Keys.HOUSE__HOUSE_HOUSE_KEEPER_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public House as(String alias) {
        return new House(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public House as(Name alias) {
        return new House(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public House rename(String name) {
        return new House(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public House rename(Name name) {
        return new House(name, null);
    }
}
