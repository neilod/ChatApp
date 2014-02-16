package ie.neilod.chat.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.websocket.Session;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * User: neilod
 * Date: 09/01/2014
 * TIME: 18:38
 */
public class ChatRegistryService {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Set<Session> activeSessions;

    public ChatRegistryService() {
        activeSessions = new CopyOnWriteArraySet<Session>();
    }

    public void addSession(Session s) {
        activeSessions.add(s);
    }

    public void removeSession(Session s) {
        activeSessions.remove(s);
    }

    public void alertAll(String text) {
        for(Session s : activeSessions) {
            try {
                ObjectNode message = objectMapper.createObjectNode();
                message.put("type", "message");
                message.put("data", text);
                s.getAsyncRemote().sendText(message.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int count() {
        return activeSessions.size();
    }
}