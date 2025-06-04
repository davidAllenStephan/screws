/**
 * Authors: David Allen Stephan Marino
 * Date: 6/1/25
 */

package davidmarino.davidmarinostats;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class Record {
    private String id;
    private long startTime;
    private long endTime;
    private long duration;

    @JsonCreator
    public Record(@JsonProperty("id") String id, @JsonProperty("startTime") long startTime, @JsonProperty("endTime") long endTime, @JsonProperty("duration") long duration) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public Record(long startTime, long endTime, long duration) {
        this.id = UUID.randomUUID().toString();
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public Record(long startTime, long endTime) {
        this.id = UUID.randomUUID().toString();
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = endTime - startTime;
    }
    public boolean equals(String id) {
        return this.id.equals(id);
    }

    public Record find(String id) {
        if (this.id.equals(id)) return this;
        return null;
    }

    public boolean lessThan(long duration) {
        return this.duration < duration;
    }

}
