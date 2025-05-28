package com.senai.ControleDeAcessoSpring.infrastructure.util;

import java.security.Principal;

public class UsernamePrincipal implements Principal {
        private final String name;

        public UsernamePrincipal(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
}
