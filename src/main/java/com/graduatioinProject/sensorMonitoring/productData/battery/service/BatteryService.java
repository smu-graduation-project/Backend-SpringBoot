package com.graduatioinProject.sensorMonitoring.productData.battery.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryRequest;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponseWithNode;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.battery.repository.BatteryRepository;
import com.graduatioinProject.sensorMonitoring.productData.battery.repository.BatteryRepositoryCustom;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/01
 */

@Slf4j
@Service
public class BatteryService {
    @Autowired
    private SiteService siteService;
    @Autowired
    private BatteryRepository batteryRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BatteryRepositoryCustom batteryRepositoryCustom;

    public List<Battery> findAll() {
        return batteryRepository.findAll();
    }

    public BatteryResponse findById(Long id) {
        Optional<Battery> response = batteryRepository.findById(id);
        if (response.isPresent()) {
            return response.get().toResponse();
        }
        throw (new BussinessException(ExMessage.BATTERY_ERROR_NOT_FOUND.getMessage()));
    }

    public BatteryResponseWithNode findByIdWithNode(Long id) {
        return batteryRepositoryCustom.findByIdWithNode(id);
    }

//    public Battery findByIdWithSite(Long id) {
//        return batteryRepositoryCustom.findByIdWithSite(id);
//    }
    @Transactional(rollbackFor = Exception.class)
    public void save(BatteryRequest batteryRequest, Long siteId) {
        Battery battery = batteryRequest.toEntity();
        battery.setSite(Site.builder().id(siteId).build());
        this.save(battery);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(BatteryRequest batteryRequest, Long siteId, Long batteryId) {
        Battery battery = batteryRequest.toEntity();
        battery.setId(batteryId);
        battery.setSite(Site.builder().id(siteId).build());
        this.save(battery);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addImage(String imgUrl, Long batteryId) {
        BatteryResponse batteryResponse = this.findById(batteryId);
        batteryResponse.setImageUrl(imgUrl);
        this.save(batteryResponse.toEntity());
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(Battery battery) {
        Battery newBattery = batteryRepository.save(battery);
    }
//    public void uploadImage(MultipartFile image, Long id) {
//        try {
//            String imagePath = "resources/static/upload/image/battery/";
//            String imageName = image.getOriginalFilename() + "-" + UUID.randomUUID();
//            String imageUrl = imagePath + imageName;
//            image.transferTo(new File(imageUrl));
//
//            Battery battery = findById(id);
//            battery.setImageName(imageName);
//            battery.setImageUrl(imageUrl);
//            battery.setImage_size(image.getSize());
//            batteryRepository.save(battery);
//
//        } catch (Exception e) {
//            throw new BussinessException(ExMessage.IMAGE_NOT_UPLOADED.getMessage());
//        }
//    }


    public Boolean chekMemberAuthorityUser(String userName, Long batteryId) {
        SiteResponse siteResponse = batteryRepository.findById(batteryId).orElseThrow().getSite().toResponse();
        List<Long> siteIdList = memberService.findByUserNameWithSiteIdList(userName);
        return siteIdList.contains(siteResponse.getId());
    }

//    public Boolean checkMemberRole(Member member, Long batteryId) {
//        Set<Member> members = batteryRepositoryCustom.findByIdMemberRole(batteryId).getSite().getMembers();
//        return members.contains(member);
//    }
}
