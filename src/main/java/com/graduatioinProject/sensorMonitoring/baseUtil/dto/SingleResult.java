package com.graduatioinProject.sensorMonitoring.baseUtil.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> extends CommonResult {
	T data;
}
