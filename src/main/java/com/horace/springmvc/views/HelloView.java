package com.horace.springmvc.views;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Component
public class HelloView implements View {
    /**
     * Return the content type of the view, if predetermined.
     * <p>Can be used to check the view's content type upfront,
     * i.e. before an actual rendering attempt.
     *
     * @return the content type String (optionally including a character set),
     * or {@code null} if not predetermined
     */
    @Override
    public String getContentType() {
        return "text/html";
    }

    /**
     * Render the view given the specified model.
     * <p>The first step will be preparing the request: In the JSP case, this would mean
     * setting model objects as request attributes. The second step will be the actual
     * rendering of the view, for example including the JSP via a RequestDispatcher.
     *
     * @param model    Map with name Strings as keys and corresponding model
     *                 objects as values (Map can also be {@code null} in case of empty model)
     * @param request  current HTTP request
     * @param response HTTP response we are building
     * @throws Exception if rendering failed
     */
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().println("hello view ,time :"+ new Date());
    }
}
