import static spark.Spark.*;


public class Application {
    public static void main(String[] args) {
        staticFileLocation("/public");

        Integer port = 5555;

        port(port);
        get("/hello", (req, res) -> "Hello World");

        new ClientRoutes();
    }
}
