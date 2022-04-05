package com.graduatioinProject.sensorMonitoring.baseUtil.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResponse;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

	public CommonResult successResult() {
		return setSuccess(new CommonResult());
	}

	public CommonResult failResult(String message) {
		CommonResult failResult = new CommonResult();
		failResult.setCode(CommonResponse.FAIL.getCode());
		failResult.setMessage(message);
		failResult.setSuccess(false);
		return failResult;
	}

	public <T> SingleResult<T> singleResult(T data) {
		SingleResult<T> single = new SingleResult<>();
		single.setData(data);
		setSuccess(single);
		return single;
	}

	public <T> ListResult<T> listResult(List<T> data) {
		ListResult<T> list = new ListResult<>();
		setSuccess(list);
		list.setData(data);
		return list;
	}

	private CommonResult setSuccess(CommonResult result) {
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMessage(CommonResponse.SUCCESS.getMessage());
		return result;
	}
}
