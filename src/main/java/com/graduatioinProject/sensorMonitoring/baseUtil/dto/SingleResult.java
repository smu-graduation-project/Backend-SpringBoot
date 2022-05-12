package com.graduatioinProject.sensorMonitoring.baseUtil.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SingleResult<T> extends CommonResult {
	T data;
}
