package security.advanced_jaas;

import security.basic_jaas.ZaCallbackHandler;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PrivilegedAction;

// Subject: an authenticated user executing the application
// Principal: identities of the subject

public class Driver {
    public enum Action {action1, action2, logout};

    public static void main(String[] args) throws LoginException {
        System.setProperty("java.security.auth.login.config", "/Users/xutao/project/jvm_study/out/production/classes/jaastutorial.config");

        Driver driver = new Driver();

        while(true){
            try {
                LoginContext loginContext = new LoginContext("ZaJaasTutorialAdvanced", new ZaCallbackHandler());
                loginContext.login();

                boolean flag = true;
                while(flag) flag = driver.performAction(loginContext);
            }catch(LoginException | IOException e ){
                System.out.println(e.getMessage());
            }
        }

    }

    boolean performAction(LoginContext loginContext) throws IOException, LoginException {
        boolean flag = true;
        System.out.println("Please specify action to take (usage: action1, action2, logout)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        switch (Action.valueOf(br.readLine())){
            case logout:
                loginContext.logout();
                System.out.println("you are logged out");
                flag = false;
                break;
            case action1:
                Subject.doAs(loginContext.getSubject(), new PrivilegedAction<Object>() {
                    public Object run() {
                        System.out.println("action1 was performed");
                        return null;
                    }
                });
                break;
            case action2:
                Subject.doAs(loginContext.getSubject(), new PrivilegedAction<Object>() {
                    public Object run() {
                        System.out.println("action2 was performed");
                        return null;
                    }
                });
                break;
        }

        return flag;
    }
}
