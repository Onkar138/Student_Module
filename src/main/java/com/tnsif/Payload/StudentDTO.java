package com.tnsif.Payload;

import lombok.Data;

@Data
public class StudentDTO {

    private long s_id;
    private String s_name;
    private long s_roll;
    private String s_qualification;
    private String course;
    private int s_year;
    private long s_hallTicketNo;

}
