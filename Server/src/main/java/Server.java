import static spark.Spark.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import spark.Response;

public class Server {

	private static String readResource(String name) throws IOException{
		Path p1 = FileSystems.getDefault().getPath("resources",name);
		return Files.readAllLines(p1).stream().reduce((s1,s2)->{return s1 + "\n" + s2;}).orElse("");
	}
	
	private static void setupURL(String url, String filename) throws IOException{
		get("/"+url, (req,res) -> readResource(filename));
	}
	
	private static void setupHtml(String name) throws IOException{
		setupURL(name,name+".html");
	}
	
	private static void setupJavaScript(String name) throws IOException{
		setupURL(name+".js",name+".js");
	}
	
	public static void main(String[] args) throws IOException {
		setupHtml("list");
		setupHtml("scan");
		setupHtml("main");
		setupHtml("scanRes");
		setupJavaScript("app");
		setupJavaScript("scan");
	}
}
