/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.profile;

import lombok.Data;

import java.util.Date;

@Data
public class Record {
    private String id; // unique uuid
    private String levelId;
    private long startTime;
    private long endTime;
    private long duration;
    private Date date;
}
