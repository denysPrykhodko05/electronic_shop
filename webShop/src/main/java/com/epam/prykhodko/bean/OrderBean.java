package com.epam.prykhodko.bean;

import static com.epam.prykhodko.constants.ApplicationConstants.CARD_NUMBER;
import static com.epam.prykhodko.constants.ApplicationConstants.CVC;
import static com.epam.prykhodko.constants.ApplicationConstants.DATA_OF_CARD;
import static com.epam.prykhodko.constants.ApplicationConstants.DELIVERY_METHOD;
import static com.epam.prykhodko.constants.ApplicationConstants.PAYMENT_METHOD;

import javax.servlet.http.HttpServletRequest;

public class OrderBean {

    private static String payment;
    private static String delivery;
    private static String cardNumber;
    private static String dataOfCard;
    private static String cvc;

    public static OrderBean createOrderBeanFromRequest(HttpServletRequest request) {
        OrderBean orderBean = new OrderBean();
        orderBean.payment = request.getParameter(PAYMENT_METHOD);
        orderBean.delivery = request.getParameter(DELIVERY_METHOD);
        orderBean.cardNumber = request.getParameter(CARD_NUMBER);
        orderBean.dataOfCard = request.getParameter(DATA_OF_CARD);
        orderBean.cvc = request.getParameter(CVC);
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
