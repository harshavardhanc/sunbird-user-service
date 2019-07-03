package org.sunbird;

import akka.actor.ActorRef;
import io.opensaber.registry.app.OpenSaberApplication;
import org.springframework.context.ApplicationContext;
import org.sunbird.actor.core.ActorCache;
import org.sunbird.actor.core.ActorService;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is used to instantiate the actor system and open saber.
 * @author Amit Kumar
 */
public class Application {

    private static Application instance = new Application();
    public static ApplicationContext applicationContext;
    private static final String USER_ORG_ACTOR_SYSTEM="userOrgActorSystem";

    // private constructor restricted to this class itself
    private Application() {
    }

    // static method to create instance of ActorService class
    public static Application getInstance() {
        return instance;
    }

    // instantiate actor system and actors
    public void init() {
        List<String> actorClassPaths = new ArrayList<>();
        actorClassPaths.add("org.sunbird");
        ActorService.getInstance().init(USER_ORG_ACTOR_SYSTEM,actorClassPaths);
        OpenSaberApplication.main(new String[0]);
        applicationContext = OpenSaberApplication.getContext();
    }


    /**
     * this method is used to get the reference of actor from in memory cache.
     * @param operation
     * @return
     */
    public ActorRef getActorRef(String operation) {
        return ActorCache.getActorRef(operation);
    }
}
