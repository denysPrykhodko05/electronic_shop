package com.epam.prykhodko.bean;

import static com.epam.prykhodko.constants.ApplicationConstants.CARD_NUMBER;
import static com.epam.prykhodko.constants.ApplicationConstants.CVC;
import static com.epam.prykhodko.constants.ApplicationConstants.DATA_OF_CARD;
import static com.epam.prykhodko.constants.ApplicationConstants.DELIVERY_METHOD;
import static com.epam.prykhodko.constants.ApplicationConstants.PAYMENT_METHOD;

import javax.servlet.http.HttpServletRequest;

public class OrderBean {

    private String payment;
    private String delivery;
    private String cardNumber;
    private String dataOfCard;
    private String cvc;

    public void setOrderBeanFromRequest(HttpServletRequest request) {
        payment = request.getParameter(PAYMENT_METHOD);
        delivery = request.getParameter(DELIVERY_METHOD);
        cardNumber = request.getParameter(CARD_NUMBER);
        dataOfCard = request.getParameter(DATA_OF_CARD);
        cvc = request.getParameter(CVC);
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
