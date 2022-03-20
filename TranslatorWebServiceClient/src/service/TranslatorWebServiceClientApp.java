package service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * This is client application, consumer of the 'SOAPwsTranslator' a SOAP web
 * service.<br>
 * A web services client is an application capable of sending and receiving SOAP
 * messages.
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class TranslatorWebServiceClientApp {

    private static URL wsdlUrl;

    public static void main(String[] args) {

        try {
            wsdlUrl = new URL("http://localhost:8080/SOAPwsTranslator/TranslatorImplementationService?wsdl");
        } catch (MalformedURLException mfurlExc) {
            System.out.println(mfurlExc.getMessage());
        }

        QName qName = new QName("http://service/", "TranslatorImplementationService");

        Service service = Service.create(wsdlUrl, qName);

        QName port = new QName("http://service/", "TranslatorImplementationPort");

        Translator translator = service.getPort(port, Translator.class);

        //see Javadoc comments on service.Translator.translate 
        /*see \src\java\service\documents\srb_eng_esp_dictionary.xml in 'SOAPwsTranslator' project
        for words and languages that can be used as parameters*/
        System.out.println(translator.translate("drvo", "serbian", "spanish"));

    }//main() END

}
