package com.graduatioinProject.sensorMonitoring.formerData.electricCurrent;

import com.graduatioinProject.sensorMonitoring.formerData.FormerData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class ElectricCurrent extends FormerData {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
}