package com.cmi.emdsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmi.emdsystem.model.PC;
import com.cmi.emdsystem.model.PCId;
import com.cmi.emdsystem.repository.PCRepository;
import com.cmi.emdsystem.request.PCReq;

@Service
public class PCServiceImpl implements PCService {

    @Autowired
    private PCRepository pcRepository;

    @Override
    public PC findPCBySerialNumAndModelNum(String serialNum, String modelNum) throws Exception {

        PCId pcId = new PCId();
        pcId.setSerialNumber(serialNum);
        pcId.setModelNumber(modelNum);
        try {
            Optional<PC> pc = pcRepository.findById(pcId);

            if (pc.isPresent()) {
                return pc.get();
            }
        } catch (Exception e) {

        }
        throw new Exception(
                "There are no PC recorded with this Serial Number:" + serialNum + " and Model Number:" + modelNum);

        /*
         * PCId pcId = new PCId(); pcId.setSerialNumber(serialNum);
         * pcId.setModelNumber(modelNum); Optional<PC> pc =
         * pcRepository.findById(pcId); if (pc.isPresent()) { return pc.get(); }
         * else { return pc.get(); }
         */
    }

    @Override
    public Integer checkIPAvailability(Integer ipAddress) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Integer savePCInfo(PC pc) {

        pcRepository.save(pc);
        return 0;
    }

    @Override
    public Integer editPCInfo(PC pc) {

        pcRepository.save(pc);
        return 0;
    }

    @Override
    public List<PC> listOfLinkedPC() {
        return pcRepository.findBylinkPcNotNull();
    }

    @Override
    public List<PC> listOfAvailablePC() {
        return pcRepository.findBylinkPcNull();
    }

    @Override
    public List<PC> findAll() {

        return pcRepository.findAll();
    }

    @Override
    public void deletePC(PCReq pcReq) {
        PC pc = new PC();
        PCId pcId = new PCId();

        pcId.setSerialNumber(pcReq.getSerialNumber());
        pcId.setModelNumber(pcReq.getModelNumber());
        pc.setPcId(pcId);
        pcRepository.delete(pc);
    }
}
