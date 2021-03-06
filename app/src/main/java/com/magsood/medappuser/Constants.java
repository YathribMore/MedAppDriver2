package com.magsood.medappuser;

public class Constants {


    static String domain = "Https://www.maqsood.com.sd/api/v1/user/";
    static String domain2 = "http://api.dawakom.com.sd/api/v1/user/";

    public static final String RESET_PASSWORD = domain+"resetPassword";
    public static final String NEW_PASSWORD = domain+"newPassword";


    public static  String REGISTRATION_URL =domain+"registerUser";
    public static  String LOGIN_URL =domain+"login";
    public static  String PROFILE_URL =domain+"userProfile";
    public static  String LOGOUT_URL =domain+"logout";
    public static  String SEARCH_URL =domain+"search";
    public static  String SEND_REQUEST_URL =domain+"order";

    public static String GET_MEDICINE = domain+"medicines";
    public static String VerificationNumber = domain+"sendCode";
    public static String SendVerificationNumber = "http://api.dawakom.com.sd/api/v1/user/verifyAccount";
    public static String PREV_ORDERS = "http://api.dawakom.com.sd/api/v1/user/userPrevOrders";




    public static String SEARCH_DOCTOR_URL = "http://reservation.maqsood.com.sd/api/v1/user/search";


    public static String SEARCH_LAP_URL = "http://reservation.maqsood.com.sd/api/v1/user/searchLab";


    public static String RESERVITION_DOCTOR="http://reservation.maqsood.com.sd/api/v1/user/reservDoc";


    public static String RESERVITION_LAB="http://reservation.maqsood.com.sd/api/v1/user/reservLab";





}
