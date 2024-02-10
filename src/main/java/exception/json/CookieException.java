package exception.json;

public class CookieException extends Exception{
    public CookieException(){
        super("The cookie is risk controlled or expires");
    }

    public CookieException(Object mesage){
        super(mesage + "The cookie is risk controlled or expires");
    }

}
