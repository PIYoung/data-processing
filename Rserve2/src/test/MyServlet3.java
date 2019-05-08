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
@WebServlet("/my3")
public class MyServlet3 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Test3 test = new Test3();
		resp.setContentType("image/png");
		try {
			resp.getOutputStream().write(test.getRelation());
		} catch (REngineException | REXPMismatchException e) {
			e.printStackTrace();
		}
	}
}
