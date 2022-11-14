package mappers;

import com.avinty.hr.dto.EmployeeInDto;
import com.avinty.hr.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeInMapper {

    EmployeeInMapper INSTANCE = Mappers.getMapper(EmployeeInMapper.class);

    EmployeeInDto entityToDto(EmployeeEntity employeeEntity);

    List<EmployeeInDto> entityToDto(List<EmployeeEntity> employeeEntities);

    EmployeeEntity dtoToEntity(EmployeeInDto employeeInDto);

}
