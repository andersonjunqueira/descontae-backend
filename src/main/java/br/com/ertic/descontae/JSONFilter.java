package br.com.ertic.descontae;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import br.com.ertic.util.infraestructure.log.Log;

@Component
public class JSONFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)req;

        RequestWrapper wrapper = new RequestWrapper(request);

        byte[] payload = wrapper.getRequestBody();
        String queryString = request.getQueryString();

        StringBuilder msg = new StringBuilder()
            .append("\n\n[REQUEST] ")
            .append(wrapper.getMethod())
            .append(" PARA: ")
            .append(request.getRequestURL().toString())
            .append(queryString != null ? "?" + queryString : "")
            .append("\n")
            .append(new String(payload))
            .append("\n");

        Log.debug(JSONFilter.class,  msg.toString());

        chain.doFilter(wrapper, res);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}