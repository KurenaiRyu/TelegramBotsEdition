package org.telegram.telegrambots.meta.api.objects.chatmember;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * @author Ruben Bermudez
 * @version 5.3
 *
 * Represents a chat member that was banned in the chat and can't return to the chat or view chat messages.
 */
@JsonDeserialize
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMemberBanned implements ChatMember {
    public static final String STATUS = "kicked";

    private static final String STATUS_FIELD = "status";
    private static final String USER_FIELD = "user";
    private static final String UNTILDATE_FIELD = "until_date";

    private static final String JOINEDDATE_FIELD = "joined_date";
    private static final String INVITER_FIELD = "inviter";

    /**
     * Unix timestamp, when has the user joined
     */
    @JsonProperty(JOINEDDATE_FIELD)
    private Integer joinedDate;
    /**
     * The inviter
     */
    @JsonProperty(INVITER_FIELD)
    private User inviter;

    /**
     * The member's status in the chat, always “kicked”
     */
    @JsonProperty(STATUS_FIELD)
    private final String status = STATUS;
    /**
     * Information about the user
     */
    @JsonProperty(USER_FIELD)
    private User user;
    /**
     * Date when restrictions will be lifted for this user; unix time
     */
    @JsonProperty(UNTILDATE_FIELD)
    private Integer untilDate;
}
