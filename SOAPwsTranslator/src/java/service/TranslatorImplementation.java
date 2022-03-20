package service;

import java.io.InputStream;
import javax.jws.WebService;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;

/**
 * The service implementation class (service implementation bean) implements the
 * service endpoint interface and handles the actual servicing of incoming SOAP
 * requests.
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
@WebService(endpointInterface = "service.Translator")
public class TranslatorImplementation implements Translator {

    private XPathExpression xPathExpression;
    private ClassLoader classLoader;
    private InputSource inputSource;
    private String xPathResult;

    //implement abstract methods
    @Override
    public String translate(String term, String nativeLanguage, String targetLanguage) {

        //instantiate XPathFactory
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        //XPath expression used to select nodes in an XML document
        String expression = "//word[" + nativeLanguage.trim().toLowerCase() + "='" + term.trim().toLowerCase() + "']/" + targetLanguage.trim().toLowerCase();

        //compile an XPath expression
        try {
            xPathExpression = xPath.compile(expression);
        } catch (XPathExpressionException xpeExc) {
            return "Expression cannot be compiled..." + xpeExc.getMessage();
        }

        //define an InputSource / a document to read and evaluate by using XPath path expression
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (SecurityException sExc) {
            return "Current thread cannot get the context ClassLoader..." + sExc.getMessage();
        }

        InputStream inputStream = classLoader.getResourceAsStream(".\\service\\documents\\srb_eng_esp_dictionary.xml");
        if (inputStream == null) {
            return "The resource cannot be found. Returned: " + inputStream;
        }

        inputSource = new InputSource(inputStream);

        //evaluate the compiled XPath expression over the specified InputSource
        //convert the result of evaluation to type String
        try {
            xPathResult = (String) xPathExpression.evaluate(inputSource, XPathConstants.STRING);
        } catch (XPathExpressionException xpeExc) {
            return "Expression cannot be evaluated..." + xpeExc.getMessage();
        } catch (NullPointerException npExc) {
            return "Source of returnType is null..." + npExc.getMessage();
        }

        if (xPathResult.equals("")) {
            return "Word not found!\n" + "word: " + term + "\nsource language: " + nativeLanguage.trim().toLowerCase() + "\ntarget language: " + targetLanguage.trim().toLowerCase();
        }

        return nativeLanguage.trim().toLowerCase() + ": " + term.trim().toLowerCase() + "\n" + targetLanguage.trim().toLowerCase() + ": " + xPathResult;

    }//translate() END

}
