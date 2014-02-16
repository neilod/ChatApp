package ie.neilod.chat.endpoints;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ie.neilod.chat.services.ChatRegistryService;

import javax.websocket.*;

public class ChatEndpoint extends Endpoint {
    private static ChatRegistryService pusher;
    private static ObjectMapper objectMapper;

    static {
        pusher = new ChatRegistryService();
        objectMapper = new ObjectMapper();
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        session.addMessageHandler(new ChatMessageHandler(session));
        pusher.addSession(session);
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        pusher.removeSession(session);
        super.onClose(session, closeReason);
    }

    private static class ChatMessageHandler implements MessageHandler.Whole<String> {

        private final Session session;

        private ChatMessageHandler(Session session) {
            this.session = session;
        }

        @Override
        public void onMessage(String message) {
            try {
                if (session.getBasicRemote() != null) {
                    JsonNode jsonMessage = objectMapper.readTree(message);
                    String messageType = jsonMessage.path("type").asText();
                    if(messageType.equals("message")) {
                        handleMessage(jsonMessage);
                    } else if(messageType.equals("user")) {
                        handleNewUser(jsonMessage);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void handleNewUser(JsonNode jsonMessage) {
            session.getUserProperties().put("name", jsonMessage.path("data").asText());
            pusher.alertAll("User " + session.getUserProperties().get("name") + " joined");
        }

        private void handleMessage(JsonNode jsonMessage) {
            pusher.alertAll(session.getUserProperties().get("name") + ": " + jsonMessage.path("data").asText());
        }
    }
}