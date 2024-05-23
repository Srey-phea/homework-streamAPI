package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private UUID id;
    private String title;
    private String[] instructorNames;
    private String[] requirements;
    private Date startDate;
}
