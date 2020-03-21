package security.basic_jaas;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

public class ZaLoginModule implements LoginModule {
    public static final String TEST_USERNAME = "zauser";
    public static final String TEST_PASSWORD = "password";
    private CallbackHandler callbackHandler = null;
    private boolean authenticationSuccessFlag = false;


    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        //Call the callbackHandler
        Callback[] callbackArray = new Callback[2];
        callbackArray[0] = new NameCallback("User name:");
        callbackArray[1] = new PasswordCallback("Password:", false);

        try {
            callbackHandler.handle(callbackArray);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }

        String name = ((NameCallback)callbackArray[0]).getName();
        String password = new String(((PasswordCallback)callbackArray[1]).getPassword());

        // login logic
        if(TEST_USERNAME.equals(name) && TEST_PASSWORD.equals(password)){
            System.out.println("authentication success");
            authenticationSuccessFlag = true;
        }else{
            authenticationSuccessFlag = false;
            throw new FailedLoginException("authentication failure ...");
        }

        return authenticationSuccessFlag;
    }

    @Override
    public boolean commit() throws LoginException {
        return authenticationSuccessFlag;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
