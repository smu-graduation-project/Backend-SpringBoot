package com.graduatioinProject.sensorMonitoring.productData.node.dto;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;


/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NodePutRequest {
    private Long port;
    private String name;
    private String type;
    private String information;

    private Battery battery;

    @Nullable
    private String imageUrl;

    /**
     * NodePutRequest to Node
     * @return
     */
    public Node toEntity() {
        Node node = Node.builder()
                .port(this.port)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .battery(this.battery)
                .imageUrl(this.imageUrl)
                .build();

        return node;
    }
}
