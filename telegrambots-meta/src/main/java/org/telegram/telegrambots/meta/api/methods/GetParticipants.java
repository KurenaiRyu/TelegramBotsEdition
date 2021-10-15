package org.telegram.telegrambots.meta.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.*;
import org.telegram.telegrambots.meta.api.objects.ApiResponse;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kurenai
 * @version 1.0
 *
 * Get the member list of a supergroup or channel
 */
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class GetParticipants extends BotApiMethod<ArrayList<ChatMember>> {

    public static final String PATH = "getMessageInfo";

    public static final String CHATID_FIELD = "chat_id";
    public static final String FILTER_FIELD = "filter";
    private static final String OFFSET_FIELD = "offset";
    private static final String LIMIT_FIELD = "limit";

    @JsonProperty(CHATID_FIELD)
    @NonNull
    private String chatId; ///< Unique identifier for the chat to send the message to (Or username for channels)
    @JsonProperty(FILTER_FIELD)
    @NonNull
    private String filter; ///< Possible values are members, parameters, admins, administators, restricted, banned, bots
    @JsonProperty(OFFSET_FIELD)
    @NonNull
    private Integer offset; ///< Number of users to skip
    @JsonProperty(LIMIT_FIELD)
    @NonNull
    private Integer limit; ///< The maximum number of users be returned; up to 200

    @Override
    public String getMethod() {
        return PATH;
    }

    @Override
    public ArrayList<ChatMember> deserializeResponse(String answer) throws TelegramApiRequestException {
        try {
            ApiResponse<ArrayList<ChatMember>> result = OBJECT_MAPPER.readValue(answer,
                    new TypeReference<ApiResponse<ArrayList<ChatMember>>>() {
                    });
            if (result.getOk()) {
                return result.getResult();
            } else {
                throw new TelegramApiRequestException("Error getting participants", result);
            }
        } catch (IOException e2) {
            throw new TelegramApiRequestException("Unable to deserialize response", e2);
        }
    }

    @Override
    public void validate() throws TelegramApiValidationException {
    }
}
