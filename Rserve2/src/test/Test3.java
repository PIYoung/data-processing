package test;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

public class Test3 {
	public byte[] getRelation() throws REngineException, REXPMismatchException {
		RConnection r = null;
		r = new RConnection();
		REXP x = null;
		
		r.parseAndEval("library(DBI)");
		r.parseAndEval("library(RJDBC)");
		r.parseAndEval("library(rJava)");
		r.parseAndEval("library(igraph)");
		r.parseAndEval("JDBC('oracle.jdbc.driver.OracleDriver', 'C:/oraclexe/app/oracle/product/11.2.0/server/jdbc/lib/ojdbc6.jar')");
		r.parseAndEval("conn <- dbConnect(driv, 'jdbc:oracle:thin:@localhost:1521:xe', 'hr', 'hr')");
		r.parseAndEval("df <- dbGetQuery(conn, 'SELECT EMPLOYEE_ID, MANAGER_ID FROM EMPLOYEES')");
		r.parseAndEval("emp <- df$EMPLOYEE_ID");
		r.parseAndEval("manager <- df$MANAGER_ID");
		r.parseAndEval("rel <- data.frame(emp=emp, manager=manager)");
		r.parseAndEval("g <- graph.data.frame(rel, directed=T)");
		r.parseAndEval("plot(g, layout=layout.kamada.kawai, vertex.size=13, edge.arrow.size=0.5)");
		r.parseAndEval("savePlot('igraph.png', type='png')");
		r.parseAndEval("graphics.off()");
		x = r.parseAndEval("readBin('igraph.png', 'raw', 1024*1024)");
		r.parseAndEval("unlink('igraph.png')");
		byte[] arr = x.asBytes();
		
		r.close();
		return arr;
	}
}
