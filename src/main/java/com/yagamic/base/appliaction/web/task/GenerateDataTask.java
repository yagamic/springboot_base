package com.yagamic.base.appliaction.web.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;



@Component
@Slf4j
public class GenerateDataTask {

    private Executor executor = Executors.newFixedThreadPool(4);
    private static final Double drop_rate = 0.3;
    //private List<VehicleOfflineSearch> list;

    //计算运营商业务覆盖城市
    //@PostConstruct
    public void setCities() {
//        List<OperatorInfo> list = operatorRepository.findAllOperator();
//        list.stream().forEach(operatorInfo -> {
//            Integer id = operatorInfo.getId();
//            String cities = "";
//            List<VehicleInfo> vList = vehicleRepository.findByOperatorId(id);
//            if (vList != null && vList.size() > 0) {
//                cities = workOrderService.getCitiesByPlateNo(vList);
//            }
//            String distinctCities = makeAlarmOrderFromDevice.distinct(cities);
//            operatorInfo.setCities(distinctCities);
//        });
//
//        operatorRepository.saveList(list);
        System.out.println("finish to update operator cities");
    }



    //@PostConstruct
    public void aaaaa() {
        //第二次删除补全未上线的车辆信息
      /*  List<VehicleInfo> vehicleInfos = vehicleRepository.findNoMessage();
        vehicleInfos.stream().forEach(vehicleInfo -> {
            vehicleOnlineTimeRepository.save(VehicleOnlineTime
                    .builder()
                    .vehicleId(vehicleInfo.getId())
                    .status(false)
                    .monthOnline(false)
                    .createDate(new Date())
                    .updateDate(new Date())
                    .offlineDate(new Date())
                    .offlineDate(new Date())
                    .build());
        });*/

    }


    //每天0点30分执行
    @Scheduled(cron = "0 30 0 * * ?")
    public void runTask() {
        executor.execute(() -> {
            //calculateOffline();
            //calculateAlarmManager();
            //addEnterpriseAndOperatorBlackList();
        });
    }

    //@Scheduled(cron = "0 55 23 L * ?") //每月最后一日的 23:50触发   @Scheduled(cron = "0 0 0 1 * ?")  @Scheduled(cron = "0 1 0 1 * ?")// 每月1日上午00:01触发
    @Scheduled(cron = "0 0 1 1 * ?")// 每月1日上午00:01触发
    public void runTask2() {
        //calculateExamine();
    }

