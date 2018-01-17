import static spark.Spark.*;


public class Application {
    public static void main(String[] args) {
        staticFileLocation("/public");

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 5555;
        }

        port(port);

//        Integer port = 5555;
//
//        port(port);

        new ClientRoutes();
    }
}
