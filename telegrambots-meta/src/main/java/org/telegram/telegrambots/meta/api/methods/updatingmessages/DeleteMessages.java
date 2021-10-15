package org.telegram.telegrambots.meta.api.methods.updatingmessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.ApiResponse;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.io.IOException;

/**
 * @author Kurenai
 * @version 1.0
 *
 * Delete all the messages with message_id in range between start and end.
 * The start parameter MUST be less than the end parameter
 * Both start and end must be positive non zero numbers
 * The method will always return true as a result, even if the messages cannot be deleted
 * This method does not work on private chat or normal groups It is not suggested to delete more than 200 messages per call
 *
 * NOTE
 * The maximum number of messages to be deleted in a single batch is determined by the max-batch-operations parameter and is 10000 by default
 */
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteMessages extends BotApiMethod<Boolean> {
    public static final String PATH = "deleteMessages";

    private static final String CHATID_FIELD = "chat_id";
    private static final String START_FIELD = "start";
    private static final String END_FIELD = "end";

    /**
     * Unique identifier for the chat to send the message to (Or username for channels)
     */
    @JsonProperty(CHATID_FIELD)
    @NonNull
    private String chatId;
    /**
     * First message id to delete
     */
    @JsonProperty(START_FIELD)
    @NonNull
    private Integer start;
    /**
     * Last message id to delete
     */
    @JsonProperty(END_FIELD)
    @NonNull
    private Integer end;

    @Override
    public String getMethod() {
        return PATH;
    }

    @Override
    public Boolean deserializeResponse(String answer) throws TelegramApiRequestException {
        try {
            ApiResponse<Boolean> result = OBJECT_MAPPER.readValue(answer,
                    new TypeReference<ApiResponse<Boolean>>() {
                    });
            if (result.getOk()) {
                return result.getResult();
            } else {
                throw new TelegramApiRequestException("Error deleting messages", result);
            }
        } catch (IOException e) {
            throw new TelegramApiRequestException("Unable to deserialize response", e);
        }
    }

    @Override
    public void validate() throws TelegramApiValidationException {
        if (chatId.isEmpty()) {
            throw new TelegramApiValidationException("ChatId parameter can't be empty", this);
        }
    }
}
