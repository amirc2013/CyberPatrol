import static spark.Spark.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Server {

	private static String readResource(String name) throws IOException{
		Path p1 = FileSystems.getDefault().getPath("resources",name);
		return Files.readAllLines(p1).stream().reduce((s1,s2)->{return s1 + "\n" + s2;}).orElse("");
	}
	
	private static void setupURL(String name, String suffix) throws IOException{
		String s = readResource(name+"."+suffix);
		get("/"+name, (req,res) -> s);
	}
	
	private static void setupHtml(String name) throws IOException{
		setupURL(name,"html");
	}
	
	private static void setupJavaScript(String name) throws IOException{
		String s = readResource(name+".js");
		get("/"+name+".js", (req,res) -> s);
	}
	
	public static void main(String[] args) throws IOException {
		setupHtml("list");
		setupHtml("scan");
		setupHtml("main");
		setupJavaScript("app");
	}
}
