package com.graduatioinProject.sensorMonitoring.formerData;;


import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@Data
public abstract class FormerData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Long port;

    private double max;
    private double min;
    private double average;

    public FormerDataResponse toResponse() {
        FormerDataResponse response = FormerDataResponse
                .builder()
                .date(this.getDate())
                .max(this.getMax())
                .min(this.getMin())
                .average(this.getAverage())
                .build();
        return response;
    }
}
