package mappers;

import com.avinty.hr.dto.EmployeeDto;
import com.avinty.hr.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto entityToDto(EmployeeEntity employeeEntity);

    List<EmployeeDto> entityToDto(List<EmployeeEntity> employeeEntities);

    EmployeeEntity dtoToEntity(EmployeeDto employeeDto);

}
