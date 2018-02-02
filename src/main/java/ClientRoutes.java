import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;
import repository.RepoImpl;
import site.BaseRoutes;
import spark.ModelAndView;
import utils.Formula;
import utils.Utils;
import utils.VelocityTemplateEngine;

import java.util.ArrayList;
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
        get(ROOT, (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "/public/index.html");
        }, new VelocityTemplateEngine());


        get(ROOT + "index", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "/public/index.html");
        }, new VelocityTemplateEngine());


        get(ROOT + "archive", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            ArrayList<Report> report = repo.getAllObjects(Report.class);
            model.put("Report", report);
            return new ModelAndView(model, "/public/archive.html");
        }, new VelocityTemplateEngine());

        get(ROOT + "report/:id", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            Long id = Long.valueOf(request.params(":id"));
            Report report = (Report) repo.getObject(Report.class, id);
            ArrayList<Result> results = repo.getResByReport(id);

            model.put("Report", report);
            model.put("Result", results);

            return new ModelAndView(model, "/public/report.html");
        }, new VelocityTemplateEngine());

        post(ROOT + "getinfo", (request, response) -> {
            System.out.println(request.headers());
            System.out.println(request.body());
            HashMap<String, Object> model = new HashMap<>();

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            UserInfo userInfo = gson.fromJson(request.body(), UserInfo.class);

            System.out.println(userInfo.toString());

            Utils utils = new Utils();
            Long id = utils.convertAndSave(userInfo);

            Formula.calculate(id);
            return new ModelAndView(model, "/public/index.html");
        }, new VelocityTemplateEngine());
    }
}
