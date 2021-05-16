package com.project.manager.demo.security;

import org.springframework.http.HttpHeaders;

public class SecurityConstant {

    public class SecurityConstants {
        public static final String SECRET = "SecretKeyToGenJWTs";
        public static final long EXPIRATION_TIME = 864_000_000; // 10 days
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String HEADER_STRING = HttpHeaders.AUTHORIZATION;

        /*
         * https://docs.microsoft.com/pl-pl/azure/architecture/best-practices/api-design
         * https://adventure-works.com/orders // Good
         * https://adventure-works.com/create-order // Avoid
         */
        public static final String SIGN_UP_URL = "/users/sign-up";
    }
}
