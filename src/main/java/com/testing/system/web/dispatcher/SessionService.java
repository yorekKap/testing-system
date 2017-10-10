package com.testing.system.web.dispatcher;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Enumeration;
import java.util.function.Predicate;

/**
 * Created by yuri on 08.10.17.
 */
public class SessionService {
    HttpSession session;

    public SessionService(HttpSession session) {
        this.session = session;
    }

    public void cleanSessionScope() {
        Enumeration<String> attributes = session.getAttributeNames();
        while (attributes.hasMoreElements()) {
            session.removeAttribute(attributes.nextElement());
        }
    }

    public <T> T findAttribute(String attributeName, Class<T> clazz) {
        return clazz.cast(session.getAttribute(attributeName));
    }

    public <T> T findObjectInCollectionAttribute(String attributeName,
                                                 Class<T> objectClazz,
                                                 Predicate<T> predicate) {

        Object obj = Collection.class.cast(session.getAttribute(attributeName))
                .stream()
                .filter(predicate)
                .findFirst()
                .get();

        return objectClazz.cast(obj);
    }
}
