import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/change", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/change.vtl");
      ChangeMachine changeMachine = new ChangeMachine();
      //gets amount from form
      Float changeFloat = Float.parseFloat(request.queryParams("amount"));
      String changeString = changeMachine.makeChange(changeFloat);
      model.put("change-result", changeString);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
