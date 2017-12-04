import site.BaseRoutes;
import spark.ModelAndView;
import utils.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.logging.Logger;

import static spark.Spark.get;

public class ClientRoutes extends BaseRoutes {
    private static Logger log = Logger.getLogger(ClientRoutes.class.getName());
    private final String ROOT = "/";

    @Override
    public void routes() {
        get(ROOT + "index", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "/public/index.html");
        }, new VelocityTemplateEngine());


        get(ROOT + "archive", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "/public/archive.html");
        }, new VelocityTemplateEngine());

        get(ROOT + "report", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "/public/report.html");
        }, new VelocityTemplateEngine());
    }
}
