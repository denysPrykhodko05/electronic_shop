package com.nure.prykhodko.bean;

import com.nure.prykhodko.constants.ApplicationConstants;
import javax.servlet.http.HttpServletRequest;

public class OrderBean {

    private static String payment;
    private static String delivery;
    private static String cardNumber;
    private static String dataOfCard;
    private static String cvc;

    public static OrderBean createOrderBeanFromRequest(HttpServletRequest request) {
        OrderBean orderBean = new OrderBean();
        orderBean.payment = request.getParameter(ApplicationConstants.PAYMENT_METHOD);
        orderBean.delivery = request.getParameter(ApplicationConstants.DELIVERY_METHOD);
        orderBean.cardNumber = request.getParameter(ApplicationConstants.CARD_NUMBER);
        orderBean.dataOfCard = request.getParameter(ApplicationConstants.DATA_OF_CARD);
        orderBean.cvc = request.getParameter(ApplicationConstants.CVC);
        return orderBean;
    }

    public String getPayment() {
        return payment;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getDataOfCard() {
        return dataOfCard;
    }

    public String getCvc() {
        return cvc;
    }
}
