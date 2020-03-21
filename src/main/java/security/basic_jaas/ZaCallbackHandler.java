package security.basic_jaas;

import javax.security.auth.callback.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZaCallbackHandler implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        // use callback to get the authentication back
        for(Callback callback : callbacks){
            if(callback instanceof NameCallback){
                NameCallback nameCallback = (NameCallback) callback;
                System.out.println(nameCallback.getPrompt());
                nameCallback.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
            }else if(callback instanceof PasswordCallback){
                PasswordCallback passwordCallback = (PasswordCallback) callback;
                System.out.println(passwordCallback.getPrompt());
                passwordCallback.setPassword(new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray());
            }
        }
    }
}
