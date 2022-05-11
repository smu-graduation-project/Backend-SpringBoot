package com.graduatioinProject.sensorMonitoring.formerData.temperature.entity;


import com.graduatioinProject.sensorMonitoring.formerData.FormerData;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Builder
public class Temperature extends FormerData {

    private void test() {
        for (int i = 0; i < 390; i++) {
            System.out.println();
            System.out.println("i = " + i);
            System.out.println("Temperature.test");
        }
    }
}