   /* //计算首页的离线率  企业还有违规车辆报表
    public void calculateOffline() {
        log.info("start to calculate enterprise offline and offlineRate.");
        List<VehicleOfflineStatement> enterpriseList = vehicleOfflineHistoryRepository.findAllEnterpriseMessage().stream().map(vehicleOfflineSearch -> {
            VehicleOfflineStatement vehicleOfflineStatement = new VehicleOfflineStatement();

            vehicleOfflineStatement.setCreateDate(vehicleOfflineSearch.getDate());
            vehicleOfflineStatement.setEnterpriseId(vehicleOfflineSearch.getEnterpriseId() != null ? vehicleOfflineSearch.getEnterpriseId() : 0);
            vehicleOfflineStatement.setIllegalCount(vehicleOfflineSearch.getIllegalCount());
            Integer offlineCount = vehicleOfflineSearch.getTotal() - vehicleUtil.findOnlineByEnterpriseId(vehicleOfflineSearch.getEnterpriseId());

            vehicleOfflineStatement.setOfflineCount(offlineCount);
            vehicleOfflineStatement.setOfflineRate(calculateRate(vehicleOfflineStatement.getOfflineCount(), vehicleOfflineSearch.getTotal()));

            List<AlarmOrder> list = alarmOrderRepository.findAlarmByEnterpriseIdAndYesterday(vehicleOfflineStatement.getEnterpriseId());
            //List<String> sList = list.stream().map(alarmOrder -> alarmOrder.getPlateNo()).distinct().collect(Collectors.toList());

            List<AlarmOrder> offList = alarmOrderRepository.findAlarmByEnterpriseIdAndYesterdayAndOffline(vehicleOfflineStatement.getEnterpriseId());
            //List<String> sOffList = offList.stream().map(alarmOrder -> alarmOrder.getPlateNo()).distinct().collect(Collectors.toList());
            Integer illegalCount = list.size() - offList.size();

            vehicleOfflineStatement.setIllegalCount(illegalCount);
            return vehicleOfflineStatement;

        }).collect(Collectors.toList());
        vehicleOfflineStatementRepository.save(enterpriseList);

        log.info("start to calculate operator offline and offlineRate.");
        List<VehicleOfflineStatement> operatorList = vehicleOfflineHistoryRepository.findAllOperatorMessage().stream().map(vehicleOfflineSearch -> {
            VehicleOfflineStatement vehicleOfflineStatement = new VehicleOfflineStatement();
            vehicleOfflineStatement.setCreateDate(vehicleOfflineSearch.getDate());
            vehicleOfflineStatement.setOperatorId(vehicleOfflineSearch.getOperatorId());
            //vehicleOfflineStatement.setIllegalCount(vehicleOfflineSearch.getIllegalCount());
            Integer offlineCount = vehicleOfflineSearch.getTotal() - vehicleUtil.findOnlineByOperatorId(vehicleOfflineSearch.getOperatorId());

            vehicleOfflineStatement.setOfflineCount(offlineCount);
            vehicleOfflineStatement.setOfflineRate(calculateRate(vehicleOfflineStatement.getOfflineCount(), vehicleOfflineSearch.getTotal()));
            return vehicleOfflineStatement;
        }).collect(Collectors.toList());
        vehicleOfflineStatementRepository.save(operatorList);

        log.info("finish to calculate  offline and offlineRate.");

        // 把vehicle_online_time 表中的 day_online 清零 (status为0的清零,1的不需要)
        vehicleOfflineHistoryRepository.updateStatus();
    }

    //计算报警管理报表
    public void calculateAlarmManager() {
        Calendar calendar = Calendar.getInstance();//获取当前日期
        calendar.add(calendar.DATE, -1);
        Date createDate = calendar.getTime();
        log.info("start to calculate alarmManager for city");
        //地市

        //离线
        List<AlarmManagerStatement> offlineListCity = alarmOrderRepository.findAlarmByAllCity(101, "离线");
        offlineListCity.stream().forEach(alarmManagerStatement -> {
            alarmManagerStatement.setCount(alarmRepository.findAlarmDaoByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), 101, "离线"));
            alarmManagerStatement.setRate(calculateRate(alarmManagerStatement.getCount(), alarmManagerStatement.getTotal()));

            List<AlarmOrder> list = alarmOrderRepository.findAlarmOrderByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), "离线");
            Integer alarmVehicle = list.size();//distinctVehicle(list);
            alarmManagerStatement.setAlarmVehicle(alarmVehicle);

            alarmManagerStatement.setType(1);
            alarmManagerStatement.setCreateDate(createDate);
            alarmManagerStatementJpaRepository.save(alarmManagerStatement);
        });
        //超速
        List<AlarmManagerStatement> overSpeedListCity = alarmOrderRepository.findAlarmByAllCity(2, "超速");
        overSpeedListCity.stream().forEach(alarmManagerStatement -> {
            alarmManagerStatement.setCount(alarmRepository.findAlarmDaoByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), 2, "超速"));
            alarmManagerStatement.setRate(calculateRate(alarmManagerStatement.getCount(), alarmManagerStatement.getTotal()));
            List<AlarmOrder> list = alarmOrderRepository.findAlarmOrderByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), "超速");
            Integer alarmVehicle = list.size();//distinctVehicle(list);
            alarmManagerStatement.setAlarmVehicle(alarmVehicle);

            alarmManagerStatement.setType(2);
            alarmManagerStatement.setCreateDate(createDate);
            alarmManagerStatementJpaRepository.save(alarmManagerStatement);
        });
        //疲劳
        List<AlarmManagerStatement> tiredListCity = alarmOrderRepository.findAlarmByAllCity(4, "疲劳驾驶");
        tiredListCity.stream().forEach(alarmManagerStatement -> {
            alarmManagerStatement.setCount(alarmRepository.findAlarmDaoByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), 4, "疲劳驾驶"));
            alarmManagerStatement.setRate(calculateRate(alarmManagerStatement.getCount(), alarmManagerStatement.getTotal()));

            List<AlarmOrder> list = alarmOrderRepository.findAlarmOrderByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), "疲劳驾驶");
            Integer alarmVehicle = list.size();//distinctVehicle(list);
            alarmManagerStatement.setAlarmVehicle(alarmVehicle);

            alarmManagerStatement.setType(3);
            alarmManagerStatement.setCreateDate(createDate);
            alarmManagerStatementJpaRepository.save(alarmManagerStatement);
        });
        //违禁时段
        List<AlarmManagerStatement> banTimeListCity = alarmOrderRepository.findAlarmByAllCity(102, "违禁时段");
        banTimeListCity.stream().forEach(alarmManagerStatement -> {
            alarmManagerStatement.setCount(alarmRepository.findAlarmDaoByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), 102, "违禁时段"));
            alarmManagerStatement.setRate(calculateRate(alarmManagerStatement.getCount(), alarmManagerStatement.getTotal()));
            List<AlarmOrder> list = alarmOrderRepository.findAlarmOrderByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), "违禁时段");
            Integer alarmVehicle = list.size();//distinctVehicle(list);
            alarmManagerStatement.setAlarmVehicle(alarmVehicle);

            alarmManagerStatement.setType(4);
            alarmManagerStatement.setCreateDate(createDate);
            alarmManagerStatementJpaRepository.save(alarmManagerStatement);
        });
        //违禁区域
        List<AlarmManagerStatement> banAreaListCity = alarmOrderRepository.findAlarmByAllCity(1048576, "进出区域");//1 048 576
        banAreaListCity.stream().forEach(alarmManagerStatement -> {
            alarmManagerStatement.setCount(alarmRepository.findAlarmDaoByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), 1048576, "进出区域"));
            alarmManagerStatement.setRate(calculateRate(alarmManagerStatement.getCount(), alarmManagerStatement.getTotal()));
            List<AlarmOrder> list = alarmOrderRepository.findAlarmOrderByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), "进出区域");
            Integer alarmVehicle = list.size();//distinctVehicle(list);
            alarmManagerStatement.setAlarmVehicle(alarmVehicle);
            alarmManagerStatement.setType(5);
            alarmManagerStatement.setCreateDate(createDate);
            alarmManagerStatementJpaRepository.save(alarmManagerStatement);
        });

        log.info("start to calculate alarmManager for county");
        //区县
        List<AlarmManagerStatement> offlineListCounty = alarmOrderRepository.findAlarmByAllCounty(101, "离线");
        offlineListCounty.stream().forEach(alarmManagerStatement -> {
            alarmManagerStatement.setCount(alarmRepository.findAlarmDaoByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), 101, "离线"));
            alarmManagerStatement.setCreateDate(createDate);
            alarmManagerStatement.setRate(calculateRate(alarmManagerStatement.getCount(), alarmManagerStatement.getTotal()));
            List<AlarmOrder> list = alarmOrderRepository.findAlarmOrderByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), "离线");
            Integer alarmVehicle = list.size();//distinctVehicle(list);
            alarmManagerStatement.setAlarmVehicle(alarmVehicle);

            alarmManagerStatement.setType(1);
            alarmManagerStatementJpaRepository.save(alarmManagerStatement);
        });
        List<AlarmManagerStatement> overSpeedListCounty = alarmOrderRepository.findAlarmByAllCounty(2, "超速");
        overSpeedListCounty.stream().forEach(alarmManagerStatement -> {
            alarmManagerStatement.setCount(alarmRepository.findAlarmDaoByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), 2, "超速"));
            alarmManagerStatement.setCreateDate(createDate);
            alarmManagerStatement.setRate(calculateRate(alarmManagerStatement.getCount(), alarmManagerStatement.getTotal()));
            List<AlarmOrder> list = alarmOrderRepository.findAlarmOrderByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), "超速");
            Integer alarmVehicle = list.size();//distinctVehicle(list);
            alarmManagerStatement.setAlarmVehicle(alarmVehicle);
            alarmManagerStatement.setType(2);
            alarmManagerStatementJpaRepository.save(alarmManagerStatement);
        });
        List<AlarmManagerStatement> tiredListCounty = alarmOrderRepository.findAlarmByAllCounty(4, "疲劳驾驶");
        tiredListCounty.stream().forEach(alarmManagerStatement -> {
            alarmManagerStatement.setCount(alarmRepository.findAlarmDaoByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), 4, "疲劳驾驶"));
            alarmManagerStatement.setCreateDate(createDate);
            alarmManagerStatement.setRate(calculateRate(alarmManagerStatement.getCount(), alarmManagerStatement.getTotal()));
            List<AlarmOrder> list = alarmOrderRepository.findAlarmOrderByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), "疲劳驾驶");
            Integer alarmVehicle = list.size();//distinctVehicle(list);
            alarmManagerStatement.setAlarmVehicle(alarmVehicle);
            alarmManagerStatement.setType(3);
            alarmManagerStatementJpaRepository.save(alarmManagerStatement);
        });
        List<AlarmManagerStatement> banTimeListCounty = alarmOrderRepository.findAlarmByAllCounty(102, "违禁时段");
        banTimeListCounty.stream().forEach(alarmManagerStatement -> {
            alarmManagerStatement.setCount(alarmRepository.findAlarmDaoByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), 102, "违禁时段"));
            alarmManagerStatement.setCreateDate(createDate);
            alarmManagerStatement.setRate(calculateRate(alarmManagerStatement.getCount(), alarmManagerStatement.getTotal()));
            List<AlarmOrder> list = alarmOrderRepository.findAlarmOrderByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), "违禁时段");
            Integer alarmVehicle = list.size();//distinctVehicle(list);
            alarmManagerStatement.setAlarmVehicle(alarmVehicle);
            alarmManagerStatement.setType(4);
            alarmManagerStatementJpaRepository.save(alarmManagerStatement);
        });

        List<AlarmManagerStatement> banAreaListCounty = alarmOrderRepository.findAlarmByAllCounty(1048576, "进出区域");
        banAreaListCounty.stream().forEach(alarmManagerStatement -> {
            alarmManagerStatement.setCount(alarmRepository.findAlarmDaoByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), 1048576, "进出区域"));
            alarmManagerStatement.setCreateDate(createDate);
            alarmManagerStatement.setRate(calculateRate(alarmManagerStatement.getCount(), alarmManagerStatement.getTotal()));
            List<AlarmOrder> list = alarmOrderRepository.findAlarmOrderByOrgIdAndYesterday(alarmManagerStatement.getOrganizationId(), "进出区域");
            Integer alarmVehicle = list.size();//distinctVehicle(list);
            alarmManagerStatement.setAlarmVehicle(alarmVehicle);
            alarmManagerStatement.setType(5);
            alarmManagerStatementJpaRepository.save(alarmManagerStatement);
        });
        log.info("finished to calculate alarmManager !");
    }

    //计算 考核报表
    public void calculateExamine() {
        Calendar calendar = Calendar.getInstance();//获取当前日期
        calendar.add(calendar.DATE, -1);
        Date createDate = calendar.getTime();
        //所有城市
        List<ExamineOrganizationStatement> cityThisMonth = examineOrganizationRepository.findCityThisMonth();
        cityThisMonth.stream().forEach(examineOrganizationStatement -> {
            examineOrganizationStatement.setIsCityList(true);
            examineOrganizationStatement.setCreateDate(createDate);
            Double onlineRate = calculateRate(examineOrganizationStatement.getOnline(), examineOrganizationStatement.getNet());
            Double vehicleNetRate = calculateRate(examineOrganizationStatement.getNet(), examineOrganizationStatement.getTotal());
            examineOrganizationStatement.setVehicleOnlineRate(onlineRate);
            examineOrganizationStatement.setVehicleNetRate(vehicleNetRate);
            // 这里只有 入网率和在线率
            Double trajectoryRate = 0D;
            if (examineOrganizationStatement.getComplete() != null && examineOrganizationStatement.getIncomplete() != null) {
                trajectoryRate = calculateRate(examineOrganizationStatement.getComplete(), (examineOrganizationStatement.getComplete() + examineOrganizationStatement.getIncomplete()));
            }
            examineOrganizationStatement.setDataPassRate(1D);
            examineOrganizationStatement.setPlatformConnectRate(1D);
            examineOrganizationStatement.setTrajectoryRate(trajectoryRate);
            examineOrganizationStatement.setCrossDomainDataRate(0D);

            examineOrganizationStatement.setScore(calculateOrganizationScore(examineOrganizationStatement.getVehicleNetRate(),
                    examineOrganizationStatement.getVehicleOnlineRate(), examineOrganizationStatement.getPlatformConnectRate(),
                    examineOrganizationStatement.getTrajectoryRate(), examineOrganizationStatement.getDataPassRate(),
                    examineOrganizationStatement.getCrossDomainDataRate()));
        });
        examineOrganizationRepository.saveList(cityThisMonth);
        log.info("finish to city  calculateExamine.");
        //每个机构
        List<ExamineOrganizationStatement> countyThisMonth = examineOrganizationRepository.findCountyThisMonth();
        countyThisMonth.stream().forEach(examineOrganizationStatement -> {
            examineOrganizationStatement.setIsCityList(false);
            examineOrganizationStatement.setCreateDate(createDate);
            Double onlineRate = calculateRate(examineOrganizationStatement.getOnline(), examineOrganizationStatement.getNet());
            Double vehicleNetRate = calculateRate(examineOrganizationStatement.getNet(), examineOrganizationStatement.getTotal());
            examineOrganizationStatement.setVehicleOnlineRate(onlineRate);
            examineOrganizationStatement.setVehicleNetRate(vehicleNetRate);
            // 这里只有 入网率和在线率
            Double trajectoryRate = 0D;
            if (examineOrganizationStatement.getComplete() != null && examineOrganizationStatement.getIncomplete() != null) {
                trajectoryRate = calculateRate(examineOrganizationStatement.getComplete(), (examineOrganizationStatement.getComplete() + examineOrganizationStatement.getIncomplete()));
            }
            examineOrganizationStatement.setDataPassRate(1D);
            examineOrganizationStatement.setPlatformConnectRate(1D);
            examineOrganizationStatement.setTrajectoryRate(trajectoryRate);
            examineOrganizationStatement.setCrossDomainDataRate(0D);

            examineOrganizationStatement.setScore(calculateOrganizationScore(examineOrganizationStatement.getVehicleNetRate(),
                    examineOrganizationStatement.getVehicleOnlineRate(), examineOrganizationStatement.getPlatformConnectRate(),
                    examineOrganizationStatement.getTrajectoryRate(), examineOrganizationStatement.getDataPassRate(),
                    examineOrganizationStatement.getCrossDomainDataRate()));
        });
        examineOrganizationRepository.saveList(countyThisMonth);
        log.info("finish to organization  calculateExamine.");

        //运营商
        List<ExamineOperatorStatement> examineOperatorStatements = examineOperatorStatementRepository.findThisMonth();
        examineOperatorStatements.forEach(examineOperatorStatement -> {
            examineOperatorStatement.setCreateDate(createDate);
            examineOperatorStatement.setVehicleOnlineRate(calculateRate(examineOperatorStatement.getOnline(), examineOperatorStatement.getNet()));
            // 这里只有 在线率
            Double trajectoryRate = 0D;
            if (examineOperatorStatement.getComplete() != null && examineOperatorStatement.getIncomplete() != null) {
                trajectoryRate = calculateRate(examineOperatorStatement.getComplete(), (examineOperatorStatement.getComplete() + examineOperatorStatement.getIncomplete()));
            }
           *//* Double satellitePositioningDriftRate = 0D;
            if (examineOperatorStatement.getAllDot() != null && examineOperatorStatement.getDriftDot() != null) {
                satellitePositioningDriftRate = calculateRate(examineOperatorStatement.getDriftDot(), examineOperatorStatement.getAllDot());
            }*//*
            int dropVehicle = getDopVehicleCountByOperatorId(examineOperatorStatement.getOperatorId());
            Double satellitePositioningDriftRate = calculateRate(dropVehicle, examineOperatorStatement.getOnline());

            examineOperatorStatement.setPlatformConnectRate(1D);
            examineOperatorStatement.setSatellitePositioningDriftRate(satellitePositioningDriftRate);
            examineOperatorStatement.setTrajectoryRate(trajectoryRate);
            examineOperatorStatement.setDataPassRate(1D);
            examineOperatorStatement.setScore(calculateOperatorScore(examineOperatorStatement.getVehicleOnlineRate(), examineOperatorStatement.getPlatformConnectRate(),
                    examineOperatorStatement.getTrajectoryRate(), examineOperatorStatement.getDataPassRate(), examineOperatorStatement.getSatellitePositioningDriftRate()));
        });
        examineOperatorStatementRepository.saveList(examineOperatorStatements);
        log.info("finish to operator  calculateExamine.");

        //企业
        List<ExamineEnterpriseStatement> examineEnterpriseStatements = examineEnterpriseRepository.findLastMonth();//findThisMonth  看定时任务的时间  findLastMonth
        examineEnterpriseStatements.stream().forEach(examineEnterpriseStatement -> {
            examineEnterpriseStatement.setCreateDate(createDate);


            examineEnterpriseStatement.setVehicleOnlineRate(calculateRate(examineEnterpriseStatement.getOnline(), examineEnterpriseStatement.getNet()));
            examineEnterpriseStatement.setVehicleNetRate(calculateRate(examineEnterpriseStatement.getNet(), examineEnterpriseStatement.getTotal()));
            //examineEnterpriseStatement.setAverageOverSpeed(0D);
            //examineEnterpriseStatement.setAreaAverageOverSpeed(1D);
            examineEnterpriseStatement.setAverageOverSpeed(calculateRate(examineEnterpriseStatement.getOverSpeed(), examineEnterpriseStatement.getOnline()));
            examineEnterpriseStatement.setAreaAverageOverSpeed(calculateRate(examineEnterpriseStatement.getAllOverSpeed(), examineEnterpriseStatement.getAreaTotal()));
            //设置疲劳时长
            Double fatigue;
            Double allFatigue;
            if (examineEnterpriseStatement.getFatigue() == null) {
                fatigue = 0D;
            } else {
                fatigue = calculateRate(examineEnterpriseStatement.getFatigue(), examineEnterpriseStatement.getOnline());
            }
            if (examineEnterpriseStatement.getAllFatigue() == null) {
                allFatigue = 0D;
            } else {
                allFatigue = calculateRate(examineEnterpriseStatement.getAllFatigue(), examineEnterpriseStatement.getAreaTotal());
            }

            examineEnterpriseStatement.setAverageFatigueDriving(fatigue);//fatigue
            examineEnterpriseStatement.setAreaAverageFatigueDriving(allFatigue);//allFatigue

            // 这里只有 入网率和在线率  超速次数  疲劳时长(单位是秒)
            Double trajectoryRate = 0D;
            if (examineEnterpriseStatement.getComplete() != null && examineEnterpriseStatement.getIncomplete() != null) {
                trajectoryRate = calculateRate(examineEnterpriseStatement.getComplete(), (examineEnterpriseStatement.getComplete() + examineEnterpriseStatement.getIncomplete()));
            }
            //Double satellitePositioningDriftRate = 0D;//漂移率
            int dropVehicle = getDopVehicleCountByEnterpriseId(examineEnterpriseStatement.getEnterpriseId());
            Double satellitePositioningDriftRate = calculateRate(dropVehicle, examineEnterpriseStatement.getOnline());

            examineEnterpriseStatement.setTrajectoryRate(trajectoryRate);
            examineEnterpriseStatement.setSatellitePositioningDriftRate(satellitePositioningDriftRate);
            examineEnterpriseStatement.setCheckResponseRate(calculateRate(examineEnterpriseStatement.getResponseCount(), examineEnterpriseStatement.getInspectCount()));
            examineEnterpriseStatement.setDataPassRate(1D);

            examineEnterpriseStatement.setScore(calculateEnterpriseScore(examineEnterpriseStatement.getVehicleNetRate(),
                    examineEnterpriseStatement.getVehicleOnlineRate(), examineEnterpriseStatement.getTrajectoryRate(), examineEnterpriseStatement.getDataPassRate()
                    , examineEnterpriseStatement.getSatellitePositioningDriftRate(),
                    examineEnterpriseStatement.getAverageOverSpeed(),
                    examineEnterpriseStatement.getAreaAverageOverSpeed(),
                    examineEnterpriseStatement.getAverageFatigueDriving(),
                    examineEnterpriseStatement.getAreaAverageFatigueDriving(),
                    examineEnterpriseStatement.getCheckResponseRate()));
        });
        examineEnterpriseRepository.saveList(examineEnterpriseStatements);

        log.info("finish to calculate  examine-statement !!!");


//测试以后改好
        //把轨迹完整率的 数量清零
        List<VehicleMotion> vehicleMotionList = vehicleMotionRepository.findAll();
        vehicleMotionList.stream().forEach(vehicleMotion -> {
            vehicleMotion.setComplete(0);
            vehicleMotion.setIncomplete(0);
        });
        vehicleMotionRepository.saveList(vehicleMotionList);
        //月上线车辆 数量清零
        List<VehicleOnlineTime> vehicleOnlineTimeList = vehicleOnlineTimeRepository.findAll();
        vehicleOnlineTimeList.stream().forEach(vehicleOnlineTime -> vehicleOnlineTime.setMonthOnline(false));
        vehicleOnlineTimeRepository.saveList(vehicleOnlineTimeList);
        //卫星定位漂移率  数量清零
        List<SatellitePositioningDrift> satellitePositioningDriftList = satellitePositioningDriftRepository.findAll();
        satellitePositioningDriftList.stream().forEach(satellitePositioningDrift -> {
            satellitePositioningDrift.setAllDot(0L);
            satellitePositioningDrift.setDriftDot(0L);
        });
        satellitePositioningDriftRepository.saveList(satellitePositioningDriftList);


    }

    //计算 漂移的车辆数
    public Integer getDopVehicleCountByEnterpriseId(Long enterpriseId) {
        List<Long> l = new ArrayList<>();
        List<VehicleInfo> list = vehicleRepository.findByEnterpriseId(enterpriseId);
        list.stream().forEach(vehicleInfo -> {
            SatellitePositioningDrift satellitePositioningDrift = satellitePositioningDriftRepository.findByVehicleId(vehicleInfo.getId());
            if (satellitePositioningDrift != null) {
                Double d = calculateRate(satellitePositioningDrift.getDriftDot(), satellitePositioningDrift.getAllDot());
                if (d > drop_rate) {
                    l.add(vehicleInfo.getId());
                }
            }
        });
        return l.size();
    }

    public Integer getDopVehicleCountByOperatorId(Integer operatorId) {
        List<Long> l = new ArrayList<>();
        List<VehicleInfo> list = vehicleRepository.findByOperatorId(operatorId);
        list.stream().forEach(vehicleInfo -> {
            SatellitePositioningDrift satellitePositioningDrift = satellitePositioningDriftRepository.findByVehicleId(vehicleInfo.getId());
            if (satellitePositioningDrift != null) {
                Double d = calculateRate(satellitePositioningDrift.getDriftDot(), satellitePositioningDrift.getAllDot());
                if (d > drop_rate) {
                    l.add(vehicleInfo.getId());
                }
            }
        });
        return l.size();
    }


    //生成 企业(报警工单超期) / 运营商(上报工单超期) 黑名单
    public void addEnterpriseAndOperatorBlackList() {
        List<EnterpriseInfo> eList = homeUtil.getEnterpriseOverdue();
        eList.stream().forEach(enterpriseInfo -> {
            if (enterpriseInfo.getOverdue() > 0) {
                EnterpriseBlackList e = enterpriseBlackListRepository.findIsDeleteById(enterpriseInfo.getId());
                if (e == null) {
                    enterpriseBlackListRepository.save(EnterpriseBlackList
                            .builder()
                            .enterpriseId(enterpriseInfo.getId())
                            .blockingReason("报警工单超期未处理")
                            .blockingDate(new Date())
                            .isDelete(false)
                            .build());
                }

            }
        });
        log.info("finish to make  enterprise-blacklist !");
        List<OperatorInfo> oList = homeUtil.getOperatorOverdue();
        oList.stream().forEach(operatorInfo -> {
            if (operatorInfo.getOverdue() > 0) {
                OperatorBlackList o = operatorBlackListRepository.findIsDeleteById(operatorInfo.getId());
                if (o == null) {
                    operatorBlackListRepository.save(OperatorBlackList
                            .builder()
                            .operatorId(operatorInfo.getId())
                            .blockingReason("上报工单超期未处理")
                            .blockingDate(new Date())
                            .isDelete(false)
                            .build());
                }

            }

        });
        log.info("finish to make  operator-blacklist !");
    }*/


