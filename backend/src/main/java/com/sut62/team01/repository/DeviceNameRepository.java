package com.sut62.team01.repository;

import com.sut62.team01.entity.DeviceName;
import com.sut62.team01.entity.DeviceType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DeviceNameRepository extends JpaRepository<DeviceName, Long> {
    DeviceName findById(long id);
    public List<DeviceName> findByDeviceType(DeviceType deviceType);
}