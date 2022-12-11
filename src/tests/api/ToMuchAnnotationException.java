package tests.api;

/*
 *
 * @author Roman Netesa
 *
 */
public class ToMuchAnnotationException extends Exception{
    public ToMuchAnnotationException(String annoName){
        super("To much annotations! Annotation name:" + annoName);
    }
}
