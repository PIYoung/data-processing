package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;

@SuppressWarnings("serial")
@WebServlet("/my1")
public class MyServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Test test = new Test();
		resp.setContentType("image/png");
		try {
			resp.getOutputStream().write(test.getCrawl());
		} catch (REngineException | REXPMismatchException e) {
			e.printStackTrace();
		}
	}
}