    private double calculateRate(double val1, double val2) {
        if (val2 == 0) {
            return 0;
        }
        return val1 / val2;
    }

   /* public Integer distinctVehicle(List<AlarmOrder> list) {
        List<Long> vList = list.stream().map(alarmOrder -> alarmOrder.getVehicleId()).distinct().collect(Collectors.toList());
        return vList.size();
    }*/

    private double calculateOrganizationScore(double vehicleNetRate,
                                              double vehicleOnlineRate,
                                              double platformConnectRate,
                                              double trajectoryRate,
                                              double dataPassRate,
                                              double crossDomainDataRate) {
        double score = 0;
        if (vehicleNetRate >= 0.9) {
            score += vehicleNetRate * 5;
        }
        if (true) {
            score += vehicleOnlineRate * 10;
        }
        if (platformConnectRate >= 0.9) {
            score += platformConnectRate * 15;
        }
        if (trajectoryRate >= 0.7) {
            score += trajectoryRate * 30;
        }
        if (dataPassRate >= 0.8) {
            score += dataPassRate * 30;
        }
        score += crossDomainDataRate * 10;
        return score;
    }

    private double calculateOperatorScore(double vehicleOnlineRate,
                                          double platformConnectRate,
                                          double trajectoryRate,
                                          double dataPassRate,
                                          double satellitePositioningDriftRate
    ) {
        double score = 0;
        if (true) {
            score += vehicleOnlineRate * 10;
        }
        if (platformConnectRate >= 0.9) {
            score += platformConnectRate * 20;
        }
        if (trajectoryRate >= 0.7) {
            score += trajectoryRate * 25;
        }
        if (dataPassRate >= 0.8) {
            score += dataPassRate * 25;
        }
        if (satellitePositioningDriftRate <= 0.05) {
            score += (20 - satellitePositioningDriftRate * 20);
        }

        return score;
    }


