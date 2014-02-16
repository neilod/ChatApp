package ie.neilod.chat;

import ie.neilod.chat.endpoints.ChatEndpoint;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class EndpointsConfig implements ServerApplicationConfig {

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {
        Set<ServerEndpointConfig> result = new HashSet<ServerEndpointConfig>();
        if (scanned.contains(ChatEndpoint.class)) {
            result.add(ServerEndpointConfig.Builder.create(
                    ChatEndpoint.class,
                    "/chat").build());
        }
        return result;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        return Collections.emptySet();
    }
}