package security.basic_jaas;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class Driver {
    public static void main(String[] args) throws LoginException {
        System.setProperty("java.security.auth.login.config", "/Users/xutao/project/jvm_study/out/production/classes/jaastutorial.config");

        LoginContext loginContext = new LoginContext("ZaJaasTutorial", new ZaCallbackHandler());
        loginContext.login();
    }
}
