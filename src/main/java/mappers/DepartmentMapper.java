package mappers;

import com.avinty.hr.dto.DepartmentDto;
import com.avinty.hr.entity.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDto entityToDto(DepartmentEntity departmentEntity);

    List<DepartmentDto> entityToDto(List<DepartmentEntity> departmentEntities);
    
    DepartmentEntity dtoToEntity(DepartmentDto departmentDto);

}
