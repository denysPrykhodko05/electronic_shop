package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.CART;
import static com.epam.prykhodko.constants.ApplicationConstants.HOME_URL;
import static com.epam.prykhodko.constants.ApplicationConstants.ORDERED_PRODUCTS;
import static com.epam.prykhodko.constants.ApplicationConstants.ORDER_SERVICE;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_SERVICE;

import com.epam.prykhodko.entity.Cart;
import com.epam.prykhodko.entity.Order;
import com.epam.prykhodko.entity.OrderStatus;
import com.epam.prykhodko.entity.OrderedProduct;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.service.UserService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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

@WebServlet("/makeOrder")
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
        UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        OrderService orderService = (OrderService) servletContext.getAttribute(ORDER_SERVICE);
        List<OrderedProduct> orderedProducts = (List<OrderedProduct>) session.getAttribute(ORDERED_PRODUCTS);
        String login = (String) session.getAttribute(USER_LOGIN);
        Order order = new Order();
        User user;

        if (Objects.isNull(login)) {
            resp.sendRedirect("/registration");
            return;
        }
        user = userService.getByLogin(login);
        order.setDate(LocalDate.now());
        order.setOrderedProducts(orderedProducts);
        order.setUserEmail(user.getEmail());
        order.setOrderStatus(OrderStatus.ACCEPTED);

        if (Objects.isNull(orderService.add(order))) {
            resp.sendRedirect("/makeOrder");
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
}
