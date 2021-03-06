package io.choerodon.base.app.service;

import com.github.pagehelper.PageInfo;
import io.choerodon.base.infra.dto.UserDTO;
import org.springframework.data.domain.Pageable;
import io.choerodon.core.enums.ResourceType;
import io.choerodon.base.api.query.ClientRoleQuery;
import io.choerodon.base.api.dto.RoleAssignmentDeleteDTO;
import io.choerodon.base.infra.dto.ClientDTO;
import io.choerodon.base.infra.dto.MemberRoleDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * @author superlee
 * @author wuguokai
 * @author zmf
 */
public interface RoleMemberService {

    MemberRoleDTO insertSelective(MemberRoleDTO memberRoleDTO);

    List<MemberRoleDTO> createOrUpdateRolesByMemberIdOnSiteLevel(
            Boolean isEdit, List<Long> memberIds, List<MemberRoleDTO> memberRoleDTOList, String memberType);

    List<MemberRoleDTO> createOrUpdateRolesByMemberIdOnOrganizationLevel(
            Boolean isEdit, Long organizationId, List<Long> memberIds, List<MemberRoleDTO> memberRoleDTOList, String memberType);

    PageInfo<ClientDTO> pagingQueryClientsWithRoles(Pageable Pageable, ClientRoleQuery clientRoleSearchDTO, Long sourceId, ResourceType resourceType);

    List<MemberRoleDTO> createOrUpdateRolesByMemberIdOnProjectLevel(
            Boolean isEdit, Long projectId, List<Long> memberIds, List<MemberRoleDTO> memberRoleDTOList, String memberType);

    void deleteOnSiteLevel(RoleAssignmentDeleteDTO roleAssignmentDeleteDTO);

    void deleteOnOrganizationLevel(RoleAssignmentDeleteDTO roleAssignmentDeleteDTO);

    /**
     *
     * @param roleAssignmentDeleteDTO
     * @param syncAll 删除子项目所有权限
     */
    void deleteOnProjectLevel(RoleAssignmentDeleteDTO roleAssignmentDeleteDTO, Boolean syncAll);

    void deleteOnProjectLevel(RoleAssignmentDeleteDTO roleAssignmentDeleteDTO);

    ResponseEntity<Resource> downloadTemplatesByResourceLevel(String suffix, String resourceLevel);

    void import2MemberRole(Long sourceId, String sourceType, MultipartFile file);

    List<MemberRoleDTO> insertOrUpdateRolesOfUserByMemberId(
            Boolean isEdit, Long sourceId, Long memberId, List<MemberRoleDTO> memberRoles, String sourceType);

    List<MemberRoleDTO> insertOrUpdateRolesOfUserByMemberId(
            Boolean isEdit, Long sourceId, Long memberId, List<MemberRoleDTO> memberRoles, String sourceType, Boolean syncAll);

    List<MemberRoleDTO> insertOrUpdateRolesOfClientByMemberId(
            Boolean isEdit, Long sourceId, Long memberId, List<MemberRoleDTO> memberRoles, String sourceType);

    /**
     * 批量删除客户端及角色之间的关系
     *
     * @param roleAssignmentDeleteDTO 数据
     * @param sourceType              sourceType
     */
    void deleteClientAndRole(RoleAssignmentDeleteDTO roleAssignmentDeleteDTO, String sourceType);

    void delete(RoleAssignmentDeleteDTO roleAssignmentDeleteDTO, String sourceType);

    void insertAndSendEvent(Long fromUserId, UserDTO userDTO, MemberRoleDTO memberRole, String loginName);

    /**
     * 删除组织root
     *
     * @param organizationId
     * @param userId
     * @param memberRoleDTOS
     * @param value
     */
    void deleteOrgAdmin(Long organizationId, Long userId, List<MemberRoleDTO> memberRoleDTOS, String value, Set<String> lableNames);


    List<Long> insertOrUpdateRolesByMemberIdExecute(Long fromUserId, Boolean isEdit, Long sourceId,
                                                    Long memberId, String sourceType,
                                                    List<MemberRoleDTO> memberRoleList,
                                                    List<MemberRoleDTO> returnList, String memberType);

}
