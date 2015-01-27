package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.RunLog;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by yulinguo on 1/22/15.
 */
public class RunLogs extends Controller {

    public static Result create(){

        JsonNode js = request().body().asJson();
        RunLog.create(js);
        //return ok anyway
        return ok();
    }
}