    private double calculateEnterpriseScore(double vehicleNetRate,
                                            double vehicleOnlineRate,
                                            double trajectoryRate,
                                            double dataPassRate,
                                            double satellitePositioningDriftRate,
                                            double averageOverSpeed,
                                            double areaAverageOverSpeed,
                                            double averageFatigueDriving,
                                            double areaAverageFatigueDriving,
                                            double checkResponseRate) {
        double score = 0;
        if (vehicleNetRate >= 0.9) {
            score += vehicleNetRate * 5;
        }
        if (true) {
            score += vehicleOnlineRate * 5;
        }
        if (trajectoryRate >= 0.7) {
            score += trajectoryRate * 15;
        }
        if (dataPassRate >= 0.8) {
            score += dataPassRate * 15;
        }
        if (satellitePositioningDriftRate <= 0.05) {
            score += (10 - satellitePositioningDriftRate * 10);
        }
        if (averageOverSpeed <= areaAverageOverSpeed && areaAverageOverSpeed != 0) {
            score += (10 + (areaAverageOverSpeed - averageOverSpeed) / areaAverageOverSpeed * 10);
        } else if (averageOverSpeed > areaAverageOverSpeed && (averageOverSpeed < areaAverageOverSpeed * 2) && areaAverageOverSpeed != 0) {
            score += ((areaAverageOverSpeed * 2 - averageOverSpeed) / areaAverageOverSpeed * 10);
        } else if (areaAverageOverSpeed == 0) {
            score += 20;
        }
        if (averageFatigueDriving <= areaAverageFatigueDriving && areaAverageFatigueDriving != 0) {
            score += (10 + (areaAverageFatigueDriving - averageFatigueDriving) / areaAverageFatigueDriving * 10);
        } else if (averageFatigueDriving > areaAverageFatigueDriving && (averageFatigueDriving < areaAverageFatigueDriving * 2) && areaAverageFatigueDriving != 0) {
            score += ((areaAverageFatigueDriving * 2 - averageFatigueDriving) / areaAverageFatigueDriving * 10);
        } else if (areaAverageFatigueDriving == 0) {
            score += 20;
        }
        if (true) {
            score += checkResponseRate * 10;
        }
        return score;
    }
}
