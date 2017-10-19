package doodle.doodle;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
/*
* @RestContoller
* This annotation eliminates the need of annotating each method with @ResponseBody.
* Under the hood, @RestController is itself annotated with @ResponseBody, and can be
* considered as combination of @Controller and @ResponseBody.
* */

@RestController
public class DoodleController {
    //counts the opened doodles
    private int id = 0;
    private String name = "";
    //keeps track of the currently open doodles as couples <id, Doodle>
    private HashMap<Integer, Doodle> doodles = new HashMap<>();
    /* @RequestMapping
    * Annotation for mapping web requests onto specific handler classes and/or handler methods.
    * */
    @RequestMapping(value = "/doodles", method = RequestMethod.PUT)
    public long createDoodle(@RequestBody Doodle d) {
        doodles.put(id, new Doodle(d));
        id++;
        return id - 1;
    }

    @RequestMapping(value = "/doodles", method = RequestMethod.GET)
    public HashMap<Integer, Doodle> getDoodles() {
        return doodles;
    }

    @RequestMapping(value="/doodles/{id}", method = RequestMethod.DELETE)
    public void deleteDoodle(@PathVariable("id") int id){
        doodles.remove(id);
    }
    @RequestMapping(value="/doodles/{id}", method = RequestMethod.GET)
    public Doodle getDoodle(@PathVariable("id") int id){
        return doodles.get(id);
    }

    /*
    * @RequestBody : If a method parameter is annotated with @RequestBody, Spring will bind
    * the incoming HTTP request body to that parameter. While doing that, Spring will
    * [behind the scenes] use HTTP Message converters to convert the HTTP request body into
    * domain object [deserialize request body to domain object], based on ACCEPT or Content-Type
    * header present in request.
    * */
    @RequestMapping(value="/doodles/{id}/vote", method = RequestMethod.PUT)
    public String vote(
            @PathVariable("id") int id,
            @RequestBody Vote v){
        return doodles.get(id).addVote(v);
    }

    /*
    * @PathVariable This annotation indicates that a method parameter should be bound to a URI
    * template variable [the one in '{}']
    * */
    @RequestMapping(value="/doodles/{id}/vote/{name}", method = RequestMethod.GET)
    public String vote(@PathVariable("id") int id, @PathVariable("name") String name){

                return doodles.get(id).findPreviousVote(name);
    }

    @RequestMapping(value="/doodles/{id}/vote/{name}", method = RequestMethod.DELETE)
    public void deleteName(@PathVariable("id") int id, @PathVariable("name") String name){
        doodles.get(id).removeVote(name);
    }

    /*updateVote posts an update as a new JSON request and returns the name of the voter*/
    @RequestMapping(value="/doodles/{id}/vote/{name}", method = RequestMethod.POST)
    public String updateVote(@PathVariable("id") int id, @PathVariable("name") String name,
                             @RequestBody Vote vote){
          doodles.get(id).addVote(vote);
          return vote.getName();
    }
}
