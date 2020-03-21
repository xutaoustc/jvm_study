package security.advanced_jaas;

import java.security.Principal;

public class ZaPrinciPal implements Principal {
    private final String name;

    public ZaPrinciPal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object){
        boolean flag = false;
        if(object instanceof ZaPrinciPal) flag = name.equals(((ZaPrinciPal)object).getName());
        return flag;
    }
}
