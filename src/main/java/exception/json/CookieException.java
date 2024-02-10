package exception.json;

public class CookieException extends Exception{
    CookieException(){
        super("The cookie is risk controlled or expires");
    }

    CookieException(String mesage){
        super(mesage + "The cookie is risk controlled or expires");
    }

}
