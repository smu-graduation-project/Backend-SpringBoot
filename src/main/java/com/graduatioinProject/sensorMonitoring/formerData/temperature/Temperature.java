package com.graduatioinProject.sensorMonitoring.formerData.temperature;


import com.graduatioinProject.sensorMonitoring.formerData.FormerData;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Temperature extends FormerData {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
}