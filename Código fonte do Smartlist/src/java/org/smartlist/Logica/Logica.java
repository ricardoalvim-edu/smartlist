package org.smartlist.Logica;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logica<T,Type extends Serializable> {
    String executa(HttpServletRequest req,
            HttpServletResponse res) throws Exception;
}
