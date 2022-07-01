package com.graduatioinProject.sensorMonitoring.productData.battery.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.battery.repository.BatteryRepository;
import com.graduatioinProject.sensorMonitoring.productData.battery.repository.BatteryRepositoryCustom;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/01
 */
@Service
public class BatteryService {
    private BatteryRepository batteryRepository;
    private BatteryRepositoryCustom batteryRepositoryCustom;

    public List<Battery> findAll() {
        return batteryRepository.findAll();
    }

    public Battery findById(Long id) {
        Optional<Battery> response = batteryRepository.findById(id);
        if (response.isPresent()) {
            return response.get();
        }
        throw (new BussinessException(ExMessage.BATTERY_ERROR_NOT_FOUND.getMessage()));
    }

    public Battery findByIdCustom(Long id) {
        return batteryRepositoryCustom.findByIdWithNode(id);
    }

    public void setBattery(Battery battery) {
        try {
            batteryRepository.save(battery);
        } catch (Exception e) {
            throw new BussinessException(e.getMessage());
        }
    }

    public void uploadImage(MultipartFile image, Long id) {
        try {
            String imagePath = "resources/static/upload/image/battery/";
            String imageName = image.getOriginalFilename() + "-" + UUID.randomUUID();
            String imageUrl = imagePath + imageName;
            image.transferTo(new File(imageUrl));

            Battery battery = findById(id);
            battery.setImageName(imageName);
            battery.setImageUrl(imageUrl);
            battery.setImage_size(image.getSize());
            batteryRepository.save(battery);

        } catch (Exception e) {
            throw new BussinessException(ExMessage.IMAGE_NOT_UPLOADED.getMessage());
        }
    }


    public Boolean chekMemberAuthorityUser(Long memberId, Long batteryId) {
        Site site = batteryRepositoryCustom.findByIdWithMember(batteryId).getSite();

//        Member member = memberService.findById(memberId);
//        return member.getSites().contains(site);

        // 임시
        return true;
    }
//    public Boolean checkMemberRole(Member member, Long batteryId) {
//        Set<Member> members = batteryRepositoryCustom.findByIdMemberRole(batteryId).getSite().getMembers();
//        return members.contains(member);
//    }
}
