import model.Report;
import model.Result;
import model.Substance;
import repository.RepoImpl;
import site.BaseRoutes;
import spark.ModelAndView;
import utils.Formula;
import utils.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.logging.Logger;

import static spark.Spark.get;
import static spark.Spark.post;


public class ClientRoutes extends BaseRoutes {
    private static Logger log = Logger.getLogger(ClientRoutes.class.getName());
    private final String ROOT = "/";

    RepoImpl repo = new RepoImpl();

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

        post(ROOT + "getinfo", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            String org = request.queryParams("org");
            String city = request.queryParams("city");
            String district = request.queryParams("district");
            String street = request.queryParams("street");
            String house = request.queryParams("house");
            String category = request.queryParams("category");
            String[] cat = category.split("-");
            String drug = request.queryParams("drug");
            String unit = request.queryParams("unit");
            String concentration = request.queryParams("concentration");
            String date = request.queryParams("date");

            Report report = new Report(org, city, district, street, house, Integer.parseInt(cat[1]), date);
            repo.addObject(report);

            Substance substance = new Substance(drug, unit, Double.valueOf(concentration), report);
            repo.addObject(substance);

            Result result = new Result("new", 0.32323, true, report);
            repo.addObject(result);

            log.info( org+ city+ district+ street+ house+ category+ date + drug + unit + concentration);

            Formula.calculate(report.getId());
            return new ModelAndView(model, "/public/index.html");
        }, new VelocityTemplateEngine());
    }
}
