/**
 * Authors: David Allen Stephan Marino
 * Date: 6/1/25
 */

package davidmarino.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
public class Record {
    private String recordId;
    private String userId;
    private long startTime;
    private long endTime;
    private long duration;
    private String datetime;

    @JsonCreator
    public Record(@JsonProperty("record_id") String recordId, @JsonProperty("user_id") String userId, @JsonProperty("start_time") long startTime, @JsonProperty("end_time") long endTime, @JsonProperty("duration") long duration, @JsonProperty("datetime") String datetime) {
        this.recordId = recordId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.datetime = datetime;
    }

    public Record(long startTime, long endTime, String name) {
        this.recordId = UUID.randomUUID().toString();
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = endTime - startTime;
        this.datetime = Instant.ofEpochMilli(System.currentTimeMillis())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.userId = name;
    }
    public boolean equals(String id) {
        return this.recordId.equals(id);
    }

//    public Record find(String id) {
//        if (this.recordId.equals(id)) return this;
//        return null;
//    }

//    public boolean lessThan(long duration) {
//        return this.duration < duration;
//    }

}
