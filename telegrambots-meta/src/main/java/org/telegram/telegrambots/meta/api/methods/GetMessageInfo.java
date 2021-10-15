package org.telegram.telegrambots.meta.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.*;
import org.telegram.telegrambots.meta.api.objects.ApiResponse;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.io.IOException;

/**
 * @author Kurenai
 * @version 1.0
 *
 * Get information about a message
 */
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class GetMessageInfo extends BotApiMethod<Message> {

    public static final String PATH = "getMessageInfo";

    public static final String CHATID_FIELD = "chat_id";
    private static final String MESSAGEID_FIELD = "message_id";

    @JsonProperty(CHATID_FIELD)
    @NonNull
    private String chatId; ///< Unique identifier for the chat to send the message to (Or username for channels)
    @JsonProperty(MESSAGEID_FIELD)
    @NonNull
    private Integer messageId; ///< Message identifier in the chat specified in from_chat_id

    @Override
    public String getMethod() {
        return PATH;
    }

    @Override
    public Message deserializeResponse(String answer) throws TelegramApiRequestException {
        try {
            ApiResponse<Message> result = OBJECT_MAPPER.readValue(answer,
                    new TypeReference<ApiResponse<Message>>() {
                    });
            if (result.getOk()) {
                return result.getResult();
            } else {
                throw new TelegramApiRequestException("Error getting message information", result);
            }
        } catch (IOException e2) {
            throw new TelegramApiRequestException("Unable to deserialize response", e2);
        }
    }

    @Override
    public void validate() throws TelegramApiValidationException {
    }
}
