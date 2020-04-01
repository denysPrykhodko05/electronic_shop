package com.epam.prykhodko.constants;

public abstract class ApplicationConstants {

    //Form strings
    public static final String USER_PERSONAL_DATA_REGEX = "^\\w+";
    public static final String LOGIN_REGEX = "^\\w{3,16}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]+)*$";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])[\\w!@#$%^&*]{8,}$";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CO_PASSWORD = "confirmPassword";
    public static final String LOGIN = "login";
    public static final String USER_LOGIN = "userLogin";
    public static final String POLICY = "privacy-policy";
    public static final String MAILS = "mails";
    public static final String REG_CAPTCHA = "regCaptcha";
    public static final String ROLE_ID = "role_id";
    public static final String AVATAR = "avatar";

    //Captcha strings
    public static final String CAPTCHA = "captcha";
    public static final String CAPTCHA_KEY = "captchaKey";
    public static final String CAPTCHA_KEYS = "captchaKeys";
    public static final String CAPTCHA_VALUE = "captchaValue";
    public static final String CAPTCHA_TIME = "captchaTime";
    public static final String CAPTCHA_KEEPER = "captcha keeper";

    //User strings
    public static final String USER_DATA = "userData";

    //Erorrs strings
    public static final String USER_LOGIN_EXISTS = "User with this login is exists";
    public static final String USER_EMAIL_EXISTS = "User with this email is exists";
    public static final String INCORRECT_INPUT = "Incorrect input of ";
    public static final String YOU_DONT_CHOOSE = "You don't choose ";
    public static final String INCORRECT_CAPTCHA = "Incorrect captcha";

    //Other strings
    public static final String SESSION = "session";
    public static final String COOKIE = "cookie";
    public static final String HIDDEN_FIELD = "hiddenField";
    public static final String KEEPERS = "keepers";
    public static final String ERRORS = "errors";
    public static final String VALIDATOR = "validator";
    public static final String USER_UTILS = "user utils";
    public static final String REG_FORM = "regFormBean";
    public static final String USER_SERVICE = "userService";
    public static final String PRODUCT_SERVICE = "productService";
    public static final String ORDER_SERVICE = "orderService";
    public static final String AMOUNT_OF_PRODUCTS = "amountOfProducts";
    public static final String IMAGE_DRAW = "imageDraw";
    public static final String AVATARS_PATH = "src\\main\\webapp\\images\\avatars\\";
    public static final String JPG_FORMAT = ".jpg";
    public static final String TEMPORARY_STORAGE = "javax.servlet.context.tempdir";
    public static final String CONTEXT_DESTROYER = "context destroyer";
    public static final String HIDDEN = "hidden";
    public static final String CONNECTION_MANAGER = "connection manager";
    public static final String NOT_USER_ERROR = "Try again";
    public static final String PREVIOUS_URL = "prevUrl";

    //URL path
    public static final String HOME_URL = "/";
    public static final String MAKE_ORDER_USER_CHECK_URL = "/makeOrderUserCheck";
    public static final String REGISTRATION = "/registration";
    public static final String MAKE_ORDER = "/makeOrder";

    //JSP link
    public static final String PRODUCT_JSP = "jsp/products.jsp";
    public static final String REGISTRATION_JSP_LINK = "jsp/registration.jsp";
    public static final String LOGIN_JSP_LINK = "jsp/logIn.jsp";
    public static final String ORDER_PAGE = "jsp/orderPage.jsp";

    //Product parameters
    public static final String MANUFACTURE = "manufacture";
    public static final String CATEGORY = "category";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";

    //Filter form
    public static final String MIN_PRICE = "minPrice";
    public static final String MAX_PRICE = "maxPrice";
    public static final String SORT = "sort";

    //Product page constants
    public static final String STRING_ONE_MILLION = "1000000";

    //Product page request constants
    public static final String FILTERS = "filters";
    public static final String FILTER_QUERY = "FILTER_QUERY";
    public static final String PAGE = "page";
    public static final String AMOUNT_OF_PRODUCTS_FROM_FORM = "amountOfProductsFromForm";
    public static final String DEFAULT_PRODUCTS_ON_PAGE = "defaultProductsOnPage";
    public static final String MANUFACTURES = "manufactures";
    public static final String CATEGORIES = "categories";
    public static final String BY_PRICE_FROM_LOW = "byPriceFromLow";
    public static final String BY_PRICE_FROM_HIGH = "byPriceFromHigh";
    public static final String BY_ALPHABETICAL_FROM_A_Z = "byAlphabeticalFromA-Z";
    public static final String BY_ALPHABETICAL_FROM_Z_A = "byAlphabeticalFromZ-A";

    //Cart constants
    public static final String CART = "cart";
    public static final String CART_SIZE = "cartSize";
    public static final String PRODUCT_ID = "productId";
    public static final String SUCCESS = "success";
    public static final String AMOUNT = "amount";
    public static final String CART_PRICE = "cartPrice";

    //Order constants
    public static final String ORDERED_PRODUCTS = "ordered products";
    public static final String PAYMENT_METHOD = "paymentMethod";
    public static final String DELIVERY_METHOD = "deliveryMethod";
    public static final String CARD_NUMBER = "cardNumber";
    public static final String DATA_OF_CARD = "dataOfCard";
    public static final String CVC = "CVC";
    public static final String CARD = "card";
    public static final String CARD_NUMBER_REGEX = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$";
    public static final String CARD_DATE_REGEX = "^\\d{2}/\\d{2}$";
    public static final String CVC_REGEX = "^\\d{3}$";

    //Locale constants
    public static final String LOCALE_KEEPERS = "localeKeepers";
    public static final String LANG = "lang";
    public static final String LOCALES = "locales";
    public static final String LOCALIZATION = "localization";
    public static final String LOCALE_SAVE_TIME = "localeSaveTime";
    public static final String APPLICATION_LOCALE = "applicationLocale";
    public static final String APP_LOCALE = "appLocale";

    //Cache constants
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String NO_CACHE = "no-store, no-cache, must-revalidate, max-age=0";

    //GZip constants
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String GZIP = "gzip";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
}
