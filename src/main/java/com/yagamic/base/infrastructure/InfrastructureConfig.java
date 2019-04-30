package com.yagamic.base.infrastructure;

import com.yagamic.base.domain.DomainConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by xiemeilong on 16-3-8.
 */
@Configuration
@ComponentScan
//@MapperScan(basePackageClasses = {AccountMapper.class})
@EntityScan(basePackageClasses = {DomainConfig.class})
//@EnableJpaRepositories(basePackageClasses = {AccountJpaRepository.class})
//@Import({LogisticsDiscovery.class,  DataServiceApiConfig.class}) //MysqlBootConfig.class,RedisConfig.class,
@EnableAspectJAutoProxy
public class InfrastructureConfig {

 /*   @Autowired
    private LocationDataServiceGrpc.LocationDataServiceBlockingStub locationDataService;
   *//* @Autowired
    private VehicleDataServiceGrpc.VehicleDataServiceBlockingStub vehicleDataService;*//*

    @PostConstruct
    public void init() {

        LocationData.VehicleInfo vehicleInfo = LocationData.VehicleInfo
                .newBuilder()
                .setPlateColor("黄色")
                .setPlateNo("豫Q11318")
                .build();
        LocationData.CurrentPosition currentPosition = locationDataService.getVehicleCurrentLocation(vehicleInfo);
        System.out.println(currentPosition.getLongitude());
        System.out.println(currentPosition.getLatitude());

    }*/
/*
    //根据车牌颜色和车牌号码 获取车辆八根线的状态
    @PostConstruct
    public void getState() {
        VehicleData.Vehicle vehicle = VehicleData.Vehicle
                .newBuilder()
                .setPlateColor("黄色")
                .setPlateNo("豫Q11318")
                .build();

        VehicleData.CurrentState currentState = vehicleDataService.getVehicleCurrentInfo(vehicle);
        System.out.println("ACC:"+currentState.getACCSwitch());

    }*/

}
