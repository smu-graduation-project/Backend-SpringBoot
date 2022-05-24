package com.graduatioinProject.sensorMonitoring.productData.site.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/24
 */

@Data
@Builder
public class SitePageResponse {
    private long totalElements;
    private int totalPages;
    private int numberOfElements;
    private int size;
}
