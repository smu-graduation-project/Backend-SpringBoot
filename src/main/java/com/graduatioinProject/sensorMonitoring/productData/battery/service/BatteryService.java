package com.graduatioinProject.sensorMonitoring.productData.battery.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.battery.repository.BatteryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/01
 */
@Service
public class BatteryService {
    private BatteryRepository batteryRepository;

    public Battery getBattery(Long id) {
        Optional<Battery> response = batteryRepository.findById(id);
        if (response.isPresent()) {
            return response.get();
        }
        throw (new BussinessException(ExMessage.BATTERY_ERROR_NOT_FOUND.getMessage()));
    }

    public Battery setBattery(Battery battery) {
        return batteryRepository.save(battery);
    }

    public void uploadImage(MultipartFile image, Long id) {
        try {
            String imagePath = "resources/static/upload/image/battery/";
            String imageName = image.getOriginalFilename() + "-" + UUID.randomUUID();
            String imageUrl = imagePath + imageName;
            image.transferTo(new File(imageUrl));

            Battery battery = getBattery(id);
            battery.setImageName(imageName);
            battery.setImageUrl(imageUrl);
            battery.setImage_size(image.getSize());
            batteryRepository.save(battery);

        } catch (Exception e) {
            throw new BussinessException(ExMessage.IMAGE_NOT_UPLOADED.getMessage());
        }
    }
}
