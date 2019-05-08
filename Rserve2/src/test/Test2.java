package test;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

public class Test2 {
	public static void main(String[] args) throws Exception {
		new Test2().getAPI();
	}
	public byte[] getAPI() throws REngineException, REXPMismatchException {
		RConnection r = null;
		r = new RConnection();
		REXP x = null;
		
		r.parseAndEval("total <- read.csv('seoul.csv', head=T)");
		r.parseAndEval("row.names(total) <- total$date");
		r.parseAndEval("total <- total[,2:7]");
		r.parseAndEval("stars(total, flip.labels=FALSE, draw.segment=FALSE, frame.plot=TRUE, full=TRUE, main='seoul-dust-analyze')");
		r.parseAndEval("savePlot('ggplot.png', type='png')");
		r.parseAndEval("graphics.off()");
		x = r.parseAndEval("readBin('ggplot.png', 'raw', 1024*1024)");
		r.parseAndEval("unlink('ggplot.png')");
		byte[] arr = x.asBytes();
		
		r.close();
		return arr;
	}
}
