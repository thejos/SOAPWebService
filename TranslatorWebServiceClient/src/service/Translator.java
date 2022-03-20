package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 */
@WebService
public interface Translator {

    /**
     * Translates text (a term) from one language into another.
     *
     * @param term word being translated.
     * @param nativeLanguage the language being translated from.
     * @param targetLanguage the language being translated into.
     * @return
     */
    @WebMethod
    String translate(String term, String nativeLanguage, String targetLanguage);

}
