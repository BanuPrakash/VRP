package com.visa.prj.dao;

import com.visa.prj.entity.Mobile;

public class MobileDaoMongoDbImpl implements MobileDao{
    @Override
    public void addMobile(Mobile m) {
        System.out.println("mongo store " + m.getName());
    }
}
