package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.CARD;
import static com.epam.prykhodko.constants.ApplicationConstants.CARD_DATE_REGEX;
import static com.epam.prykhodko.constants.ApplicationConstants.CARD_NUMBER;
import static com.epam.prykhodko.constants.ApplicationConstants.CARD_NUMBER_REGEX;
import static com.epam.prykhodko.constants.ApplicationConstants.CART;
import static com.epam.prykhodko.constants.ApplicationConstants.CVC;
import static com.epam.prykhodko.constants.ApplicationConstants.CVC_REGEX;
import static com.epam.prykhodko.constants.ApplicationConstants.DATA_OF_CARD;
import static com.epam.prykhodko.constants.ApplicationConstants.DELIVERY_METHOD;
import static com.epam.prykhodko.constants.ApplicationConstants.ERRORS;
import static com.epam.prykhodko.constants.ApplicationConstants.MAKE_ORDER;
import static com.epam.prykhodko.constants.ApplicationConstants.ORDERED_PRODUCTS;
import static com.epam.prykhodko.constants.ApplicationConstants.ORDER_PAGE;
import static com.epam.prykhodko.constants.ApplicationConstants.ORDER_SERVICE;
import static com.epam.prykhodko.constants.ApplicationConstants.PAYMENT_METHOD;
import static com.epam.prykhodko.constants.ApplicationConstants.REGISTRATION;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_SERVICE;
import static com.epam.prykhodko.constants.ApplicationConstants.VALIDATOR;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.bean.OrderBean;
import com.epam.prykhodko.entity.Cart;
import com.epam.prykhodko.entity.Order;
import com.epam.prykhodko.entity.OrderStatus;
import com.epam.prykhodko.entity.OrderedItem;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.service.UserService;
import com.epam.prykhodko.util.Validator;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(MAKE_ORDER)
public class MakeOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<Product, Integer> cart = ((Cart) session.getAttribute(CART)).getCart();
        List<OrderedItem> orderedItems = new ArrayList<>();

        cart.forEach((k, v) -> orderedItems.add(createOrderedProduct(k, v)));

        session.setAttribute(ORDERED_PRODUCTS, orderedItems);
        req.getRequestDispatcher(ORDER_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ServletContext servletContext = req.getServletContext();
        Cart cart = (Cart) session.getAttribute(CART);
        Validator validator = (Validator) servletContext.getAttribute(VALIDATOR);
        UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        OrderService orderService = (OrderService) servletContext.getAttribute(ORDER_SERVICE);
        List<OrderedItem> orderedItems = (List<OrderedItem>) session.getAttribute(ORDERED_PRODUCTS);
        String login = (String) session.getAttribute(USER_LOGIN);
        Map<String, String> errors = new LinkedHashMap<>();
        Order order;
        User user;

        OrderBean orderBean = OrderBean.createOrderBeanFromRequest(req);
        orderBeanValidation(orderBean, validator, errors);

        if (errors.size() > INTEGER_ZERO) {
            session.setAttribute(ERRORS, errors);
            req.getRequestDispatcher(ORDER_PAGE).forward(req, resp);
            return;
        }

        if (Objects.isNull(login)) {
            resp.sendRedirect(REGISTRATION);
            return;
        }

        user = userService.getUser(login);
        order = createOrder(orderedItems, user.getEmail(), OrderStatus.ACCEPTED);
        Order ordered = orderService.add(order);
        if (Objects.isNull(ordered)) {
            resp.sendRedirect(MAKE_ORDER);
            return;
        }

        req.setAttribute("orderId", ordered.getId());
        cart.getCart().clear();
        req.getRequestDispatcher("jsp/orderedPage.jsp").forward(req, resp);
    }

    private OrderedItem createOrderedProduct(Product product, int amount) {
        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setProductId(product.getId());
        orderedItem.setPrice(product.getPrice());
        orderedItem.setAmount(amount);
        return orderedItem;
    }

    private Order createOrder(List<OrderedItem> orderedItems, String email, OrderStatus orderStatus) {
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setOrderedItems(orderedItems);
        order.setUserEmail(email);
        order.setOrderStatus(orderStatus);
        return order;
    }

    private void orderBeanValidation(OrderBean orderBean, Validator validator, Map<String, String> errors) {
        validator.checkCheckbox(PAYMENT_METHOD, orderBean.getPayment(), errors);
        validator.checkCheckbox(DELIVERY_METHOD, orderBean.getDelivery(), errors);
        if (orderBean.getPayment().equals(CARD)) {
            validator.checkField(CARD_NUMBER, orderBean.getCardNumber(), CARD_NUMBER_REGEX, errors);
            validator.checkField(DATA_OF_CARD, orderBean.getDataOfCard(), CARD_DATE_REGEX, errors);
            validator.checkField(CVC, orderBean.getCvc(), CVC_REGEX, errors);
        }
    }

}
