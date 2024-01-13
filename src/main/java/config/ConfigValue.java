package config;

public class ConfigValue<A,B>{
    public final A access_token;
    public final B cookie;

    protected ConfigValue(A access_token,B cookie){
        this.access_token = access_token;
        this.cookie = cookie;
    }
}
