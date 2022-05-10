package com.graduatioinProject.sensorMonitoring.formerData.voltage.entity;

import com.graduatioinProject.sensorMonitoring.formerData.FormerData;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voltage extends FormerData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
