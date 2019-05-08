package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

public class Test {
	
	public byte[] getCrawl() throws REngineException, REXPMismatchException {
		RConnection r = null;
		REXP x = null;
		r = new RConnection();
		
		List<String> champname = new ArrayList<>();
		for(int i = 1; i < 5; i++) {
			String url = "http://lol.inven.co.kr/dataninfo/rate/index.php?page=" + i;
			Document doc = null;
			
			try { 
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Elements elements = doc.select("span.champname a");
			champname.add(elements.text());
		}

		r.parseAndEval("library(wordcloud)");
		r.parseAndEval("library(RColorBrewer)");
		r.parseAndEval("library(stringr)");
		r.parseAndEval("palete <- brewer.pal(7, 'Set3')");
		r.parseAndEval("data <- '" + champname + "'");
		r.parseAndEval("data2 <- str_split(data, ' ')");
		r.parseAndEval("data3 <- unlist(data2)");
		r.parseAndEval("wordcount <- table(data3)");
		r.parseAndEval("head(sort(wordcount, decreasing=T), 30)");
		r.parseAndEval("palete <- brewer.pal(9, 'Set1')");
		r.parseAndEval("wordcloud(names(wordcount), freq=wordcount, scale=c(5,1), rot.per=0.25, min.freq=2, random.order=F, random.color=T, colors=palete)");
		r.parseAndEval("legend(0.3, 1, 'champion analyze', cex=0.8, fill=NA, border=NA, bg='white', text.col='red', text.font=2, box.col='red')");
		r.parseAndEval("savePlot('wordcloud.png', type='png')");
		r.parseAndEval("graphics.off()");
		x = r.parseAndEval("readBin('wordcloud.png', 'raw', 1024*1024)");
		r.parseAndEval("unlink('wordcloud.png')");
		byte[] arr = x.asBytes();
		
		r.close();
		return arr;
	}
}
