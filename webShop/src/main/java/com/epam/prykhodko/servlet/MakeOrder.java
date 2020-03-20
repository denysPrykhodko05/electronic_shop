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
import static com.epam.prykhodko.constants.ApplicationConstants.HOME_URL;
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
import com.epam.prykhodko.entity.OrderedProduct;
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
public class MakeOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<Product, Integer> cart = ((Cart) session.getAttribute(CART)).getCart();
        List<OrderedProduct> orderedProducts = new ArrayList<>();

        cart.forEach((k, v) -> orderedProducts.add(createOrderedProduct(k, v)));

        session.setAttribute(ORDERED_PRODUCTS, orderedProducts);
        req.getRequestDispatcher("jsp/OrderPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ServletContext servletContext = req.getServletContext();
        Cart cart = (Cart) session.getAttribute(CART);
        Validator validator = (Validator) servletContext.getAttribute(VALIDATOR);
        UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        OrderService orderService = (OrderService) servletContext.getAttribute(ORDER_SERVICE);
        List<OrderedProduct> orderedProducts = (List<OrderedProduct>) session.getAttribute(ORDERED_PRODUCTS);
        String login = (String) session.getAttribute(USER_LOGIN);
        OrderBean orderBean = new OrderBean();
        Map<String, String> errors = new LinkedHashMap<>();
        Order order;
        User user;

        orderBean.setOrderBeanFromRequest(req);
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

        user = userService.getByLogin(login);
        order = fillOrder(orderedProducts, user.getEmail(), OrderStatus.ACCEPTED);

        if (Objects.isNull(orderService.add(order))) {
            resp.sendRedirect(MAKE_ORDER);
            return;
        }

        cart.getCart().clear();
        resp.sendRedirect(HOME_URL);
    }

    private OrderedProduct createOrderedProduct(Product product, int amount) {
        OrderedProduct orderedProduct = new OrderedProduct();
        orderedProduct.setProductId(product.getId());
        orderedProduct.setPrice(product.getPrice());
        orderedProduct.setAmount(amount);
        return orderedProduct;
    }

    private Order fillOrder(List<OrderedProduct> orderedProducts, String email, OrderStatus orderStatus) {
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setOrderedProducts(orderedProducts);
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
