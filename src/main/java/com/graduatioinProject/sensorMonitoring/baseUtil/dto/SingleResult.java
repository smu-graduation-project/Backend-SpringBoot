package com.graduatioinProject.sensorMonitoring.baseUtil.dto;

import lombok.Data;
import lombok.Setter;

@Setter
public class SingleResult<T> extends CommonResult {
	T data;
}
