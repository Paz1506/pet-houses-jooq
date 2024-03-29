/*
 * This file is generated by jOOQ.
 */
package com.zaytsevp.pethousesjooq.model.tables.records;


import com.zaytsevp.pethousesjooq.model.tables.House;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HouseRecord extends UpdatableRecordImpl<HouseRecord> implements Record7<String, String, Integer, String, String, Boolean, String> {

    private static final long serialVersionUID = 1365797330;

    /**
     * Setter for <code>public.house.id</code>.
     */
    public void setId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.house.id</code>.
     */
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.house.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.house.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.house.capacity</code>.
     */
    public void setCapacity(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.house.capacity</code>.
     */
    public Integer getCapacity() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.house.object_size</code>.
     */
    public void setObjectSize(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.house.object_size</code>.
     */
    public String getObjectSize() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.house.status</code>.
     */
    public void setStatus(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.house.status</code>.
     */
    public String getStatus() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.house.filled</code>.
     */
    public void setFilled(Boolean value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.house.filled</code>.
     */
    public Boolean getFilled() {
        return (Boolean) get(5);
    }

    /**
     * Setter for <code>public.house.house_keeper_id</code>.
     */
    public void setHouseKeeperId(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.house.house_keeper_id</code>.
     */
    public String getHouseKeeperId() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<String, String, Integer, String, String, Boolean, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<String, String, Integer, String, String, Boolean, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return House.HOUSE.ID;
    }

    @Override
    public Field<String> field2() {
        return House.HOUSE.NAME;
    }

    @Override
    public Field<Integer> field3() {
        return House.HOUSE.CAPACITY;
    }

    @Override
    public Field<String> field4() {
        return House.HOUSE.OBJECT_SIZE;
    }

    @Override
    public Field<String> field5() {
        return House.HOUSE.STATUS;
    }

    @Override
    public Field<Boolean> field6() {
        return House.HOUSE.FILLED;
    }

    @Override
    public Field<String> field7() {
        return House.HOUSE.HOUSE_KEEPER_ID;
    }

    @Override
    public String component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Integer component3() {
        return getCapacity();
    }

    @Override
    public String component4() {
        return getObjectSize();
    }

    @Override
    public String component5() {
        return getStatus();
    }

    @Override
    public Boolean component6() {
        return getFilled();
    }

    @Override
    public String component7() {
        return getHouseKeeperId();
    }

    @Override
    public String value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public Integer value3() {
        return getCapacity();
    }

    @Override
    public String value4() {
        return getObjectSize();
    }

    @Override
    public String value5() {
        return getStatus();
    }

    @Override
    public Boolean value6() {
        return getFilled();
    }

    @Override
    public String value7() {
        return getHouseKeeperId();
    }

    @Override
    public HouseRecord value1(String value) {
        setId(value);
        return this;
    }

    @Override
    public HouseRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public HouseRecord value3(Integer value) {
        setCapacity(value);
        return this;
    }

    @Override
    public HouseRecord value4(String value) {
        setObjectSize(value);
        return this;
    }

    @Override
    public HouseRecord value5(String value) {
        setStatus(value);
        return this;
    }

    @Override
    public HouseRecord value6(Boolean value) {
        setFilled(value);
        return this;
    }

    @Override
    public HouseRecord value7(String value) {
        setHouseKeeperId(value);
        return this;
    }

    @Override
    public HouseRecord values(String value1, String value2, Integer value3, String value4, String value5, Boolean value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HouseRecord
     */
    public HouseRecord() {
        super(House.HOUSE);
    }

    /**
     * Create a detached, initialised HouseRecord
     */
    public HouseRecord(String id, String name, Integer capacity, String objectSize, String status, Boolean filled, String houseKeeperId) {
        super(House.HOUSE);

        set(0, id);
        set(1, name);
        set(2, capacity);
        set(3, objectSize);
        set(4, status);
        set(5, filled);
        set(6, houseKeeperId);
    }
}
