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
@WebServlet("/my2")
public class MyServlet2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Test2 test = new Test2();
		resp.setContentType("image/png");
		try {
			resp.getOutputStream().write(test.getAPI());
		} catch (REngineException | REXPMismatchException e) {
			e.printStackTrace();
		}
	}
}
