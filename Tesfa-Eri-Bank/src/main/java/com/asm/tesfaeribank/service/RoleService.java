package com.asm.tesfaeribank.service;



import com.asm.tesfaeribank.dto.RoleDto;

import java.util.List;


public interface RoleService {

    List<RoleDto> getRoles();

    RoleDto getRoleById(String id);

    RoleDto addRole(RoleDto roleDto);

    void deleteRole(String id);

    RoleDto updateRole(String id, RoleDto roleDto);





}
