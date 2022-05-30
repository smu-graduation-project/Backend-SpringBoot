package com.graduatioinProject.sensorMonitoring.baseUtil.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResult<T> extends CommonResult {
	List<T> data;
}
