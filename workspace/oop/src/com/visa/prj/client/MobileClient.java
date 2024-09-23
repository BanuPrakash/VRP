package com.visa.prj.client;

import com.visa.prj.dao.MobileDao;
import com.visa.prj.dao.MobileDaoDbImpl;
import com.visa.prj.dao.MobileDaoFactory;
import com.visa.prj.entity.Mobile;

import java.util.Comparator;

public class MobileClient {
    public static void main(String[] args) {
        Mobile m = new Mobile(523,"iPhone 15", 89000.00, "5G");

//        MobileDao mobileDao = new MobileDaoDbImpl();
        MobileDao mobileDao = MobileDaoFactory.getMobileDao();
        mobileDao.addMobile(m);
    }
}
