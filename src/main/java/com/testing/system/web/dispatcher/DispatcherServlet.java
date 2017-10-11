package com.testing.system.web.dispatcher;

import com.testing.system.exceptions.dispatcher.BadRequestException;
import com.testing.system.exceptions.validation.ValidationException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * {@link HttpServlet} implementation for dispatching incoming
 * request to mapped controller
 *
 * @author yuri
 */
public class DispatcherServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(DispatcherServlet.class);

	private static final long serialVersionUID = 1L;

	RequestHelper helper;

    public DispatcherServlet() {
        helper = RequestHelper.getInstance();
   }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    													throws ServletException, IOException {
    	processRequest(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response){
		try {
			Controller controller =  helper.getController(request);
			String page = controller.execute(request, response);

			if(page != null){
				request.getRequestDispatcher(page).forward(request, response);
			}

		} catch (ServletException e) {
			log.error("ServletException in DispatcherServlet : ", e);
		} catch (IOException e) {
			log.error("IOException in DispatcherServlet : ", e);
		} catch(BadRequestException e){
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				log.warn("404 error code is returned");
			} catch (IOException e1) {
				log.error("IOException in DispatcherServlet : ", e);
			}
		} catch (ValidationException e){
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				log.warn("Validation failed");
			} catch (IOException e1) {
				log.error("IOException in DispatcherServlet : ", e);
			};
		}

	}

}
