package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * <code>Translator</code> interface is a service endpoint interface (SEI), a
 * Java interface class that defines the methods to be exposed as a Web service.
 * <p>
 * Web services provide a standard means of inter-operating between different
 * software applications, running on a variety of platforms or frameworks.
 * <p>
 * SOAP is the protocol used for communication between the web service and the
 * client application. SOAP uses the Hypertext Transfer Protocol (HTTP or HTTPS)
 * as the underlying protocol for transporting the data.
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 */
@WebService
public interface Translator {

    //abstract methods that are exposed to the client
    @WebMethod
    String translate(String term, String nativeLanguage, String targetLanguage);

}
