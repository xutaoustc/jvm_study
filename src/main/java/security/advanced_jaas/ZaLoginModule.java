package security.advanced_jaas;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

public class ZaLoginModule implements LoginModule {
    public static final String[][] TEST_USERS = {{"zauser1","password1"},{"zauser2","password2"},{"zauser3","password3"}};
    private CallbackHandler callbackHandler = null;
    private Subject subject;
    private ZaPrinciPal zaPrinciPal;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbackArray = new Callback[2];
        callbackArray[0] = new NameCallback("User name:");
        callbackArray[1] = new PasswordCallback("Password:", false);

        try {
            callbackHandler.handle(callbackArray);
            String name = ((NameCallback)callbackArray[0]).getName();
            String password = new String(((PasswordCallback)callbackArray[1]).getPassword());

            for(String[] user: TEST_USERS){
                if(user[0].equals(name) && user[1].equals(password)){
                    zaPrinciPal = new ZaPrinciPal(name);
                    return true;
                }
            }

            throw new FailedLoginException("authentication failure ...");
        } catch (IOException | UnsupportedCallbackException  e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        if(subject != null && !subject.getPrincipals().contains(zaPrinciPal)){
            subject.getPrincipals().add(zaPrinciPal);
        }

        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        if(subject != null && zaPrinciPal != null && subject.getPrincipals().contains(zaPrinciPal))
            subject.getPrincipals().remove(zaPrinciPal);
        subject = null;
        zaPrinciPal = null;

        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(zaPrinciPal);
        subject = null;
        return true;
    }
}
