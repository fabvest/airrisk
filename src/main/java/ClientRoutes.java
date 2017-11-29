import site.BaseRoutes;
import spark.ModelAndView;
import utils.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;

public class ClientRoutes extends BaseRoutes {
    private final String ROOT = "/";

    @Override
    public void routes() {
        get(ROOT + "index", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "/public/index.html");
        }, new VelocityTemplateEngine());
    }
}
