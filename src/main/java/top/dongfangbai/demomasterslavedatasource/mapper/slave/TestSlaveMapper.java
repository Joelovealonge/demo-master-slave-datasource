package top.dongfangbai.demomasterslavedatasource.mapper.slave;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestSlaveMapper {

    boolean insertSlaveUser();
}
