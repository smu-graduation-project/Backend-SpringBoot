package com.graduatioinProject.sensorMonitoring.formerData.temperature.entity;


import com.graduatioinProject.sensorMonitoring.formerData.FormerData;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Temperature extends FormerData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}