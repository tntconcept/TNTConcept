package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;

public class OfferDAO_IT extends IntegrationTest {
    private final OfferDAO offerDAO;
    public OfferDAO_IT() {
        offerDAO = (OfferDAO) SpringUtils.getSpringBean("daoOffer");
    }


}
